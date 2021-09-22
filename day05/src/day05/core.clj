(ns day05.core
  (:require [clojure.string :as str]))

(def input (str/split-lines (slurp "input")))

(defn has-3-vowels? [s]
  (<= 3 (count (re-seq #"[aeiou]" s))))

(defn has-double-letter? [s]
  (<= 1 (count (re-seq #"([a-zA-Z])\1" s))))

(defn has-no-forbidden? [s]
  (nil? (re-seq #"ab|cd|pq|xy" s)))

(defn has-repeating-pair? [s]
  (not (nil? (re-seq #"([a-zA-Z][a-zA-Z]).*\1" s))))

(defn has-repeating-letter-around-single? [s]
  (not (nil? (re-seq #"([a-zA-Z]).\1" s))))

(defn part-1 [input]
  (->> input
       (map #(and (has-3-vowels? %) (has-double-letter? %) (has-no-forbidden? %)))
       (filter identity)
       count))

(defn part-2 [input]
  (->> input
       (map #(and (has-repeating-pair? %) (has-repeating-letter-around-single? %)))
       (filter identity)
       count))

(defn main []
  (println "Part 1:" (part-1 input))
  (println "Part 2:" (part-2 input)))
