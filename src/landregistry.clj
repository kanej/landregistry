(ns landregistry
  (:require [incanter.core :as incant]))

(def columns [:id :price :date-of-transfer :postcode :property-type :old-or-new :duration :paon :saon :street :locality :town :district :county :record-status :occurrence])

(defn add-town-occurence-column [dataset]
  (incant/conj-cols dataset (take (incant/nrow dataset) (repeat 1))))

(defn prepare-dataset [dataset]
  (-> dataset
      add-town-occurence-column
      (incant/col-names columns)))

(defn top-rows [n dataset]
  (incant/sel dataset :rows (range n)))

(defn town-freq [dataset]
  (->> dataset
       prepare-dataset
       (incant/$rollup :count :occurrence [:town])
       (incant/$order :occurrence :desc)
       (top-rows 20)
       incant/view))

(defn show-towns [dataset]
  (incant/with-data dataset
    (incant/view (incant/$ [:town]))))
