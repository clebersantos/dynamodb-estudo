(ns dynamodb-estudo.components.server
  (:require [io.pedestal.http :as http]
            [io.pedestal.interceptor :as interceptor]
            [dynamodb-estudo.endpoints :as endpoints]
            [com.stuartsierra.component :as component]))


(defn- db-interceptor [database]
  (interceptor/interceptor {:name :db-interceptor
                            :enter (fn [context]
                                     (update context :request assoc :database database))})
  )

(defn service-map [database]
  (-> {::http/routes endpoints/routes
       ::http/type   :jetty
       ::http/port   8890}
      http/default-interceptors
      (update ::http/interceptors conj http/json-body)
      (update ::http/interceptors conj (db-interceptor database))))

(defn- start [database]
  (-> (service-map database)
      (http/create-server)
      http/start))

; defonce muda apenas uma vez
; o atamo que é alterado
(defonce server
         (atom nil))

(defrecord Server [database]
  component/Lifecycle
  (start [this]
    (if (nil? @server)
      (let [server-started (start database)]
        (reset! server server-started)
        (assoc this :http @server))
      (println "O servidor já está rodando!")))
  (stop [this]
    (http/stop @server)
    (assoc this :http nil)))

(defn new-server []
  (->Server {}))                                            ;chave vazia para passar um mapa fazio