(def counts {"apple" 2
             "orange" 1})

(get counts "apple" 0)
(get counts "banana" 0)

(assoc counts "banana" 1)
(assoc counts "apple" 3)

(defn word-frequencies
  [words]
  (reduce
    (fn [counts word] (assoc counts word (inc (get counts word 0))))
    {}
    words))

(def words ["one"
            "potato"
            "two"
            "potato"
            "three"
            "potato"
            "four"])

(word-frequencies words)
(frequencies words)

(map inc [0 1 2 3 4 5])

(map (fn [x] (* 2 x)) [0 1 2 3 4 5])

(def multiply-by-2 (partial * 2))

(multiply-by-2 3)

(map (partial * 2) [0 1 2 3 4 5])
(map multiply-by-2 [0 1 2 3 4 5])

(defn text->words
  [text]
  (re-seq #"\w+" text))

(text->words "one two three four")

(def sentences ["one two three"
                "four five six"
                "seven eight nine"])

(map text->words sentences)

(mapcat text->words sentences)

(->> sentences
     (mapcat text->words)
     frequencies)

(defn count-words-sequential
  [pages]
  (frequencies (mapcat text->words pages)))

(count-words-sequential sentences)

(merge-with + {:a 1 :b 2} {:a 3 :b 4})
