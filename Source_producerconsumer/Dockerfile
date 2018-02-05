FROM openjdk:8-jre-alpine
 
ENTRYPOINT ["/usr/bin/java", "-jar", "-Dserver.port=20001", "/usr/share/ProducerConsumer/producerconsumer-1.0.jar"]
 
# Add Maven dependencies
ADD target/producerconsumer-1.0.jar /usr/share/ProducerConsumer/producerconsumer-1.0.jar
ADD ApplicationStats.json /usr/share/ProducerConsumer/ApplicationStats.json
#LABEL io.cadvisor.metric.prometheus-xyz=/usr/share/ProducerConsumer/ApplicationStats.json