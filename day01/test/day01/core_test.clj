(ns day01.core-test
  (:require [clojure.test :refer [deftest is]]
            [day01.core :refer [part-1 part-2]]))

(deftest test-1
  (is (= 0 (part-1 "(())"))))

(deftest test-2
  (is (= 0 (part-1 "()()"))))

(deftest test-3
  (is (= 3 (part-1 "((("))))

(deftest test-4
  (is (= 3 (part-1 "(()(()("))))

(deftest test-5
  (is (= 3 (part-1 "))((((("))))

(deftest test-6
  (is (= -1 (part-1 "())"))))

(deftest test-7
  (is (= -1 (part-1 "))("))))

(deftest test-8
  (is (= -3 (part-1 ")))"))))

(deftest test-9
  (is (= -3 (part-1 ")())())"))))

(deftest test-10
  (is (= 1 (part-2 ")"))))

(deftest test-11
  (is (= 5 (part-2 "()())"))))
