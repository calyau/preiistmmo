(ns preiistmmo.tests.impl.sundaram.simple
  (:require
    [clojure.test :refer :all]
    [preiistmmo.impl.sundaram.simple :as sundaram-simple]
    [preiistmmo.tests.util :as util]))

(def algo (sundaram-simple/create))

(deftest n-primes
  (is (= util/first-100
         (sundaram-simple/n-primes algo 100)))
  (is (= util/first-1000
         (sundaram-simple/n-primes algo 1000)))
  (is (= util/first-10000
         (sundaram-simple/n-primes algo 10000))))
