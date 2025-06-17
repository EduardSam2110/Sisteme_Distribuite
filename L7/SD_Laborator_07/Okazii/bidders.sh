#!/bin/bash

NUM_BIDDERS=10
JAR_FILE="out/artifacts/BidderMicroservice_jar/BidderMicroservice.jar"


for((i=1; i<=NUM_BIDDERS;i++))
do
  echo "Pornesc Bidder $i"
  export PATH=$PATH:/home/iedi/.jdks/corretto-1.8.0_442/bin
  java -cp $JAR_FILE BidderMicroserviceKt &
  sleep 0.1
done
wait
echo "Toti bidderii au fost porniti"