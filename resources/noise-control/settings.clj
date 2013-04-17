{:nlp {:max-sentence-length 50}
 :datomic {:uri "datomic:free://localhost:4334//Discovery"}
 :database {:classname "org.postgresql.Driver"
            :subprotocol "postgresql"
            :subname "//localhost:5432/Discovery"
            :user "postgres"
            :password "root"}
 :storm {:tuples-per-call 10
         :message-timeout-secs 30
         :worker-processes 4
         :max-spout-pending 10
         :debug true}
 }