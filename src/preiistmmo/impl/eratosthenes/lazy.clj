(ns preiistmmo.impl.eratosthenes.lazy)

(defn primes-seq
  "Generates an lazy sequence of prime numbers, gaining a modicum of
  efficiency by tracking known factors of numbers in a hash-map.

  Modified from:
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
  [this prime-count]
  (take prime-count (primes-seq)))

(def behaviour {
  :n-primes n-primes})

(def create #(->EratosthenesSieve))
