(ns etl-examples.core
  (:require [clojure.data.csv :as csv]
            [clojure.java.io :as io]
            [clojure.set :as s]
            )
  )

(defn make-relation [headers rowseq]
  ; Turn a sequence rows and their headers into a relation (a sequence of maps, values keyed by the headers as keywords)
  ; Interleving the headers wth the row content produces a set of arguments that can be applied to hash-map
  (map #(apply hash-map (interleave %1 %2)) (repeat (map keyword headers)) rowseq))

;; Read a CSV file, detecting headers and mapping rows into maps whose keys are the headers as keywords, values are
;; row values.
(defn read-csv
  [filename sep quote]
  ;; Get a reader on the file
  (with-open [infile (io/reader filename)]
    ;; Pull the contents as a lazy seq of vectors (rows)
    (let [contents (csv/read-csv infile :separator sep :quote quote)
          ;; Extract the header row and the rest of the rows by using juxt to create a vector that can be destructured
          [headers rowseq] ((juxt
                            (comp first (partial take 1))   ; Take the first row (header)
                            (partial drop 1))               ; Drop the header to get the rows themselves
                           contents)]

      ; Now turn the rows into a relation (a sequence of maps, values keyed by the headers).
      ; Interleving the headers wth the row content produces a set of arguments that can be applied to hash-map
      (make-relation headers (doall rowseq)))))




