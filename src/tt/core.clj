(ns tt.core
  (:require [java-time :as time]
            [next.jdbc :as jdbc]
            [next.jdbc.result-set :as rs]
            [clojure.string])
  (:gen-class))

(def bg-colors
  (map #(str "\u001B[48;5;" % "m") (range 1 254)))

(def dbspec {:dbtype "sqlite"
             :dbname "database.db"})

(jdbc/execute! dbspec ["
                        create table if not exists tt (
                        proj_name varchar(200) not null,
                        color varchar(200) not null,
                        start datetime not null,
                        end datetime
                        )"])

(defn execute!
  [query]
  (jdbc/execute! dbspec query {:builder-fn rs/as-unqualified-maps}))

(defn execute-one!
  [query]
  (jdbc/execute-one! dbspec query {:builder-fn rs/as-unqualified-maps}))

(defn already-running?
  [proj-name]
  (let [result (execute-one! ["select rowid from tt where proj_name = ? and end is null" proj-name])]
    (if result
      true
      false)))

(defn stop-running-projects!
  []
  (execute! ["update tt set end = ? where end is null" (time/sql-timestamp)]))

(defn insert-project!
  [project-name color]
  (stop-running-projects!)
  (execute! ["insert into tt values (?, ?, ?, ?)" project-name color (time/sql-timestamp) nil]))

(defn get-color-for-project
  [project-name]
  (let [result (execute-one! ["select color from tt where proj_name = ?" project-name])]
    (if result
      (:color result)
      (rand-nth bg-colors))))


(defn start-project!
  [project-name]
  (let [is-running (already-running? project-name)
        color (get-color-for-project project-name)]
    (if is-running
      (println "Already running")
      (insert-project! project-name color))))

(defn -main
  [project-name]
  (start-project! project-name)
  (println "Hello!"))