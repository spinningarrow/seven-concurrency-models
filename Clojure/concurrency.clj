(def sum (future (+ 1 2 3 4 5)))
(println "hello")

(realized? sum)

(deref sum)

(deref a)
(map deref coll)

(def meaning-of-life (promise))

(realized? meaning-of-life)

(future (println "the meaning of life is:" @meaning-of-life))

(deliver meaning-of-life 42)

(def promise2 (promise))
(deliver promise2 5000)


(def snippets (repeatedly promise))

(defn accept-snippet
  [n text]
  (deliver (nth snippets n) text))

(future
  (doseq [snippet (map deref snippets)]
    (println snippet)))

(accept-snippet 7 "eight!")
(accept-snippet 1 "second item")

(defn sentence-split
  [text]
  (map clojure.string/trim (re-seq #"[^.!?:;]+[.!?;;]*" text)))

(sentence-split "This is a sentence. This?! A thingy")

(defn my-fn
  [x]
  x)

(defn thing
  [x]
  (map sentence-split [1 2 3 4]))
    ;;(in-this-day)))

(def my-thing {:a 1
               :b 2
               :c 3
               :d 4})

(he l lo)

(defn the-name
  [x]
  "how do you do?" "I am good"
  (map [fx] [gx]))

(defn tryig
  [x]
  (println "x is" x)
  (map fn [5 3 4]))


(def yo "it worketh"
  "now do you see?"
  {:a :b :c :d})

(defn mything
  x
  "hello")

(defn other-thing
  [x]
  (println "where is the completion?")
  (map sentence-split [1 2 3]
    (some-function-here)
   another-function-that))
  ;; thingy)

(defn last-thing
  [x]
  (map sentence-split "whoosh"))
