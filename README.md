# Apache-Kafka

Commands for Console Producer and Consumer
--------------------------------------------------  
start zookeeper-server-start.bat %KAFKA_CONFIG%/zookeeper.properties  
start kafka-server-start.bat %KAFKA_CONFIG%/server.properties     
start kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic [[Hello-kafka]]  
start kafka-topics.bat --list --zookeeper localhost:2181  
start kafka-console-producer.bat --broker-list localhost:9092 --topic [[Hello-kafka]]          
start kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic [[Hello-kafka]] --from-beginning   
start kafka-topics.bat --zookeeper localhost:2181 --delete --topic [[Hello-kafka]]    

Local server.properties
----------------------------------------------------- 
listeners = PLAINTEXT://localhost:9092  
delete.topic.enable=true  


Tweet Producer-Consumer Setup
----------------------------------------------------- 
start zookeeper-server-start.bat %KAFKA_CONFIG%/zookeeper.properties  
start kafka-server-start.bat %KAFKA_CONFIG%/server.properties     
start kafka-topics.bat --list --zookeeper localhost:2181    
start kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 5 --topic [[IPL2018]]    

Producer--]]
Run TweetProducer , Program Arg IPL2018

Consumer--]]
Run TweetConsumerGroup , Program Arg IPL2018 5
(Note: Here 5 is no of partitions of topic [[IPL2018]] which is setup during Topic configuration)


