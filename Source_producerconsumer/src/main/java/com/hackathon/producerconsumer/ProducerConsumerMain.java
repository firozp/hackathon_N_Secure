package com.hackathon.producerconsumer;

import java.util.List;

import io.prometheus.client.spring.boot.EnablePrometheusEndpoint;
import io.prometheus.client.spring.boot.EnableSpringBootMetricsCollector;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.hackathon.producerconsumer.util.Utils;

/**
 * Main class of the producer consumer microservice
 *
 */
@SpringBootApplication(scanBasePackages={"com.hackathon.producerconsumer"})
//@EnablePrometheusEndpoint
//@EnableSpringBootMetricsCollector
public class ProducerConsumerMain 
{
    public static void main( String[] args )
    {
    	SpringApplication.run(ProducerConsumerMain.class, args);
    }
}
