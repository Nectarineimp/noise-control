;; Noise Control
;; Works with analytics-engine
;; 

(ns noise-control
  (:require [clojure.string :as str :refer (join)]
            [clojure.pprint :refer (pprint)])
  (:import (edu.stanford.nlp.pipeline StanfordCoreNLP
                                      Annotation)
           (edu.stanford.nlp.ling CoreAnnotations$SentencesAnnotation
                                  CoreAnnotations$NamedEntityTagAnnotation)
           edu.stanford.nlp.trees.tregex.TregexPattern
           edu.stanford.nlp.trees.tregex.tsurgeon.Tsurgeon
           edu.stanford.nlp.trees.TreeCoreAnnotations$TreeAnnotation)
  (:import [java.util.ArrayList])
  (:import [com.cybozu.labs.langdetect Detector DetectorFactory Language]))

(def ^:private props
  (doto (java.util.Properties.)
    (.put "annotators" "tokenize, ssplit, pos, lemma, ner, parse")
    (.put "parse.maxlen" (str (-> config :nlp :max-sentence-length)))))

(def ^:private pipeline (StanfordCoreNLP. props))

(defn- annotated-doc [s]
  (.process pipeline s))

(defn parse-sentences [s]
  (-> s
      annotated-doc
      (.get CoreAnnotations$SentencesAnnotation)
      vec))

(defonce detector-factory (DetectorFactory/loadProfile "/home/petermancini/language-detection/profiles"))

(defn detect-language
  [s]
  (let [detector (DetectorFactory/create)]
    (.append detector s)
    (.detect detector )))

(defn english?
  [s]
  (= (detect-language s) "en"))

(defn filter-english-only [sentences]
  (filter english? sentences))

(defn sanitize-string [s] (clojure.string/replace (apply str (map (fn [c]
         (if (some #{c} '(\a \b \c \d \e \f \g \h \i \j \k \l \m \n \o \p \q \r \s \t \u \v \w \x \y \z 
                          \A \B \C \D \E \F \G \H \I \J \K \L \M \N \O \P \Q \R \S \T \U \V \W \X \Y \Z
                          \0 \1 \2 \3 \4 \5 \6 \7 \8 \9
                          \. \! \? \, \; \: \& \@ \[ \] \{ \} \$ \% \< \> \\ \~ \' \space))
           c
           \space))
       s)) #"  +" " "))