(ns dynamodb-estudo.components
  (:require [com.stuartsierra.component :as component]
            [dynamodb-estudo.components.server :as server]
            [dynamodb-estudo.components.dynamodb :as dynamodb]))



(defn base []
  (component/system-map
    :database (dynamodb/new-dynamodb)
    :server (component/using (server/new-server) [:database])
    ))

(defn run []
  (component/start (base)))