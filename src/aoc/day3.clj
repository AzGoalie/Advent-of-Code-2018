(ns aoc.day3
  (:require [clojure.string :as str]))

(defrecord Claim [id xpos ypos width height])

(defn list-to-claim [[id x y w h]]
  (->Claim id x y w h))

(def input (->> (slurp "resources/day-3-input.txt")
                (str/split-lines)
                (map #(re-seq #"\d+" %))
                (map list-to-claim)))
