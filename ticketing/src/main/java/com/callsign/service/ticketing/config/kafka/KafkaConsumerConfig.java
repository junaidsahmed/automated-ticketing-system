package com.callsign.service.ticketing.config.kafka;

import com.callsign.service.ticketing.models.request.TicketRequest;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Junaid Shakeel
 * @project Exercise
 */
@Configuration
public class KafkaConsumerConfig {

    @Value("${kafka.bootstrap-servers}")
    private String bootstrapServer;

    @Value("${kafka.consumer.group-id}")
    private String groudId;

    @Value("${kafka.consumer.auto-offset-reset}")
    private String offsetReset;

    @Bean
    public Map<String,Object> consumerConfigs(){
        Map<String,Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrapServer);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        props.put(ConsumerConfig.GROUP_ID_CONFIG,groudId);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,offsetReset);
        return props;
    }
    @Bean
    public ConsumerFactory<String , TicketRequest> consumerFactory(){
        JsonDeserializer<TicketRequest> deserializer = new JsonDeserializer<>(TicketRequest.class);
        deserializer.setUseTypeMapperForKey(true);
        deserializer.addTrustedPackages("*");
        return new DefaultKafkaConsumerFactory<>
                (consumerConfigs(),new StringDeserializer(),deserializer);

    }
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String,TicketRequest> kafkaListenerContainerFactory(){
        ConcurrentKafkaListenerContainerFactory<String,TicketRequest> factory=
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;

    }

}
