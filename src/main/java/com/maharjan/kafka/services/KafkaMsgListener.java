package com.maharjan.kafka.services;

import com.maharjan.kafka.dto.Customer;
import com.maharjan.kafka.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaMsgListener {
    Logger logger = LoggerFactory.getLogger(KafkaMsgListener.class);

    @KafkaListener(topics = Constants.KAFKA_TOPIC_PARTITION_5, groupId = Constants.KAFKA_GROUP_ID)
    public void listen1(String msg) {
        logger.info("Consumer1 consumed the msg = {}", msg);
    }

    @KafkaListener(topics = Constants.KAFKA_TOPIC_PARTITION_5, groupId = Constants.KAFKA_GROUP_ID)
    public void listen2(String msg) {
        logger.info("Consumer2 consumed the msg = {}", msg);
    }

    @KafkaListener(topics = Constants.KAFKA_TOPIC_PARTITION_5, groupId = Constants.KAFKA_GROUP_ID)
    public void listen3(String msg) {
        logger.info("Consumer3 consumed the msg = {}", msg);
    }

    @KafkaListener(topics = Constants.KAFKA_TOPIC_PARTITION_5, groupId = Constants.KAFKA_GROUP_ID)
    public void listen4(String msg) {
        logger.info("Consumer4 consumed the msg = {}", msg);
    }

    @KafkaListener(topics = Constants.KAFKA_TOPIC_PARTITION_5, groupId = Constants.KAFKA_GROUP_ID)
    public void listen5(String msg) {
        logger.info("Consumer5 consumed the msg = {}", msg);
    }

    @KafkaListener(topics = Constants.KAFKA_TOPIC_PARTITION_5, groupId = Constants.KAFKA_GROUP_ID)
    public void listen6(String msg) {
        logger.info("Consumer6 consumed the msg = {}", msg);
    }

    // Listen to Customer object
    @KafkaListener(topics = Constants.KAFKA_CUSTOMER_TOPIC, groupId = Constants.KAFKA_CUSTOMER_GROUP)
    public void listenCustomer(Customer customer) {
        logger.info("Consumer consumed the customer = {}", customer.toString());
    }
}
