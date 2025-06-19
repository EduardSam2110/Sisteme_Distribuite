#!/bin/bash

for i in {1..10}
do
  echo "Pornesc service$i.jar..."
  java -jar BidderMicroservice.jar &
done

echo "Toate serviciile au fost pornite."

