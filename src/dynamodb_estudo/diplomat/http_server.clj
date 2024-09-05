(ns dynamodb-estudo.diplomat.http-server
  (:require [dynamodb-estudo.diplomat.http-in.employee :as http-in.employee]
            [io.pedestal.http.route :as route]
            [io.pedestal.http.body-params :as body-params]))

(def routes
  (route/expand-routes
    #{["/employee/:id" :get http-in.employee/look-up :route-name :employee-look-up]
      ["/employee" :post [(body-params/body-params) http-in.employee/create!] :route-name :employee-create]}))
