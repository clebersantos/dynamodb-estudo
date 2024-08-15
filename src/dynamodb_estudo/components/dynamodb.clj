(ns dynamodb-estudo.components.dynamodb
    (:require [com.stuartsierra.component :as component]
              [taoensso.faraday :as far]))

(defrecord Dynamodb []
  component/Lifecycle
  (start [this]
    (assoc this :dynamodb {:endpoint "http://localhost:8000"}))
  (stop [this]
    this))

(defn new-dynamodb []
  (->Dynamodb))

