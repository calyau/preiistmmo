(ns preiistmmo.impl.sundaram.simple
  (:require
    [clojure.set :as set]
    [preiistmmo.impl.common :as common]))

(defn desired-primes->n
  "This utility function converts the number of desired primes to the total
  numbers the Sieve of Sundaram expects to work with. This was obtained by
  obtaining known inputs/outputs and curve-fitting a polynomial for
  extrapolation/interpolation. Adjustments were made so that it should always
  over-estimate a bit, thus allowing for fine-tuning by applying `take`."
  [prime-count]
  (+ (* 0.0011 (Math/pow prime-count 2))
     (* 3 prime-count)))

(defrecord SundaramSieve [])

(defn n-primes
  "A naïve implementation of the Sieve of Sundaram.

  This function takes as arguments its implementation record and the number of
  primes to produce.

  Based upon a reading of https://en.wikipedia.org/wiki/Sieve_of_Sundaram."
  ([this prime-count]
    ;; The algorithm is defined as taking not the number of desired primes, but
    ;; rather the total numbers to try; as such, we need a utility function that
    ;; can convert from one to the other.
    (let [n (desired-primes->n prime-count)]
      (->> (for [i (range 1 n)
                 j (range 1 n) :while (<= (+ i j (* 2 i j)) n)]
             (+ i j (* 2 i j)))
           (remove nil?)
           distinct
           ;; In this next call, we loose the benefits of laziness :-(
           (set/difference (set (range 1 n)))
           (map #(inc (* 2 %)))
           sort
           (take (dec prime-count))
           (concat [2]))))
  ([this prime-count _start]
    (n-primes this prime-count)))

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

(def create ->SundaramSieve)
