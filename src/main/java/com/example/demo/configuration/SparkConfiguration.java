package com.example.demo.configuration;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SparkConfiguration {

    @Value("${spark.appName}")
    String appName;
    @Value("${spark.master}")
    String master;

    @Bean
    public SparkConf conf(){
        return new SparkConf().setMaster(master).setAppName(appName);
    }

    @Bean
    public JavaSparkContext sparkContext(){
        return new JavaSparkContext(conf());
    }
}
