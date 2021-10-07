(ns day06.core-test
  (:require [clojure.test :refer [deftest is]]
            [day06.core :refer [extract-values get-coords-from-line]]))

(deftest test-1
  (is (= '("turn off" "17" "42" "24" "111")
         (extract-values "turn off 17,42 through 24,111"))))

(deftest test-2
  (is (= '([1 2] [1 3] [1 4] [2 2] [2 3] [2 4] [3 2] [3 3] [3 4])
         (get-coords-from-line '("turn on" "1" "2" "3" "4")))))
