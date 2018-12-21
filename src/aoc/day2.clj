(ns aoc.day2
  (:require [clojure.string :as str]))

(def input (->> (slurp "resources/day-2-input.txt")
                (str/split-lines)))

; Part One
(defn has-n [n s]
  (some #(= n %) (vals (frequencies s))))

(defn count-n [n s]
  (count (filter #(has-n n %) s)))

(defn checksum [ids]
  (* (count-n 2 ids) (count-n 3 ids)))

; Part Two
(defn str-diff [a b]
  (reduce + (map (fn [c1 c2] (if (= c1 c2) 0 1)) a b)))

(defn remove-diff [a b]
  (str/join (map (fn [c1 c2] (if (= c1 c2) c1 "")) a b)))

(defn find-box-pair [ids]
  (->> (for [a ids
             b ids]
         (remove-diff a b))
       (filter #(= (count %) (dec (count (first ids)))))
       (first)))
