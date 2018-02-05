/**
 * 
 */
package com.hackathon.producerconsumer.controller;

import java.io.StringWriter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hackathon.producerconsumer.model.ProducerConsumerMetrics;
import com.hackathon.producerconsumer.util.Utils;

import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.Counter;
import io.prometheus.client.Summary;
import io.prometheus.client.hotspot.MemoryPoolsExports;
import io.prometheus.client.hotspot.StandardExports;

/**
 * @author z001msut
 *
 */
@RestController
@RequestMapping("/producerconsumer")
public class ProducerConsumerController {
	@Value("${role}")
	String Role;

	@Value("${producer.name}")
	private String producerName;

	@Value("${producer.type}")
	private String producerType;

	@Value("${producer.maxLimit}")
	private String producerMaxLimit;

	@Value("${producer.productionRate}")
	private String ProductionRate;

	@Value("${producer.backlog}")
	private String Backlog;

	@Value("${consumer.name}")
	private String consumerName;

	@Value("${consumer.type}")
	private String consumerType;

	@Value("${consumer.maxLimit}")
	private String consumerMaxLimit;

	@Value("${consumer.consumptionRate}")
	private String consumptionRate;

	static final Counter requests_success = Counter.build().name("requests_success")
			.help("Total number of successful requests.").register();
	static final Counter requests_failed = Counter.build().name("requests_failed")
			.help("Total number of failed requests.").register();
	static final Counter requests_total = Counter.build().name("requests_total").help("Total number of requests.")
			.register();
	static final Summary requestLatency_consume = Summary.build().name("requests_latency_consume_seconds")
			.help("Request latency for consume in seconds.").register();
	static final Summary requestLatency_info = Summary.build().name("requests_latency_info_seconds")
			.help("Request latency for info in seconds.").register();

	static {
		new StandardExports().register();
		new MemoryPoolsExports().register();
	}

	@RequestMapping("/info")
	public ResponseEntity<String> getInfo() {
		// requests.inc();
		//Summary.Timer timer = requestLatency_info.startTimer();
		StringWriter writer = new StringWriter();
		writer.append("The Role is " + Role);
		writer.append("\n");
		if (Role.equalsIgnoreCase("producer")) {
			writer = getProducerProps(writer);
		} else if (Role.equalsIgnoreCase("consumer")) {
			writer = getConsumerProps(writer);
		}
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setContentType(MediaType.TEXT_PLAIN);
		//timer.observeDuration();
		return new ResponseEntity<String>(writer.toString(), responseHeaders, HttpStatus.OK);
	}

	@RequestMapping("/produce")
	public String produce() {
		requests_total.inc();

		if (Role.equalsIgnoreCase("Producer")) {
			requests_success.inc();

			return "Produced an " + producerType;
		} else {
			requests_failed.inc();

			return "This is an consumer and cannot produce";
		}
	}

	@RequestMapping("/consume")
	public String consume() {
		requests_total.inc();
		Summary.Timer timer = requestLatency_consume.startTimer();
		if (Role.equalsIgnoreCase("consumer")) {
			requests_success.inc();
			timer.observeDuration();
			return "Consumed an " + consumerType;
		} else {
			requests_failed.inc();
			timer.observeDuration();
			return "This is an producer and cannot consume";
		}
	}

	@RequestMapping("/metrics")
	public ProducerConsumerMetrics getMetrics() {
		ProducerConsumerMetrics metrics = Utils
				.getSummaryMetrics(CollectorRegistry.defaultRegistry.metricFamilySamples());
		// double total_before = requests_total.get();
		// try {
		// Thread.sleep(1000);
		// } catch (InterruptedException e) {
		// }
		// double requestPerSec = requests_total.get() - total_before;
		// metrics.setRequests_per_second(requestPerSec);
		return metrics;
	}

	private StringWriter getProducerProps(StringWriter writer) {
		writer.append("producer.name = ");
		writer.append(producerName);
		writer.append("\n");

		writer.append("producer.type = ");
		writer.append(producerType);
		writer.append("\n");

		writer.append("producer.maxLimit = ");
		writer.append(producerMaxLimit);
		writer.append("\n");

		writer.append("producer.productionRate = ");
		writer.append(ProductionRate);
		writer.append("\n");

		writer.append("producer.backlog = ");
		writer.append(Backlog);
		writer.append("\n");

		return writer;
	}

	private StringWriter getConsumerProps(StringWriter writer) {
		writer.append("consumer.name = ");
		writer.append(consumerName);
		writer.append("\n");

		writer.append("consumer.type = ");
		writer.append(consumerType);
		writer.append("\n");

		writer.append("consumer.maxLimit = ");
		writer.append(consumerMaxLimit);
		writer.append("\n");

		writer.append("consumer.consumptionRate = ");
		writer.append(consumptionRate);
		writer.append("\n");

		return writer;
	}

}
