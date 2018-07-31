(def trees [nil
            [nil 4 nil]
            [[nil 3 nil] 4 nil]
            [nil 4 [nil 5 nil]]
            [[nil 3 nil] 4 [nil 5 nil]]])

(defn insert
  [tree value]
  (let [[left root right] tree]
    (cond (nil? tree) [nil value nil]
          (< value root) [(insert left value) root right]
          :else [left root (insert right value)])))

(defn has?
  [tree value]
  (let [[left root right] tree]
    (cond (nil? tree) false
          (= value root) true
          (< value root) (has? left value)
          :else (has? right value))))

(defn create
  [values]
  (reduce insert nil values))

(defn min-value
  [tree]
  (let [[left root _] tree]
    (cond (nil? tree) nil
          (nil? left) root
          :else (recur left))))

(defn max-value
  [tree]
  (let [[_ root right] tree]
    (cond (nil? tree) nil
          (nil? right) root
          :else (recur right))))
