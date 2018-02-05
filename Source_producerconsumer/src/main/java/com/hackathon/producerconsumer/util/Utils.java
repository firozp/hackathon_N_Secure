/**
 * 
 */
package com.hackathon.producerconsumer.util;

import java.util.Enumeration;

import com.hackathon.producerconsumer.model.ProducerConsumerMetrics;

import io.prometheus.client.Collector;

/**
 * @author z001msut
 *
 */
public class Utils {
	private static String role = "Consumer";
	public static String getRole() {
		return role;
	}
	public static void setRole(String Role) {
		role = Role;
	}
	public static ProducerConsumerMetrics getSummaryMetrics(Enumeration<Collector.MetricFamilySamples> mfs) {
		ProducerConsumerMetrics metrics = new ProducerConsumerMetrics();
		while (mfs.hasMoreElements()) {
			Collector.MetricFamilySamples metricFamilySamples = mfs.nextElement();
			for (Collector.MetricFamilySamples.Sample sample : metricFamilySamples.samples) {
				if (sample.name.contains("jvm_memory_bytes_used") && sample.labelValues.get(0).equals("heap")) {
					metrics.setJvm_used_heap(sample.value);
				}
				if (sample.name.contains("jvm_memory_bytes_used") && sample.labelValues.get(0).equals("nonheap")) {
					metrics.setJvm_used_nonheap(sample.value);
				}
				if (sample.name.contains("jvm_memory_bytes_max") && sample.labelValues.get(0).equals("heap")) {
					metrics.setJvm_max(sample.value);
				}
				if (sample.name.contains("process_cpu_seconds_total")) {
					metrics.setCpu_total(sample.value);
				}
				if (sample.name.contains("process_start_time_seconds")) {
					metrics.setStart_time(sample.value);
				}
				if (sample.name.contains("requests_total")) {
					metrics.setTotal_requests(sample.value);
				}
				if (sample.name.contains("requests_success")) {
					metrics.setSuccessful_requests(sample.value);
				}
				if (sample.name.contains("requests_failed")) {
					metrics.setFailed_requests(sample.value);
				}
			}
		}
		return metrics;
	}
}
