(defproject preiistmmo "0.1.0-SNAPSHOT"
  :description "Pondering scalability via primes ..."
  :url "https://github.com/calyau/preiistmmo"
  :license {
    :name "Apache License, Version 2.0"
    :url "http://www.apache.org/licenses/LICENSE-2.0"}
  :dependencies [
    [org.clojure/clojure "1.10.0-RC2"]]
  :plugins [
    [lein-ltest "0.3.0"]]
  :repl-options {
    :init-ns preiistmmo.core}
  :main preiistmmo.core
  :aot [preiistmmo.core])
