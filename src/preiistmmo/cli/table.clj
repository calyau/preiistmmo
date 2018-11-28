(ns preiistmmo.cli.table
  (:require
    [clojure.string :as string]
    [clojure.tools.cli :as cli]
    [preiistmmo.cli.common :as common]
    [preiistmmo.core :as preiistmmo])
  (:gen-class))

(def cli-opts
  (concat
    [["-c" "--columns INTEGER" "The number of primes to to use for columns"
      :default 10
      :parse-fn #(Integer/parseInt %)
      :validate [int? "Must be an integer"]]
     ["-r" "--rows INTEGER" "The number of primes to to use for rows"
      :default 10
      :parse-fn #(Integer/parseInt %)
      :validate [int? "Must be an integer"]]
     ["-o" "--operation SYMBOL" (format (str "The arithmatic operation to "
                                             "perform (one of %s)")
                                        (vec common/supported-operations))
      :default "*"
      :validate [#(contains? common/supported-operations %)
                 (format "Must be one of %s"
                         (vec common/supported-operations))]]]
    common/cli-opts))

(defn run
  [{{:keys [columns rows operation start algorithm]} :options}]
  (let [algo (preiistmmo/select-algo (keyword algorithm))]
    (println)
    (preiistmmo/print-prime-grid algo rows columns operation)
    (println)))

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
