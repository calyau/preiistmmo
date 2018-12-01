(ns preiistmmo.impl.eratosthenes.lazy
  (:require
    [preiistmmo.impl.common :as common]))

(defn primes-seq
  "Generates an lazy sequence of prime numbers, gaining a modicum of
  efficiency by tracking known factors of numbers in a hash-map.

  Please note: this is not original work! This code is a modified form
  of what was found here:
   * https://stackoverflow.com/a/7625207
   * https://web.archive.org/web/20150710134640/http://diditwith.net/2009/01/20/YAPESProblemSevenPart2.aspx"
  []
  (letfn [(reinsert [table number prime]
            (update-in table [(+ prime number)] conj prime))
          (primes-step [table number]
            (if-let [factors (get table number)]
              (recur (reduce #(reinsert %1 number %2)
                             (dissoc table number)
                             factors)
                     (inc number))
              (lazy-seq
                (cons number
                      (primes-step
                        (assoc table (* number number) (list number))
                        (inc number))))))]
    (primes-step {} 2)))

(defrecord EratosthenesSieve [])

(defn n-primes
  "Use the implementation of the Sieve of Eratosthenes to get the first `n`
  prime numbers."
  ([this prime-count]
    (take prime-count (primes-seq)))
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

(def create ->EratosthenesSieve)
