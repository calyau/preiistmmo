(ns preiistmmo.cli.common
  (:require
    [clojure.string :as string]
    [preiistmmo.core :as preiistmmo]))

(defn help
  [parsed-opts]
  (format "\nOptions:\n\n%s\n"
          (:summary parsed-opts)))

(defn error
  [parsed-opts]
  (format "\nError: %s\n%s"
    (string/join "; " (:errors parsed-opts))
    (help parsed-opts)))

(def supported-algorithms
  (->> preiistmmo/algos
       keys
       (map name)
       (into #{})))

(def cli-opts
  [["-s" "--start INTEGER" (str "The starting integer (only valid for the "
                        "'divisors' algorithm)")
    :default 2
    :parse-fn #(Integer/parseInt %)
    :validate [int? "Must be an integer"]]
   ["-a" "--algorithm NAME" (format (str "The prime number generating "
                                    "algorithm to use (one of %s)")
                               (vec supported-algorithms))
    :default "divisors"
    :validate [#(contains? supported-algorithms %)
               (format "Must be one of %s" supported-algorithms)]]
   ["-h" "--help" "This usage text."]])
