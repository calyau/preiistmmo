(ns preiistmmo.tests.util
  "This namespace is both a utiity ns that defines functions for use by
  various tests as well as a ns for tests of the `preiistmmo.util` ns."
  (:require
    [clojure.java.io :as io]
    [clojure.test :refer :all]
    [preiistmmo.util :as util]))

(defn read-prime-file
  [filename]
  (->> filename
       io/resource
       io/reader
       line-seq
       rest
       rest
       (map #(Integer/parseInt %))))

(def first-100 (read-prime-file "first-100.txt"))
(def first-1000 (read-prime-file "first-1000.txt"))
(def first-10000 (read-prime-file "first-10000.txt"))

(deftest divisible?
  (is (util/divisible? 10 1))
  (is (util/divisible? 10 2))
  (is (util/divisible? 10 5))
  (is (util/divisible? 10 10))
  (is (not (util/divisible? 10 3)))
  (is (not (util/divisible? 10 7)))
  (is (not (util/divisible? 10 11))))

(deftest prime?
  (is (not (util/prime? 0)))
  (is (not (util/prime? 1)))
  (is (not (util/prime? 4)))
  (is (not (util/prime? 16)))
  (is (not (util/prime? 100)))
  (is (= []
         (filter util/prime? (range (inc 1)))))
  (is (= [2]
         (filter util/prime? (range (inc 2)))))
  (is (= [2 3 5 7 11 13 17 19 23 29]
         (filter util/prime? (range (inc 30)))))
  (is (= [907 911 919 929 937 941 947 953 967 971 977 983 991 997]
         (filter util/prime? (range 900 (inc 1000)))))
  (is (= first-100
         (take 100 (filter util/prime? (range)))))
  (is (= first-1000
         (take 1000 (filter util/prime? (range)))))
  (is (= first-10000
         (take 10000 (filter util/prime? (range))))))
