(ns aoc.day1
  (:require [clojure.string :as str]))

; Part one
(def frequencies
  (map #(Long/parseLong %)
       (str/split-lines (slurp "resources/day-1-input.txt"))))

(defn calculate-frequency
  [frequencies]
  (reduce + frequencies))

; Part two
(defn duplicate-frequency
  [frequencies]
  (loop [sum 0
         ancestors #{0}
         current (first frequencies)
         numbers (rest frequencies)]
    (let [newSum (+ sum current)]
      (if (contains? ancestors newSum)
        newSum
        (recur newSum
               (conj ancestors newSum)
               (first (if (empty? numbers) frequencies numbers))
               (rest (if (empty? numbers) frequencies numbers)))))))