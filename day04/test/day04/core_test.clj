(ns day04.core-test
  (:require [clojure.test :refer [deftest is]]
            [day04.core :refer [part-1]]))

(deftest test-1
  (is (= 609043 (part-1 "abcdef"))))

(deftest test-2
  (is (= 1048970 (part-1 "pqrstuv"))))
