(ns landregistry
  (:require [incanter.core :as incant]))

(def columns [:id :price :date-of-transfer :postcode :property-type :old-or-new :duration :paon :saon :street :locality :town :district :county :record-status])

(defn- add-town-occurence-column [dataset]
  (incant/conj-cols dataset (take (incant/nrow dataset) (repeat 1))))

(defn- top-rows [n dataset]
  (incant/sel dataset :rows (range n)))

(defn prepare-dataset
  "Prepare the dataset for further analysis.

   This consists of two steps: firstly, add a column of 1s to
   provide a hook for count aggregations. Secondly add in column names
   for ease."
  [dataset]
  (-> dataset
      add-town-occurence-column
      (incant/col-names (conj columns :occurrence))))

(defn show-top-twenty-towns
  "Given a prepared land registry monthly dataset, return the top 20
   towns with the most sales.

   Town is defined by the town/city column of the land registry
   dataset. To prepare the dataset see landregistry/prepare-dataset."
  [dataset]
  (->> dataset
       (incant/$rollup :count :occurrence [:town])
       (incant/$order :occurrence :desc)
       (top-rows 20)
       incant/view))

(defn show-all-town-entries
  "Show the town column of the land registry month dataset."
  [dataset]
  (incant/with-data dataset
    (incant/view (incant/$ [:town]))))
