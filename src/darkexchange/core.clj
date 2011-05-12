(ns darkexchange.core
  (:require [clojure.tools.string-utils :as conjure-str-utils]
            [config.environment :as environment]
            [darkexchange.database.util :as database-util]))

(def initialized? (atom false))

(def init? (promise))

(defn
  init-promise-fn []
  (environment/require-environment)
  (database-util/init-database)
  ;((:init session-config/session-store))
  ;(logging/info "Server Initialized.")
  ;(logging/info "Initializing plugins...")
  ;(plugin-util/initialize-all-plugins)
  ;(logging/info "Plugins initialized.")
  ;(logging/info "Initializing app controller...")
  ;(try
  ;  (require 'controllers.app)
  ;  (logging/info "App controller initialized.")
  ;  (deliver init? true)
  ;(catch Throwable t
  ;  (logging/error "Failed to initialize app controller." t)
  ;  (deliver init? false)))
  (deliver init? true))

(defn
#^{ :doc "Initializes the conjure server." }
  init []
  (when (compare-and-set! initialized? false true)
    (init-promise-fn))
  @init?)

(defn
#^{ :doc "Sets the server mode to the given mode. The given mode must be a keyword or string like development, 
production, or test." }
  set-mode [mode]
  (when mode 
    (environment/set-evironment-property (conjure-str-utils/str-keyword mode))))