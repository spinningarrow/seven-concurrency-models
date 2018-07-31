(max 3 5)

(+ 1 (* 2 3))

(def meaning-of-life 42)

(if (< meaning-of-life 0)
  "negative"
  "non-negative")

(def droids ["Huey" "Dewey" "Louie"])

(count droids)

(droids 0)
(droids 2)

(def me {:name "Paul"
         :age 45
         :sex :male})

(:age me)
(me :age)

(defn percentage
  [x p]
  (* x (/ p 100.0)))

(percentage 200 10)
