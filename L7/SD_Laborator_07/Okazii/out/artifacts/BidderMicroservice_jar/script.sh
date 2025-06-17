#!/bin/bash
for i in {1..100}
do
    java -jar * &  # rulează în fundal
    (( i % 100 == 0 )) && wait  # așteaptă după fiecare 100 de procese
done
