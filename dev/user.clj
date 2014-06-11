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

(def columns [:id :price :date-of-transfer :postcode :property-type :old-or-new :duration :paon :saon :street :locality :town :district :county :record-status :occurrence])

(defn add-town-occurence-column [dataset]
  (incant/conj-cols dataset (take (incant/nrow dataset) (repeat 1))))

(defonce sales
  (-> (io/read-dataset "./data/pp-monthly.csv")
      add-town-occurence-column
      (incant/col-names columns)))

(defn show-towns [dataset]
  (incant/with-data dataset
    (incant/view (incant/$ [:town]))))

(defn top-rows [n dataset]
  (incant/sel dataset :rows (range n)))

(defn town-freq [dataset]
  (->> dataset
       (incant/$rollup :count :occurrence [:town])
       (incant/$order :occurrence :desc)
       (top-rows 20)
       incant/view))

(comment
  (show-towns sales)
  (town-freq sales))
