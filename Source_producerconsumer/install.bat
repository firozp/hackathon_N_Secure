@ECHO OFF
cd /d D:/Projects/NetAct/Cloud/myprojects
cd producerconsumer

ECHO "Creating Config Map"

kubectl create configmap producer-consumer-config --from-literal=producer.name=MessagePoller --from-literal=producer.type=Events --from-literal=producer.maxLimit=100 --from-literal=producer.productionRate=5 --from-literal=producer.backlog=50 --from-literal=consumer.name=MessageConsumer --from-literal=consumer.maxLimit=75 --from-literal=consumer.consumptionRate=10 --from-literal=consumer.type=Events

ECHO "Created Config Map"

ECHO -------------------------------------------------------------

PAUSE

ECHO "Installing ProducerConsumer-1"

kubectl create -f producerconsumer-service-1.yaml
kubectl create -f producerconsumer-deployment-1.yaml

ECHO "Deployed ProducerConsumer-1 and the URL is"
minikube service producerconsumer-1 --url

ECHO -------------------------------------------------------------

PAUSE

ECHO "Installing ProducerConsumer-2"

kubectl create -f producerconsumer-service-2.yaml
kubectl create -f producerconsumer-deployment-2.yaml

ECHO "Deployed ProducerConsumer-2 and the URL is"
minikube service producerconsumer-2 --url

ECHO -------------------------------------------------------------
@ECHO ON
