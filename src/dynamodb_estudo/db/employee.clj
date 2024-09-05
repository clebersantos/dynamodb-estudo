(ns dynamodb-estudo.db.employee
  (:require [taoensso.faraday :as far]))

(defn look-up [{:keys [opts table] } employee-id]
  (far/scan opts table #_{:pk (str "HR-EMPLOYEE" employee-id)}))

; efeito colateral
; pode acontecer alguma coisa que o resultado não é puro
; a funcao é pura quando o dado é imutável
; o valor pode mudar pq lída com Infra

(defn create! [{:keys [opts table]} full-name job-title]
  (far/put-item opts table {:pk "HR-EMPLOYEE2" :sk "EMPLOYEE2" :full-name full-name :job-title job-title}))

