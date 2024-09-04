(ns dynamodb-estudo.controllers.employee
  (:require [dynamodb-estudo.db.employee :as db.employee]))

(defn look-up [employee-id dynamodb]
  (db.employee/look-up dynamodb employee-id))
