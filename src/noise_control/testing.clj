;; Anything you type in here will be executed 
;; immediately with the results shown on the 
;; right.

(def vect [0 1 2 3 4])

(defn fun [vect]
  print vect)

(fun vect)

(filter (partial < 2) vect)

(import '(clojure.string))
;(doall (map (partial re-find #"T.t.") (clojure.string/split "This is a sentence." #"")))

(def str "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.!?,;:&@[]{}$%<>\\~'")