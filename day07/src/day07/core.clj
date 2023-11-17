(ns day07.core
  (:require [clojure.string :as str]))

(def test-input
  ["123 -> x"
   "456 -> y"
   "x AND y -> d"
   "x OR y -> e"
   "x LSHIFT 2 -> f"
   "y RSHIFT 2 -> g"
   "NOT x -> h"
   "NOT y -> i"])

(def input
  (->> "input"
       slurp
       str/split-lines))

(defn parse-line [line]
  (let [[_ expr target] (re-matches #"(.*) -> ([a-z]+)" line)]
    {target (condp re-matches expr
              #"(\d+)"                 :>> (fn [[_ a]] {:op :const :args (list a)})
              #"([a-z]+) AND ([a-z]+)" :>> (fn [[_ a b]] {:op :and :args (list a b)})
              #"([a-z]+) OR ([a-z]+)"  :>> (fn [[_ a b]] {:op :or :args (list a b)})
              #"([a-z]+) LSHIFT (\d+)" :>> (fn [[_ a b]] {:op :lshift :args (list a b)})
              #"([a-z]+) RSHIFT (\d+)" :>> (fn [[_ a b]] {:op :rshift :args (list a b)})
              #"NOT ([a-z]+)"          :>> (fn [[_ a]] {:op :not :args (list a)}))}))
