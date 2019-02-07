#!/bin/sh
echo 1 > ~shilo/Downloads/scrapersInMachines.txt
echo "\n" >> ~shilo/Downloads/scrapersInMachines.txt
for var1 in 1 2 3 4
do
  echo $var1 >> ~shilo/Downloads/scrapersInMachines.txt
  echo "\n" >> ~shilo/Downloads/scrapersInMachines.txt
  NAME=$(ssh -t shilo.gilor@wiser-scraper-production$var1.wiser.com ' sudo ls -A1 /mint/topologies')
  echo "$NAME" >> ~shilo/Downloads/scrapersInMachines.txt
  echo "\n" >> ~shilo/Downloads/scrapersInMachines.txt
  echo $var1 >> ~shilo/Downloads/scrapersInMachines.txt
  echo "\n" >> ~shilo/Downloads/scrapersInMachines.txt
done
