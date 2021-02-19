#!/bin/sh

#based on our needs, we can add options such as
    # --conf  spark.shuffle.manager=tungsten-sort
    # --num-executors 10 //--executor-cores 10
    # --executor-memory ...
#and we can change master

#to use the first approch : MostCommonValueHiveApproach
spark-submit --class com.ovh.mostCommonValue.MainApproaches.MostCommonValueHiveApproach --master yarn-cluster --files /home/ovhProject/OVH/config/ovhApplication.properties /home/ovhProject/OVH/scripts/jar/OVH-project-assembly-0.1

#to use the second approch : MostCommonValueWindowApproach
spark-submit --class com.ovh.mostCommonValue.MainApproaches.MostCommonValueWindowApproach --master yarn-cluster --files /home/ovhProject/OVH/config/ovhApplication.properties /home/ovhProject/OVH/scripts/jar/OVH-project-assembly-0.1
