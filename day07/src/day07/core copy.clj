(ns day07.core
  (:require [clojure.string :as str]))

(def input
  (->> "input"
       slurp
       str/split-lines))


(comment
  (def test-results
    {"d" 72
     "e" 507
     "f" 492
     "g" 114
     "h" 65412
     "i" 65079
     "x" 123
     "y" 456})

  (def test-input
    (->> ["123 -> x"
          "456 -> y"
          "x AND y -> d"
          "x OR y -> e"
          "x LSHIFT 2 -> f"
          "y RSHIFT 2 -> g"
          "NOT x -> h"
          "NOT y -> i"]
         (map #(str/split % #" "))))

  (defn my-not [n]
    (let [bin-str (Integer/toBinaryString n)
          pad-size (- 16 (count bin-str))]
      (as-> (repeat \0) c
        (take pad-size c)
        (conj (seq bin-str) c)
        (flatten c)
        (map #(if (= \0 %) 1 0) c)
        (str/join c)
        (Integer/parseInt c 2))))

  (defn process-line [input results]
    (println (first input))
    (if (= 0 (count input))
      results
      (let [current-line (first input)
            wire (last current-line)
            value (condp = (count current-line)
                    3 (Integer/parseInt (first current-line))
                    4 (my-not (get results (nth current-line 1)))
                    5 (let [op (nth current-line 1)
                            a (get results (first current-line))
                            b (nth current-line 2)]
                        (case op
                          "AND" (bit-and a (get results b))
                          "OR" (bit-or a (get results b))
                          "LSHIFT" (bit-shift-left a (Integer/parseInt b))
                          "RSHIFT" (bit-shift-right a (Integer/parseInt b)))))]
        (process-line (rest input) (assoc results wire value)))))


  (Integer/toBinaryString 3)
  (Integer/parseInt "1101" 2)



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;



  (def ops
    {:and bit-and
     :or bit-or
     :lshift bit-shift-left
     :rshift bit-shift-right
     :not bit-not})

  ;; op & args -> {:op op :args args}
  (defn to-map [op & args]
    {:op op
     :args args})

  ;; "123" -> {:constant 123}
  ;; val   -> {:var val}
  (defn var-or-constant [val]
    (let [op (if (re-matches #"\d+" val) :constant :var)]
      (to-map
       op
       (if (= op :constant) (Integer/parseInt val) val))))

  (def eval-op
    (memoize (fn [network {:keys [op args]}]
               (condp = op
                 :constant (first args)
                 :var (eval-op network (get network (first args)))
                 (apply (get ops op) (map (partial eval-op network) args))))))

  (defn run []
    (let [instructions ["123 -> x"
                        "456 -> y"
                        "x AND y -> d"
                        "x OR y -> e"
                        "x LSHIFT 2 -> f"
                        "y RSHIFT 2 -> g"
                        "NOT x -> h"
                        "NOT y -> i"]
          state (atom {})]

      (doseq [instruction instructions]
        (let [[_ expr target] (re-matches #"(.*) -> ([a-z]+)" instruction)]
          (swap! state assoc target
                 (condp re-matches expr
                   ;; "123" -> {:constant 123}
                   ;; "val"   -> {:var "val"}
                   #"(\w+)" :>> (fn [[_ val]] (var-or-constant val))

                   ;; "x LSHIFT 2 -> f" ->
                   ;; {:op :lshift :args '({:op :var :args x} {:op :var 2})}
                   #"([a-z]+) (RSHIFT|LSHIFT) (\d+)" :>> (fn [[_ sym-shift op shift-by]]
                                                           (to-map (if (= op "RSHIFT") :rshift :lshift)
                                                                   (to-map :var sym-shift)
                                                                   (var-or-constant shift-by)))

                   ;; "x AND y" ->
                   ;; {:op :and, :args ({:op :var, :args (x)} {:op :var, :args (y)})}
                   #"(\w+) (AND|OR) (\w+)" :>> (fn [[_ lhs op rhs]]
                                                 (to-map (if (= op "AND") :and :or)
                                                         (var-or-constant lhs)
                                                         (var-or-constant rhs)))

                   ;; "NOT x" ->
                   ;; {:op :not, :args ({:op :var, :args (x)})}
                   #"NOT ([a-z]+)" :>> (fn [[_ sym]] (to-map :not (var-or-constant sym)))))
          (println @state)))

    ;; part 1
      (eval-op @state {:op :var :args ["x"]}))))
