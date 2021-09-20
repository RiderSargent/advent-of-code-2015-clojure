(ns day02.core
  (:require [clojure.string :as str]))

(def input
  (as-> "input" i
    (slurp i)
    (str/split-lines i)
    (map #(str/split % #"x") i)
    (map (fn [v] (sort (map #(Integer/parseInt %) v))) i)))

(defn paper [[l w h]]
  (+ (* 3 l w) (* 2 w h) (* 2 h l)))

(defn ribbon [[l w h]]
  (+ (+ l l w w) (* l w h)))

(defn part-1 [input]
  (reduce + (map paper input)))

(defn part-2 [input]
  (reduce + (map ribbon input)))

(defn main []
  (println "Part 1:" (part-1 input))
  (println "Part 2:" (part-2 input)))
