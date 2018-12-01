(ns preiistmmo.cli.bench
  (:require
    [preiistmmo.core :as preiistmmo])
  (:gen-class))

(defn runit
  [alg primes-count]
  (print (format "%s primes: " primes-count))
  (time (doall (preiistmmo/n-primes alg primes-count))))

(defn mark
  ""
  []
  (let [divs (preiistmmo/select-algo :divisors)
        apache (preiistmmo/select-algo :apache)
        eratos (preiistmmo/select-algo :eratosthenes)
        sund (preiistmmo/select-algo :sundaram)]
    (println "\nRunning n-primes for :divisors implementation ...")
    (runit divs 10)
    (runit divs 100)
    (runit divs 1000)
    (runit divs 10000)
    (runit divs 100000)
    (println "\nRunning n-primes for :apache implementation ...")
    (runit divs 10)
    (runit divs 100)
    (runit divs 1000)
    (runit divs 10000)
    (runit divs 100000)
    (println "\nRunning n-primes for :eratosthenes implementation ...")
    (runit eratos 10)
    (runit eratos 100)
    (runit eratos 1000)
    (runit eratos 10000)
    (runit eratos 100000)
    (println "\nRunning n-primes for :sundaram implementation ...")
    (runit sund 10)
    (runit sund 100)
    (runit sund 1000)
    (runit sund 10000)
    (runit sund 100000)
    (println)))

(defn -main
  ""
  [& args]
  (mark))
