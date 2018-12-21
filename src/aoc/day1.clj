(ns aoc.day1
  (:require [clojure.string :as str]))

; Part one
(def input
  (->> (str/split-lines (slurp "resources/day-1-input.txt"))
       (map #(Long/parseLong %))))

(defn calculate-frequency [frequencies]
  (reduce + frequencies))

; Part two
(defn duplicate-frequency [frequencies]
  (loop [sum 0
         ancestors #{0}
         [current & numbers] (cycle frequencies)]
    (let [newSum (+ sum current)]
      (if (contains? ancestors newSum)
        newSum
        (recur newSum
               (conj ancestors newSum)
               numbers)))))