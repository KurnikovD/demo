package com.example.demo.spark;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;

public interface SparkTransformation {
    Dataset<Row> trasform(Dataset<Row> dataset);
}

