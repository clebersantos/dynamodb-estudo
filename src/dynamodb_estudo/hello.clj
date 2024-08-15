(ns dynamodb-estudo.hello
  (:require [taoensso.faraday :as far]))

(defn respond-hello [request]
  (let [dynamo (get-in request [:database :dynamodb])
        _
        (far/create-table dynamo :my-table
                          [:id :n]                          ; Primary key named "id", (:n => number type)
                          {:throughput {:read 1 :write 1}   ; Read & write capacity (units/sec)
                           :block?     true                 ; Block thread during table creation
                           })
        tables (far/list-tables dynamo)]
    {:status 200 :body tables}))
