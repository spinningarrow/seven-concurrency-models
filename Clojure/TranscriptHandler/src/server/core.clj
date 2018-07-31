;---
; Excerpted from "Seven Concurrency Models in Seven Weeks",
; published by The Pragmatic Bookshelf.
; Copyrights apply to this code. It may not be used to create training material, 
; courses, books, articles, and the like. Contact us if you are in doubt.
; We make no guarantees that this code is fit for any purpose. 
; Visit http://www.pragmaticprogrammer.com/titles/pb7con for more book information.
;---
(ns server.core
  (:require [server.sentences   :refer [strings->sentences]]
            [server.charset     :refer [wrap-charset]]
            [server.translate   :refer [translate]]
            [server.session     :refer [new-session get-session]]
            [compojure.core     :refer :all]
            [compojure.handler  :refer [api]]
            [ring.util.response :refer [charset response]]
            [ring.adapter.jetty :refer [run-jetty]]
            [ring.middleware.reload :refer [wrap-reload]]
            [clojure.tools.reader.edn :as edn]))

(defn create-session []
  (let [snippets (repeatedly promise)
        translations (delay (map translate
                                 (strings->sentences (map deref snippets))))]
    (new-session {:snippets snippets :translations translations})))

(defn accept-snippet [session n text]
  (deliver (nth (:snippets session) n) text))

(defn get-translation [session n]
  (deref (nth @(:translations session) n) 1000 "????"))

(defroutes app-routes
  (POST "/session/create" []
    (response (str (create-session))))

  (context "/session/:session-id" [session-id]
    (let [session (get-session (edn/read-string session-id))]
      (routes
        (PUT "/snippet/:n" [n :as {:keys [body]}]
          (accept-snippet session (edn/read-string n) (slurp body))
          (response "OK"))

        (GET "/translation/:n" [n]
          (response (get-translation session (edn/read-string n))))))))

(defn -main [& args]
  (run-jetty (wrap-reload (wrap-charset (api app-routes))) {:port 3000}))

(deref (nth (map translate (map deref ((get-session 1) :snippets))) 0) 1000 "??????????")
