(ns preiistmmo.cli.primes
  (:require
    [clojure.string :as string]
    [clojure.tools.cli :as cli]
    [preiistmmo.cli.common :as common]
    [preiistmmo.core :as preiistmmo])
  (:gen-class))

(def supported-algorithms
  (map name (keys preiistmmo/algos)))

(def cli-opts
  [["-c" "--count INTEGER" "The number of primes to return"
    :default 10
    :parse-fn #(Integer/parseInt %)
    :validate [int? "Must be an integer"]]
   ["-s" "--start INTEGER" (str "The starting integer (only valid for the "
                        "'divisors' algorithm)")
    :default 2
    :parse-fn #(Integer/parseInt %)
    :validate [int? "Must be an integer"]]
   ["-a" "--algorithm NAME" (format (str "The prime number generating "
                                    "algorithm to use (one of %s)")
                               (vec supported-algorithms))
    :default "divisors"
    :validate [#(contains? supported-algorithms %)
               (format "Must be one of %s" supported-algorithms)]]
   ["-h" "--help" "This usage text."]])

(defn run
  [{{:keys [count start algorithm]} :options}]
  (let [algo (preiistmmo/select-algo (keyword algorithm))]
    (println "\n"
             (if (> start 2)
               (preiistmmo/n-primes algo count start)
               (preiistmmo/n-primes algo count))
             "\n")))

(defn -main
  [& args]
  (let [parsed (cli/parse-opts args cli-opts)
        errors (:errors parsed)]
    (cond (get-in parsed [:options :help])
          (println (common/help parsed))

          (not (nil? errors))
          (println (common/error parsed))

          :else
          (run parsed))))
