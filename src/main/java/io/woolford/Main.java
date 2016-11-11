package io.woolford;


import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.hive.HiveContext;

public class Main {

    static Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {

        SparkConf conf = new SparkConf();
        conf.setAppName("Hive to Solr");
        SparkContext sc = new SparkContext(conf);

        HiveContext hiveContext = new HiveContext(sc);

        Row[] results = hiveContext.sql("SELECT * FROM default.sanjay LIMIT 10").collect();

        for (Row row : results){
            logger.log(Level.INFO, row.toString());
        }

    }

}
