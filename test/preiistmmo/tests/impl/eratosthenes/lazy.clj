(ns preiistmmo.tests.impl.eratosthenes.lazy
  (:require
    [clojure.test :refer :all]
    [preiistmmo.impl.eratosthenes.lazy :as eratosthenes-lazy]
    [preiistmmo.tests.util :as util]))

(def algo (eratosthenes-lazy/create))

(deftest n-primes
  (is (= util/first-100
         (eratosthenes-lazy/n-primes algo 100)))
  (is (= util/first-1000
         (eratosthenes-lazy/n-primes algo 1000)))
  (is (= util/first-10000
         (eratosthenes-lazy/n-primes algo 10000))))
