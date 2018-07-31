;---
; Excerpted from "Seven Concurrency Models in Seven Weeks",
; published by The Pragmatic Bookshelf.
; Copyrights apply to this code. It may not be used to create training material, 
; courses, books, articles, and the like. Contact us if you are in doubt.
; We make no guarantees that this code is fit for any purpose. 
; Visit http://www.pragmaticprogrammer.com/titles/pb7con for more book information.
;---
(ns wordcount.core
  (:require [wordcount.pages :refer :all]
            [wordcount.words :refer :all]))

(def path "/Users/sahil/Downloads/wiki/simplewiki-20170820-pages-meta-current.xml")

(defn count-words-sequential [pages]
  (frequencies (mapcat get-words (remove nil? pages))))

(defn count-words-parallel [pages]
  (reduce (partial merge-with +)
    (pmap #(frequencies (get-words %)) (remove nil? pages))))

(defn count-words [pages]
  (reduce (partial merge-with +)
    (pmap count-words-sequential (partition-all 100 pages))))

(defn -main [& args]
  (time (count-words (take 100000 (get-pages path))))
  (shutdown-agents))

(+ 1 2 3 4)

(def pinky (promise))
(future (println "I promised that I would" @pinky))
(deliver pinky "see you today")