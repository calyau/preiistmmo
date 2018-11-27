(ns preiistmmo.tests.impl.divisors.lazy
  (:require
    [clojure.test :refer :all]
    [preiistmmo.impl.divisors.lazy :as divisors-lazy]
    [preiistmmo.tests.util :as util]))

(def algo (divisors-lazy/create))

(deftest n-primes
  (is (= util/first-100
         (divisors-lazy/n-primes algo 100)))
  (is (= util/first-1000
         (divisors-lazy/n-primes algo 1000)))
  (is (= util/first-10000
         (divisors-lazy/n-primes algo 10000))))
