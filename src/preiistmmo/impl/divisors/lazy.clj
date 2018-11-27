(ns preiistmmo.impl.divisors.lazy
  (:require
    [preiistmmo.util :as util]))

(defrecord PrimeDivisorsTrial [])

(defn n-primes
  "Use the `util.prime?` function to get a lazy sequence of the first `n` prime
  numbers."
  ([this prime-count]
    (take prime-count (filter util/prime? (range))))
  ([this prime-count start]
    (take prime-count (filter util/prime? (iterate inc start)))))

(def behaviour {
  :n-primes n-primes})

(def create ->PrimeDivisorsTrial)
