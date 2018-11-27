(ns preiistmmo.tests.core
  (:require
    [clojure.test :refer :all]
    [preiistmmo.core :as preiistmmo]
    [preiistmmo.tests.util :as util]))

;; Note that these tests are the same exact things as the tests for each impl;
;; the difference here is that the tests below are exercising the public API
;; for the prime generation.

(deftest divisors-n-primes
  (let [algo (preiistmmo/select-algo :divisors)]
    (is (= util/first-100
           (preiistmmo/n-primes algo 100)))
    (is (= util/first-1000
           (preiistmmo/n-primes algo 1000)))
    (is (= util/first-10000
           (preiistmmo/n-primes algo 10000)))))

(deftest eratosthenes-n-primes
  (let [algo (preiistmmo/select-algo :eratosthenes)]
    (is (= util/first-100
           (preiistmmo/n-primes algo 100)))
    (is (= util/first-1000
           (preiistmmo/n-primes algo 1000)))
    (is (= util/first-10000
           (preiistmmo/n-primes algo 10000)))))

(deftest sundaram-n-primes
  (let [algo (preiistmmo/select-algo :sundaram)]
    (is (= util/first-100
           (preiistmmo/n-primes algo 100)))
    (is (= util/first-1000
           (preiistmmo/n-primes algo 1000)))
    (is (= util/first-10000
           (preiistmmo/n-primes algo 10000)))))
