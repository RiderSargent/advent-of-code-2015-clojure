(ns day05.core-test
  (:require [clojure.test :refer [deftest is]]
            [day05.core :refer [part-1 part-2]]))

(deftest test-1
  (is (= 1 (part-1 ["ugknbfddgicrmopn"]))))

(deftest test-2
  (is (= 1 (part-1 ["aaa"]))))

(deftest test-3
  (is (= 0 (part-1 ["jchzalrnumimnmhp"]))))

(deftest test-4
  (is (= 0 (part-1 ["haegwjzuvuyypxyu"]))))

(deftest test-5
  (is (= 0 (part-1 ["dvszwmarrgswjxmb"]))))

(deftest test-6
  (is (= 1 (part-2 ["qjhvhtzxzqqjkmpb"]))))

(deftest test-7
  (is (= 1 (part-2 ["xxyxx"]))))

(deftest test-8
  (is (= 0 (part-2 ["uurcxstgmygtbstg"]))))

(deftest test-9
  (is (= 0 (part-2 ["ieodomkazucvgmuy"]))))
