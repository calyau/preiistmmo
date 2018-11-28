(ns preiistmmo.impl.divisors.lazy
  (:require
    [preiistmmo.impl.common :as common]
    [preiistmmo.util :as util]))

(defrecord PrimeDivisorsTrial [])

(defn n-primes
  "Use the `util.prime?` function to get a lazy sequence of the first `n` prime
  numbers."
  ([this prime-count]
    (take prime-count (filter util/prime? (range))))
  ([this prime-count start]
    (take prime-count (filter util/prime? (iterate inc start)))))

(defn prime-grid
  ([this i-max]
    (prime-grid this i-max i-max))
  ([this i-max j-max]
    (prime-grid this i-max i-max common/default-operation))
  ([this i-max j-max op]
    (common/prime-grid this n-primes i-max j-max op)))

(defn print-prime-grid
  ([this i-max]
    (print-prime-grid this i-max i-max))
  ([this i-max j-max]
    (print-prime-grid this i-max i-max common/default-operation))
  ([this i-max j-max op]
    (common/print-prime-grid this n-primes i-max j-max op)))

(def behaviour {
  :n-primes n-primes
  :prime-grid prime-grid
  :print-prime-grid print-prime-grid})

(def create ->PrimeDivisorsTrial)
