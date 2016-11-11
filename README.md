# spark-hive-solr-demo

First, build the package and copy the shaded jar to the /tmp folder of a cluster node:

    mvn clean package
    scp target/spark-hive-solr-demo-1.0-SNAPSHOT.jar hadoop01:/tmp/

Then, `ssh` into the cluster node and execute the jar as the HDFS user:

    runuser -l hdfs -c 'spark-submit --class io.woolford.Main --master=yarn-cluster /tmp/spark-hive-solr-demo-1.0-SNAPSHOT.jar --files=/etc/hive/conf/hive-site.xml'

Ran this command:
	
	runuser -l hdfs -c 'spark-submit --class io.woolford.Main --master=yarn-cluster --files=/usr/hdp/current/spark-client/conf/hive-site.xml /tmp/spark-hive-solr-demo-1.0-SNAPSHOT.jar'
 
 The contents of file /usr/hdp/current/spark-client/conf/hive-site.xml are:
 	
 	<configuration>
 		<property>
		  <name>hive.metastore.warehouse.dir</name>
		  <value>/apps/hive/warehouse</value>
		</property>
		<property>
		  <name>javax.jdo.option.ConnectionURL</name>
		  <value>jdbc:mysql://sandbox.hortonworks.com/metastore</value>
		  <description>the URL of the MySQL database</description>
		</property>
		
		<property>
		  <name>javax.jdo.option.ConnectionDriverName</name>
		  <value>com.mysql.jdbc.Driver</value>
		</property>
		
		<property>
		  <name>javax.jdo.option.ConnectionUserName</name>
		  <value>root</value>
		</property>
		
		<property>
		  <name>javax.jdo.option.ConnectionPassword</name>
		  <value>hadoop</value>
		</property>
		
		<property>
		  <name>datanucleus.autoCreateSchema</name>
		  <value>false</value>
		</property>
		
		<property>
		  <name>datanucleus.fixedDatastore</name>
		  <value>true</value>
		</property>
		
		<property>
		  <name>datanucleus.autoStartMechanism</name>
		  <value>SchemaTable</value>
		</property>
	    
	    <property>
	      <name>hive.metastore.uris</name>
	      <value>thrift://sandbox.hortonworks.com:9083</value>
	    </property>
  	</configuration>
  	

/usr/hdp/current/hive-client/conf/hive-site.xml
<property>
		  <name>javax.jdo.option.ConnectionPassword</name>
		  <value>hadoop</value>
		</property>
		
		

su hdfs

vi firstname_lastname.txt

add some records:

$ cat firstname_lastname.txt 
Barack,Obama
Donald,Trump

hive
CREATE TABLE sanjay (
   firstname string,
   lastname string
)
ROW FORMAT DELIMITED FIELDS TERMINATED BY ','
LOCATION '/apps/hive/warehouse/sanjay';

from $
hdfs dfs -copyFromLocal firstname_lastname.txt /apps/hive/warehouse/sanjay

hive> select * from sanjay;
Barack	Obama
Donald	Trump


  