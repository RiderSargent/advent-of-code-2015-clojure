(ns day03.core
  (:require [clojure.set :refer [union]]))

(def input (slurp "input"))

(defn next-house [[x y] move]
  (cond
    (= move \>) [x (+ y 1)]
    (= move \v) [(- x 1) y]
    (= move \<) [x (- y 1)]
    (= move \^) [(+ x 1) y]))

(defn visited-houses [moves]
  (->> moves
       (reductions next-house [0 0])
       set))

(defn part-1 [input]
  (count (visited-houses input)))

(defn part-2 [input]
  (let [paired-moves (partition 2 input)
        robot-moves (map first paired-moves)
        santa-moves (map second paired-moves)]
    (count (union (visited-houses robot-moves)
                  (visited-houses santa-moves)))))

(defn main []
  (println "Part 1:" (part-1 input))
  (println "Part 2:" (part-2 input)))
