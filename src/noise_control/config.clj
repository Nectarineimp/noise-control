(ns noise-control.config
  (:require [clojure.java.io :refer (resource)]))

(defn- config-file-resource []
  (->> ["noise-control/settings.clj"
        "noise-control/settings.sample.clj"]
       (keep resource)
       first))

(def config
  (if-let [r (config-file-resource)]
    (read-string (slurp r))
    (throw (Exception. "No settings file found on classpath"))))
