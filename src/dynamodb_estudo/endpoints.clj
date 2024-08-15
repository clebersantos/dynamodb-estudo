(ns dynamodb-estudo.endpoints
  (:require
    [io.pedestal.http.route :as route]
    [dynamodb-estudo.hello :as hello]))


(def routes
  (route/expand-routes
    #{["/greet" :get hello/respond-hello :route-name :greet]}))
