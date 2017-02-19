(defproject etl-examples "0.1.0-SNAPSHOT"
  :description "ETL examples in Clojure"
  :url "https://github.com/wizardpb/etl-examples"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [
                 [org.clojure/clojure "1.8.0"]
                 [org.clojure/data.csv "0.1.3"]
                 ]
  :main ^:skip-aot etl-examples.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
