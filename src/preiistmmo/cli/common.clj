(ns preiistmmo.cli.common
  (:require
    [clojure.string :as string]))

(defn help
  [parsed-opts]
  (format "\nOptions:\n\n%s\n"
          (:summary parsed-opts)))

(defn error
  [parsed-opts]
  (format "\nError: %s\n%s"
    (string/join "; " (:errors parsed-opts))
    (help parsed-opts)))
