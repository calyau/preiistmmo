(ns preiistmmo.cli.core
  "This use of this name space allows one to provide more CLI context, a the
  expense of more typing. For instance, typing this:
  ```
    $ lein primes run
  ```
  or
  ```
    $ lein table run
  ```
  is ambigous, given the ecosystem of lein plugins and the possibility of
  colliding with something a user has on their machine (e.g., in
  ~/.lein/profiles.clj`).

  As an alternative, this ns allows one to keep things distinct:
  ```
    $ lein preiistmmo run primes
  ```
  or
  ```
    $ lein preiistmmo run table
    $ lein preiistmmo run table --rows 8 --columns 4 --operation -
    $ lein preiistmmo run benchmarks
    $ ... etc.
  ```"
  (:require
    [preiistmmo.cli.bench :as bench]
    [preiistmmo.cli.primes :as primes]
    [preiistmmo.cli.table :as table])
  (:gen-class))

(defn -main
  [dispatch & args]
  (case (keyword dispatch)
    :primes (apply primes/-main args)
    :table (apply table/-main args)
    :benchmarks (apply bench/-main args)))
