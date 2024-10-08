(ns dynamodb-estudo.diplomat.http-in.employee
  (:require [dynamodb-estudo.controllers.employee :as controllers.employee]) )

(defn look-up [request]
  (let [dynamodb (get-in request [:database :dynamodb])
        employee-id (get-in request [:path-params :id] )
        response (controllers.employee/look-up employee-id dynamodb)]
    {:status 200 :body response}))

(defn create! [request]
  (let [dynamodb (get-in request [:database :dynamodb])
        params (:json-params request)
        response (controllers.employee/create! params dynamodb)]
    {:status 201 :body response}))
