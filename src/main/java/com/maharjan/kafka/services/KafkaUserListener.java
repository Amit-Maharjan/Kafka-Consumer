package com.maharjan.kafka.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maharjan.kafka.dto.User;
import com.maharjan.kafka.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class KafkaUserListener {
    Logger logger = LoggerFactory.getLogger(KafkaUserListener.class);

    @RetryableTopic(attempts = "4") // Default attempts = 3
    @KafkaListener(topics = Constants.KAFKA_USER_TOPIC, groupId = Constants.KAFKA_USER_GROUP)
    public void listenUser(User user, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic, @Header(KafkaHeaders.OFFSET) long offset) {
        try {
            logger.info("Received {} from {} offset {}", new ObjectMapper().writeValueAsString(user), topic, offset);

            // Restrict IP Address before process the events
            List<String> restrictedIpList = Stream.of("32.241.244.236", "15.55.49.164", "81.1.95.253", "126.130.43.183").collect(Collectors.toList());
            if (restrictedIpList.contains(user.getIpAddress())) {
                throw new RuntimeException("Invalid IP Address!");
            }
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }
    }

    @DltHandler
    public void listenDLT(User user, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic, @Header(KafkaHeaders.OFFSET) long offset) {
        logger.info("DLT Received: {} from {} offset {}", user.getFirstname(), topic, offset);
    }
}
