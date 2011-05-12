(ns darkexchange.database.migrations.004-create-trades
  (:use darkexchange.database.util))

(defn
#^{:doc "Migrates the database up to version 4."}
  up []
  (create-table :trades
    (id)
    (belongs-to :offer)
    (integer :wants-first) ; Set to true if the wants currency should be sent first.
    (integer :wants-sent) ; Set to true when the wants is sent.
    (integer :wants-received) ; Set to true when the wants is received.
    (integer :has-sent) ; Set to true when the has is sent
    (integer :has-received))) ; Set to true when the has is received.
  
(defn
#^{:doc "Migrates the database down from version 4."}
  down []
  (drop-table :trades))