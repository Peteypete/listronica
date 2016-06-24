(ns webdev.core
  (:require [ring.adapter.jetty :as jetty]
            [compojure.core :refer [defroutes GET]]
            [compojure.route :refer [not-found]]
            [ring.handler.dump :refer [handle-dump]]))

(defn greet [req]
  {:status 200
   :body "Hello, World!"
   :headers {}})

(defn goodbye [req]
  {:status 200
   :body "Goodbye, Cruel World!"
   :headers {}})

(defn about [req]
  {:status 200
   :body "My name is Eric Normand and I created this site! Isn't it lovely?"
   :headers {}})

(defn yo [req]
  (let [name (get-in req [:route-params :name])]
    {:status 200
     :body (str "Yo! " name "!")
     :headers {}}))

(defroutes app
  (GET "/" [] greet)
  (GET "/goodbye" [] goodbye)
  (GET "/yo/:name" [] yo)

  (GET "/about" [] about)
  (GET "/request" [] handle-dump)
  (not-found "Page not found."))

(defn -main [port]
  (jetty/run-jetty app {:port (Integer. port)}))

;; -dev-main has moved to dev/webdev/dev.clj
;; please see the note at https://purelyfunctional.tv/web-dev-in-clojure/lets-get-a-server-up-and-running-in-the-cloud/#dev-main
