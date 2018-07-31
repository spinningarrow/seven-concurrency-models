(def players #{:x :o})

(def board (atom [[nil nil nil]
                  [nil nil nil]
                  [nil nil nil]]))

(defn moves (interleave (repeat :x) (repeat :o)))

(defn move!
  [board location player]
  {:pre [(nil? (get-in @board location))]}
  (swap! board assoc-in location player))

(defn count-moves
  [board]
  (count (remove nil? (flatten board))))

(defn next-player
  [board moves]
  (nth moves (count-moves board)))

(defn winner
  [[[v00 v01 v02]
    [v10 v11 v12]
    [v20 v21 v22]]]
  (cond
    (= v00 v01 v02) v00
    (= v10 v11 v12) v10
    (= v20 v21 v22) v20
    (= v00 v10 v20) v00
    (= v01 v11 v21) v01
    (= v02 v12 v22) v02
    (= v00 v11 v22) v00
    (= v02 v11 v20) v02
    :else nil))

(defn board-full?
  [board]
  (not-any? nil? (flatten board)))

(defn game-over?
  [board]
  (or (winner board)
      (board-full? board)))

(defn draw?
  [board]
  (and (board-full? board)
       (nil? (winner board))))

(defn render
  [board]
  (println (clojure.string/join "\n" board)))

;; (def game (-> board
;;               (move [0 0] :x)
;;               (move [1 0] :o)
;;               (move [1 1] :x)
;;               (move [0 2] :o)
;;               (move [2 2] :x)))

(defn play
  [board moves location]
  (let [player (next-player @board moves)]
    (move! board location player)))
