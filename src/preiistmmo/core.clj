(ns preiistmmo.core
  (:require
    [clojure.string :as string]
    [clojure.tools.cli :as cli]
    [preiistmmo.impl.divisors.lazy :as divisors-lazy]
    [preiistmmo.impl.eratosthenes.lazy :as eratosthenes-lazy]
    [preiistmmo.impl.sundaram.simple :as sundaram-simple])
  (:import
    (clojure.lang Keyword)
    (preiistmmo.impl.divisors.lazy PrimeDivisorsTrial)
    (preiistmmo.impl.eratosthenes.lazy EratosthenesSieve)
    (preiistmmo.impl.sundaram.simple SundaramSieve))
  (:gen-class))

(defprotocol PrimesAPI
  (n-primes [this n] [this n start]
    "Find the first `n` prime numbers. Optionally, the starting integer may be
    supplied, if the algorithm supports it.")
  (prime-grid [this i] [this i j]
    "Produce a data structure representing prime numbers multiplied by each
    other. If just `i` is provided, the results are in an `i` x `i` square
    matrix; if `j` is also provided, the results are in an `i` x `j` matrix.")
  (print-prime-grid [this i] [this i j]
    "Produce tabular data printed to stdout representing prime numbers
    multiplied by each other. If just `i` is provided, the results table has
    equal comumns and rows; if `j` is also provided, the results table will
    have `i` columns and `j` rows."))

(extend PrimeDivisorsTrial
        PrimesAPI
        divisors-lazy/behaviour)

(extend EratosthenesSieve
        PrimesAPI
        eratosthenes-lazy/behaviour)

(extend SundaramSieve
        PrimesAPI
        sundaram-simple/behaviour)

(defn select-algo
  [^Keyword algo]
  (case algo
    :divisors (divisors-lazy/create)
    :eratosthenes (eratosthenes-lazy/create)
    :sundaram (sundaram-simple/create)
    :not-implemented))

(def supported-algorithms
  #{"divisors" "eratosthenes" "sundaram"})

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
               (format "Must be one of %s" (vec supported-algorithms))]]
   ["-h" "--help" "This usage text."]])

(defn run
  [{{:keys [count start algorithm]} :options}]
  (println "\n"
           (if (> start 2)
            (n-primes (select-algo (keyword algorithm)) count start)
            (n-primes (select-algo (keyword algorithm)) count))
           "\n"))

(defn help
  [parsed-opts]
  (format "\nOptions:\n\n%s\n"
          (:summary parsed-opts)))

(defn error
  [parsed-opts]
  (format "\nError: %s\n%s"
    (string/join "; " (:errors parsed-opts))
    (help parsed-opts)))

(defn -main
  [& args]
  (let [parsed (cli/parse-opts args cli-opts)
        errors (:errors parsed)]
    (cond (get-in parsed [:options :help])
          (println (help parsed))

          (not (nil? errors))
          (println (error parsed))

          :else
          (run parsed))))
