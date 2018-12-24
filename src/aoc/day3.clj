(ns aoc.day3
  (:require [clojure.string :as str]
            [clojure.set :refer [intersection]]))

(defrecord Claim [id xpos ypos width height])

(defn s->claim [[id x y w h]]
  (->Claim id x y w h))

(def input (->> (slurp "resources/day-3-input.txt")
                (str/split-lines)
                (map #(re-seq #"\d+" %))
                (map (fn [s] (map #(Integer/parseInt %) s)))
                (map s->claim)))

; Part One
(defn calc-tiles [claim]
  (for [x (range (:xpos claim) (+ (:xpos claim) (:width claim)))
        y (range (:ypos claim) (+ (:ypos claim) (:height claim)))]
    [x y]))

(defn overlapped-tiles [claims]
  (->> claims
       (mapcat calc-tiles)
       (frequencies)
       (filter #(> (second %) 1))
       (map #(first %))))

(defn count-overlap [tiles]
  (->> (overlapped-tiles tiles)
       (count)))

; Part Two
(defn find-non-overlap-claim [claims]
  (let [overlapped-tiles (overlapped-tiles claims)]
    (some (fn [claim]
            (let [tiles (calc-tiles claim)]
              (when (empty? (intersection (set tiles) (set overlapped-tiles)))
                (:id claim))))
          claims)))
