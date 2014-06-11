(ns user
  "Tools for interactive development with the REPL. This file should
  not be included in a production build of the application."
  (:require
   [clojure.java.javadoc :refer (javadoc)]
   [clojure.pprint :refer (pprint)]
   [clojure.reflect :refer (reflect)]
   [clojure.repl :refer (apropos dir doc find-doc pst source)]
   [clojure.set :as set]
   [clojure.string :as str]
   [clojure.test :as test]
   [clojure.tools.namespace.repl :refer (refresh refresh-all)]
   [incanter.io :as io]
   [incanter.core :as incant]
   [landregistry]))

(defonce sales (io/read-dataset "./data/pp-monthly.csv"))

(defn show-towns [dataset]
  (incant/with-data dataset
    (incant/view (incant/$ [:col12]))))

#_(show-towns sales)
