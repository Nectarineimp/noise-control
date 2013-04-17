;; Lots of tuples get into the topology and the clock starts ticking
;; on their timeout, but if we up timeout everything takes forever due
;; to bad docs.  We really need a way to either limit the number of
;; tuples in the topology or timeout the processing of a particular
;; bolt (the nlp one)
{:nlp {:max-sentence-length 50}
 :datomic {:uri "datomic:dev://localhost:4334//playing"}
 :storm {:tuples-per-call 10
         :message-timeout-secs 30
         :worker-processes 4
         :max-spout-pending 10
         :debug true}
 }