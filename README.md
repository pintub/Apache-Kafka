# Apache-Kafka

Commands for Console Producer and Consumer
--------------------------------------------------  
start zookeeper-server-start.bat %KAFKA_CONFIG%/zookeeper.properties  
start kafka-server-start.bat %KAFKA_CONFIG%/server.properties     
start kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic Hello-Kafka  
start kafka-topics.bat --list --zookeeper localhost:2181  
start kafka-console-kafkaProducer.bat --broker-list localhost:9092 --topic Hello-Kafka          
start kafka-console-consumer.bat --bootstrap-server localhost:2181 --topic Hello-Kafka --from-beginning   
start kafka-topics.bat --zookeeper localhost:2181 --delete --topic First-kafka    

Local server.properties
----------------------------------------------------- 
listeners = PLAINTEXT://localhost:9092  
delete.topic.enable=true  

