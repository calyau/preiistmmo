(ns preiistmmo.impl.common
  (:require
    [clojure.pprint :as pprint]
    [preiistmmo.util :as util]))

(defn prime-grid
  ([this n-primes i-max]
    (prime-grid this i-max i-max))
  ([this n-primes i-max j-max]
    (let [header (concat [" "] (n-primes this j-max))]
      {:header header
       :body (->> (for [i (n-primes this i-max)
                        j header]
                    [j (if (string? j) i (* i j))])
                   (partition (count header))
                   (reduce conj [])
                   (map #(into {} %)))})))

(defn print-prime-grid
  ([this n-primes i-max]
    (print-prime-grid this n-primes i-max i-max))
  ([this n-primes i-max j-max]
    (let [grid (prime-grid this n-primes i-max j-max)]
      (pprint/print-table
        (:header grid)
        (:body grid))
      :ok)))
