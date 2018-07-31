;---
; Excerpted from "Seven Concurrency Models in Seven Weeks",
; published by The Pragmatic Bookshelf.
; Copyrights apply to this code. It may not be used to create training material, 
; courses, books, articles, and the like. Contact us if you are in doubt.
; We make no guarantees that this code is fit for any purpose. 
; Visit http://www.pragmaticprogrammer.com/titles/pb7con for more book information.
;---
(ns server.core
  (:require [compojure.core         :refer :all]
            [compojure.handler      :refer [site]]
            [ring.util.response     :refer [response status]]
            [ring.adapter.jetty     :refer [run-jetty]]
            [cheshire.core          :as json]
            [ring.middleware.reload :refer [wrap-reload]]))

;(def players (atom ()))
(def players (atom ()))

(defn list-players []
  (response (json/encode @players))) 

(defn create-player [player-name]
  (swap! players conj player-name) 
  (status (response "") 201))

(defn remove-player [player-name]
  (swap! players (fn [p] (remove #(= player-name %1) p)))
  (status (response "") 200))

(defroutes app-routes
  (GET "/players" [] (list-players))
  (PUT "/players/:player-name" [player-name] (create-player player-name))
  (DELETE "/players/:player-name" [player-name] (remove-player player-name)))

(defn -main [& args]
  (run-jetty (site (wrap-reload app-routes)) {:port 3000}))
