/**
 * 
 */
package com.hackathon.producerconsumer.model;

import java.util.Date;

/**
 * @author z001msut
 *
 */
public class ProducerConsumerMetrics {

	double Jvm_used_heap;
	
	double Jvm_used_nonheap;
	
	double Jvm_max;
	
	double Cpu_total;
	
	double Up_time;
	
	Date date_uptime;
	
	double total_requests;
	
	double successful_requests;
	
	double failed_requests;
	
	double requests_per_second;

	public double getJvm_used_heap() {
		return Math.round(Jvm_used_heap/(1024 *1024));
	}

	public void setJvm_used_heap(double jvm_used) {
		Jvm_used_heap = jvm_used;
	}

	public double getJvm_max() {
		return Math.round(Jvm_max/(1024*1024));
	}

	public void setJvm_max(double jvm_max) {
		Jvm_max = jvm_max;
	}

	public double getCpu_total() {
		return Cpu_total;
	}

	public void setCpu_total(double cpu_total) {
		Cpu_total = cpu_total;
	}

	public double getStart_time() {
		return Up_time;
	}

	public void setStart_time(double start_time) {
		Up_time = start_time;
		long l = (long)start_time * 1000;
		date_uptime = new Date(l);
	}

	public double getTotal_requests() {
		return total_requests;
	}

	public void setTotal_requests(double total_requests) {
		this.total_requests = total_requests;
	}
	
	public String getDate_uptime() {
		return date_uptime.toString();
	}
	
	public void setDate_uptime(Date date) {
		date_uptime = date;
	}

	public double getSuccessful_requests() {
		return successful_requests;
	}

	public void setSuccessful_requests(double successful_requests) {
		this.successful_requests = successful_requests;
	}

	public double getFailed_requests() {
		return failed_requests;
	}

	public void setFailed_requests(double failed_requests) {
		this.failed_requests = failed_requests;
	}

	public double getUp_time() {
		return Up_time;
	}

	public void setUp_time(double up_time) {
		Up_time = up_time;
	}

	public double getRequests_per_second() {
		return requests_per_second;
	}

	public void setRequests_per_second(double requests_per_second) {
		this.requests_per_second = requests_per_second;
	}

	public double getJvm_used_nonheap() {
		return Math.round(Jvm_used_nonheap/(1024 *1024));
	}

	public void setJvm_used_nonheap(double jvm_used_nonheap) {
		Jvm_used_nonheap = jvm_used_nonheap;
	}
}
