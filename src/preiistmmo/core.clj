(ns preiistmmo.core
  (:require
    [clojure.string :as string]
    [clojure.tools.cli :as cli]
    [preiistmmo.impl.common :as common]
    [preiistmmo.impl.divisors.lazy :as divisors-lazy]
    [preiistmmo.impl.eratosthenes.lazy :as eratosthenes-lazy]
    [preiistmmo.impl.sundaram.simple :as sundaram-simple])
  (:import
    (clojure.lang Keyword)
    (preiistmmo.impl.divisors.lazy PrimeDivisorsTrial)
    (preiistmmo.impl.eratosthenes.lazy EratosthenesSieve)
    (preiistmmo.impl.sundaram.simple SundaramSieve)))

(def operations common/operations)

(defprotocol PrimesAPI
  (n-primes [this n] [this n start]
    "Find the first `n` prime numbers. Optionally, the starting integer may be
    supplied, if the algorithm supports it.")
  (prime-grid [this i] [this i j] [this i j op]
    "Produce a data structure representing prime numbers multiplied by each
    other. If just `i` is provided, the results are in an `i` x `i` square
    matrix; if `j` is also provided, the results are in an `i` x `j` matrix.")
  (print-prime-grid [this i] [this i j] [this i j op]
    "Produce tabular data printed to stdout representing prime numbers
    multiplied by each other. If just `i` is provided, the results table has
    equal comumns and rows; if `j` is also provided, the results table will
    have `i` columns and `j` rows. If `op` is provided, the default arithmatic
    operation will be overridden by what the user provides."))

(extend PrimeDivisorsTrial
        PrimesAPI
        divisors-lazy/behaviour)

(extend EratosthenesSieve
        PrimesAPI
        eratosthenes-lazy/behaviour)

(extend SundaramSieve
        PrimesAPI
        sundaram-simple/behaviour)

(def algos
  {:divisors divisors-lazy/create
   :eratosthenes eratosthenes-lazy/create
   :sundaram sundaram-simple/create})

(defn select-algo
  [^Keyword algo]
  (let [algo-constructor (algo algos)]
    (if (nil? algo-constructor)
      :not-implemented
      (algo-constructor))))
