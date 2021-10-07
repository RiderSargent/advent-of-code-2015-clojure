(ns day06.core
  (:require [clojure.string :as str]))

(def raw-input (str/split-lines (slurp "input")))

(def extract-values
  (comp
   rest
   first
   #(re-seq #"(toggle|turn on|turn off) (\d+),(\d+) through (\d+),(\d+)" %)))

(def input
  (map extract-values raw-input))

(def initial-grid (vec (repeat 1000 (vec (repeat 1000 0)))))

(defn get-coords-from-line [line]
  (let [[_ x1 y1 x2 y2] line
        [x1 y1 x2 y2] (map #(Integer/parseInt %) [x1 y1 x2 y2])]
    (for [x (range x1 (inc x2)) y (range y1 (inc y2))]
      [x y])))

(defn process-coord [op grid [x y]]
  (let [current (get-in grid [x y])
        value (cond
                (= "turn on" op) 1
                (= "turn off" op) 0
                (= "toggle" op) (mod (+ 1 current) 2))]
    (assoc-in grid [x y] value)))

(defn process-instruction [grid line]
  (let [op (first line)
        coords (get-coords-from-line line)
        f (partial process-coord op)]
    (reduce f grid coords)))

(defn process-coord-2 [op grid [x y]]
  (let [current (get-in grid [x y])
        value (cond
                (= "turn on" op) (+ 1 current)
                (= "turn off" op) (max 0 (- current 1))
                (= "toggle" op) (+ 2 current))]
    (assoc-in grid [x y] value)))

(defn process-instruction-2 [grid line]
  (let [op (first line)
        coords (get-coords-from-line line)
        f (partial process-coord-2 op)]
    (reduce f grid coords)))

(defn part-1 [input]
  (->> input
       (reduce process-instruction initial-grid)
       (map #(reduce + %))
       (reduce +)))

(defn part-2 [input]
  (->> input
       (reduce process-instruction-2 initial-grid)
       (map #(reduce + %))
       (reduce +)))

(defn main []
  (println "Part 1:" (part-1 input))
  (println "Part 2:" (part-2 input)))
