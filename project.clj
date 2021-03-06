(defn get-prompt
  [ns]
  (str "\u001B[35m[\u001B[34m"
       ns
       "\u001B[35m]\u001B[33m λ\u001B[m=> "))

(defproject calyau/preiistmmo "0.2.0-SNAPSHOT"
  :description "Pondering scalability via primes ..."
  :url "https://github.com/calyau/preiistmmo"
  :license {
    :name "Apache License, Version 2.0"
    :url "http://www.apache.org/licenses/LICENSE-2.0"}
  :dependencies [
    [org.apache.commons/commons-math3 "3.6.1"]
    [org.clojure/clojure "1.10.0-RC2"]
    [org.clojure/tools.cli "0.4.1"]]
  :repl-options {
    :init-ns preiistmmo.core
    :prompt ~get-prompt}
  :aot [preiistmmo.core]
  :profiles {
    :ubercompile {
      :aot :all
      :source-paths ["test"]}
    :lint {
      :source-paths ^:replace ["src"]
      :test-paths ^:replace []
      :plugins [
        [jonase/eastwood "0.3.3"]
        [lein-ancient "0.6.15"]
        [lein-kibit "0.1.6"]]}
    :test {
      :dependencies [
        [clojusc/ltest "0.3.0"]]
      :plugins [
        [lein-ltest "0.3.0"]]
      :test-selectors {
        :unit #(not (or (:integration %) (:system %)))
        :integration :integration
        :system :system
        :default (complement :system)}}
    :cli-benchmarks {
      :main preiistmmo.cli.bench}
    :cli-preiistmmo {
      :main preiistmmo.cli.core}
    :cli-primes {
      :main preiistmmo.cli.primes}
    :cli-table {
      :main preiistmmo.cli.table}}
  :aliases {
    ;; Dev & Testing Aliases
    "repl" ["do"
      ["clean"]
      ["repl"]]
    "ubercompile" ["with-profile" "+ubercompile" "compile"]
    "check-vers" ["with-profile" "+lint" "ancient" "check" ":all"]
    "check-jars" ["with-profile" "+lint" "do"
      ["deps" ":tree"]
      ["deps" ":plugin-tree"]]
    "check-deps" ["do"
      ["check-jars"]
      ["check-vers"]]
    "ltest" ["with-profile" "+test" "ltest"]
    ;; Linting
    "kibit" ["with-profile" "+lint" "kibit"]
    "eastwood" ["with-profile" "+lint" "eastwood" "{:namespaces [:source-paths]}"]
    "lint" ["do"
      ["kibit"]
      ["eastwood"]]
    ;; Build tasks
    "build" ["do"
      ["clean"]
      ["check-vers"]
      ["lint"]
      ["ltest" ":unit"]
      ["clean"]
      ["ubercompile"]
      ["uberjar"]]
    ;; CLI
    "preiistmmo" ["with-profile" "+cli-preiistmmo" "trampoline"]
    "primes" ["with-profile" "+cli-primes" "trampoline"]
    "table" ["with-profile" "+cli-table" "trampoline"]
    "benchmarks" ["with-profile" "+cli-benchmarks" "trampoline"]})
