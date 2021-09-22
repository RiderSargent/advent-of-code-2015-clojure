(ns day04.core
  (:require [clojure.string :as str])
  (:require [digest :refer [md5]]))

(def input "yzbqklnj")

(defn part-1 [input]
  (count
   (take-while #(not (str/starts-with? % "00000"))
               (map #(md5 (str input %)) (iterate inc 0)))))

(defn part-2 [input]
  (count
   (take-while #(not (str/starts-with? % "000000"))
               (map #(md5 (str input %)) (iterate inc 0)))))

(defn main []
  (println "Part 1:" (part-1 input))
  (println "Part 2:" (part-2 input)))
