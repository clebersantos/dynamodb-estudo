(ns dynamodb-estudo.core-test
  (:require [clojure.test :refer :all]
            [dynamodb-estudo.core :refer :all]))

(defn teste2 [a b]
  (= a b))

(deftest a-test
  (testing "FIXME, I fail."
    (is (teste2 2 2))))

