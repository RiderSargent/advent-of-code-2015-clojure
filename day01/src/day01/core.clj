(ns day01.core)

(def input (slurp "input"))

(defn part-1 [input]
  (reduce + (map {\( 1 \) -1} input)))

(defn part-2 [input]
  (->> input
       (map {\( 1 \) -1})
       (reductions +)
       (take-while #(<= -1 %))
       (count)))

(defn main []
  (println "Part 1:" (part-1 input))
  (println "Part 2:" (part-2 input)))
