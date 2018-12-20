(ns aoc.day2
  (:require [clojure.string :as str]))

(def ids (str/split-lines
           (slurp "resources/day-2-input.txt")))

(defn checksum
  [ids-to-check]
  ())