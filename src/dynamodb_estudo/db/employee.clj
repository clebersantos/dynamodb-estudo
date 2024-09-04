(ns dynamodb-estudo.db.employee
  (:require [taoensso.faraday :as far]))

(defn look-up [{:keys [opts table] } employee-id]
  (merge (far/describe-table opts table) {:employee-id employee-id}))