(ns preiistmmo.util)

(defn divisible?
  [a b]
  (zero? (mod a b)))

(defn prime?
  "An implementation of a primality test using the `6k ± 1` optimization.

  Based upon a reading of https://en.wikipedia.org/wiki/Primality_test."
  [number]
  (cond (<= number 3)
        (> number 1)

        (or (divisible? number 2)
            (divisible? number 3))
        false

        :else
        (->> (iterate #(+ % 6) 5)
             (take-while #(<= (* % %) number))
             (not-any? #(or (divisible? number %)
                            (divisible? number (+ % 2)))))))
