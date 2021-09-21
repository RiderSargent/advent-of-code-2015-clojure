(ns day03.core-test
  (:require [clojure.test :refer [deftest is]]
            [day03.core :refer [part-1 part-2]]))

(deftest test-1
  (is (= 2 (part-1 ">"))))

(deftest test-2
  (is (= 4 (part-1 "^>v<"))))

(deftest test-3
  (is (= 2 (part-1 "^v^v^v^v^v"))))

(deftest test-4
  (is (= 3 (part-2 "^v"))))

(deftest test-5
  (is (= 3 (part-2 "^>v<"))))

(deftest test-6
  (is (= 11 (part-2 "^v^v^v^v^v"))))
