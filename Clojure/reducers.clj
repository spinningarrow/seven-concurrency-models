(map (partial * 2) [1 2 3 4])

(require '[clojure.core.reducers :as r])

(def r-map (r/map (partial * 2) [1 2 3 4]))

(reduce conj [] r-map)
(r/fold conj r-map)
(into [] r-map)

(into [] (r/map inc (r/filter even? [1 2 3 4])))

;; (defprotocol CollReduce
;;   (coll-reduce [this f] [this f init]))

(require '[clojure.core.protocols :refer [coll-reduce CollReduce]])

(defn my-reduce
  ([f coll] (coll-reduce coll f))
  ([f init coll] (coll-reduce coll f init)))

(defn make-reducer
  [reducible transformf]
  (reify
    CollReduce
    (coll-reduce [_ f1]
      (coll-reduce reducible (transformf f1) (f1)))
    (coll-reduce [_ f1 init]
      (coll-reduce reducible (transformf f1) init))))

(defn my-map
  [mapf reducible]
  (make-reducer
    reducible
    (fn [reducef] (fn [acc v] (reducef acc (mapf v))))))
