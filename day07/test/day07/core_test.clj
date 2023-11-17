(ns day07.core-test
  (:require [clojure.test :refer [deftest is are]]
            [day07.core :refer [target-and-expr-for signals-for]]))

(def test-input
  ["123 -> x"
   "456 -> y"
   "x AND y -> d"
   "x OR y -> e"
   "x LSHIFT 2 -> f"
   "y RSHIFT 2 -> g"
   "NOT x -> h"
   "NOT y -> i"])

(deftest test-target-and-expr-for
  (are [x y] (= x y)
    {"x" "123"}        (target-and-expr-for (first test-input))
    {"y" "456"}        (target-and-expr-for (nth test-input 1))
    {"d" "x AND y"}    (target-and-expr-for (nth test-input 2))
    {"e" "x OR y"}     (target-and-expr-for (nth test-input 3))
    {"f" "x LSHIFT 2"} (target-and-expr-for (nth test-input 4))
    {"g" "y RSHIFT 2"} (target-and-expr-for (nth test-input 5))
    {"h" "NOT x"}      (target-and-expr-for (nth test-input 6))
    {"i" "NOT y"}      (target-and-expr-for (nth test-input 7))))

(deftest test-maps
  (let [expected {"d" 72
                  "e" 507
                  "f" 492
                  "g" 114
                  "h" 65412
                  "i" 65079
                  "x" 123
                  "y" 456}]
    (is (= expected (signals-for test-input)))))
