(ns dynamodb-estudo.components.dynamodb
    (:require [com.stuartsierra.component :as component]
              [taoensso.faraday :as far]))

(defrecord Dynamodb []
  component/Lifecycle
  (start [this]
    (let [opts {:endpoint "http://localhost:8000"}
          table :human-resources
          table-describe (far/describe-table opts table)]   ; colchete para fazer binds nas variaveis
      (when (nil? table-describe)                             ;tudo que tem interrogacao é um predicado(que avalia se é booleano)
        (far/create-table opts table [:pk :s]
                          {:range-keydef [:sk :s]
                           :throughput {:read 1 :write 1}
                           :block? true}))
      (assoc this :dynamodb {:opts opts :table table})))
  (stop [this]
    this))

(defn new-dynamodb []
  (->Dynamodb))

