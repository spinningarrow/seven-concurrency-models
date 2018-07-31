(defn recursive-sum
  [numbers]
  (if (empty? numbers)
    0
    (+ (first numbers) (recursive-sum (rest numbers)))))

(recursive-sum [1 2 3 4 5])

(defn reduce-sum
  [numbers]
  (reduce (fn [acc x] (+ acc x)) 0 numbers))

(reduce-sum [1 2 3 4 5])

(defn sum
  [numbers]
  (reduce + numbers))

(sum [1 2 3 4 5])

;; Effortless parallelism

(defn parallel-sum
  [numbers]
  (clojure.core.reducers/fold + numbers))

(def numbers (into [] (range 0 10000000)))
(time (sum numbers))
(time (parallel-sum numbers))
