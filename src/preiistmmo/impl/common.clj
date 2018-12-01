(ns preiistmmo.impl.common
  (:require
    [clojure.pprint :as pprint]
    [preiistmmo.util :as util]))

(def default-start 2)

(def operations
  {"+" +
   "-" -
   "*" *
   "/" /
   "%" mod})

(def default-operation "*")

(defn prime-grid
  ([this n-primes i-max]
    (prime-grid this i-max i-max))
  ([this n-primes i-max j-max]
    (prime-grid this n-primes i-max j-max default-start))
  ([this n-primes i-max j-max start]
    (prime-grid this n-primes i-max j-max start default-operation))
  ([this n-primes i-max j-max start op]
    (let [header (concat [op] (n-primes this j-max start))
          op-fn (get operations op)]
      {:header header
       :body (->> (for [i (n-primes this i-max start)
                        j header]
                    [j (if (string? j)
                         i
                         (op-fn i j))])
                   (partition (count header))
                   (reduce conj [])
                   (map #(into {} %)))})))

(defn print-prime-grid
  ([this n-primes i-max]
    (print-prime-grid this n-primes i-max i-max))
  ([this n-primes i-max j-max]
    (print-prime-grid this n-primes i-max i-max default-start))
  ([this n-primes i-max j-max start]
    (print-prime-grid this n-primes i-max i-max start default-operation))
  ([this n-primes i-max j-max start op]
    (let [grid (prime-grid this n-primes i-max j-max start op)]
      (pprint/print-table
        (:header grid)
        (:body grid))
      :ok)))
