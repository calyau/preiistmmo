(ns preiistmmo.impl.apache.math3
  (:require
    [preiistmmo.impl.common :as common]
    [preiistmmo.util :as util])
  (:import
    (org.apache.commons.math3.primes Primes)))

(defrecord ApachePrimeCheck [])

(defn n-primes
  "Use the `apache.commons` `Primes/isPrime` static method to get a lazy
  sequence of the first `n` prime numbers."
  ([this prime-count]
    (take prime-count (filter #(Primes/isPrime %)(range))))
  ([this prime-count start]
    (take prime-count (filter #(Primes/isPrime %) (iterate inc start)))))

(defn prime-grid
  ([this i-max]
    (prime-grid this i-max i-max))
  ([this i-max j-max]
    (prime-grid this i-max i-max common/default-start))
  ([this i-max j-max start]
    (prime-grid this i-max i-max start common/default-operation))
  ([this i-max j-max start op]
    (common/prime-grid this n-primes i-max j-max start op)))

(defn print-prime-grid
  ([this i-max]
    (print-prime-grid this i-max i-max))
  ([this i-max j-max]
    (print-prime-grid this i-max i-max common/default-start))
  ([this i-max j-max start]
    (print-prime-grid this i-max i-max start common/default-operation))
  ([this i-max j-max start op]
    (common/print-prime-grid this n-primes i-max j-max start op)))

(def behaviour {
  :n-primes n-primes
  :prime-grid prime-grid
  :print-prime-grid print-prime-grid})

(def create ->ApachePrimeCheck)
