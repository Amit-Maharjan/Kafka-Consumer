package com.maharjan.kafka.services;

import com.maharjan.kafka.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaMsgListener {
    Logger logger = LoggerFactory.getLogger(KafkaMsgListener.class);

    @KafkaListener(topics = Constants.KAFKA_TOPIC_PARTITION_5, groupId = Constants.KAFKA_GROUP_ID_1)
    public void listen(String msg) {
        logger.info("Consumer consumed the msg = {}", msg);
    }
}
