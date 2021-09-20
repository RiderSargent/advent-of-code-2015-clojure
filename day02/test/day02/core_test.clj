(ns day02.core-test
  (:require [clojure.test :refer [deftest is]]
            [day02.core :refer [paper ribbon]]))

(deftest test-1
  (is (= 58 (paper '(2 3 4)))))

(deftest test-2
  (is (= 43 (paper '(1 1 10)))))

(deftest test-3
  (is (= 34 (ribbon '(2 3 4)))))

(deftest test-4
  (is (= 14 (ribbon '(1 1 10)))))
