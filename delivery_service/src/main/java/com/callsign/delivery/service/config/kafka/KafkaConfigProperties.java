package com.callsign.delivery.service.config.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Junaid Shakeel
 * @project Exercise
 */
@Configuration
@Slf4j
public class KafkaConfigProperties {
    @Value("${kafka.producer.bootstrap-server}")
    private String bootStrapServer;
    /*
        returns kafka producer's factory
    */
    @Bean
    public <T> ProducerFactory<String,T> producerFactory(){
        Map<String,Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,bootStrapServer);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class.getName());
        log.info("boot strap server "+bootStrapServer);
        return new DefaultKafkaProducerFactory(props);
    }
    @Bean
    public <T> KafkaTemplate<String,T> kafkaTemplate(){
        return new KafkaTemplate(producerFactory());
    }
}
