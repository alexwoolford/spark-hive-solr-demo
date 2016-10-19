# spark-hive-solr-demo

First, build the package and copy the shaded jar to the /tmp folder of a cluster node:

    mvn clean package
    scp target/spark-hive-solr-demo-1.0-SNAPSHOT.jar hadoop01:/tmp/

Then, `ssh` into the cluster node and execute the jar as the HDFS user:

    runuser -l hdfs -c 'spark-submit --class io.woolford.Main --master=yarn-cluster /tmp/spark-hive-solr-demo-1.0-SNAPSHOT.jar --files=/etc/hive/conf/hive-site.xml'
