package com.bookstore.gateway.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic generateTopic() {
        Map<String,String> config = new HashMap<>();
        //Delete borra el mensaje por completo cuando ya no se necesita
        //Compact mantiene el mensaje mas actual y borra los anteriores
        config.put(TopicConfig.CLEANUP_POLICY_CONFIG, TopicConfig.CLEANUP_POLICY_DELETE);
        //El tiempo que se va a dejar el mensaje antes de que se borre a no ser que esté configurado que no se borre.
        // Esto es en milisegundos.
        // Por defecto es -1 (No se borra)
        config.put(TopicConfig.RETENTION_MS_CONFIG, "172800000");
        //El tamaño máximo que puede tener un segmento antes de que se cree uno nuevo.
        //Es en bytes. Por defecto es 1073741824 (1GB)
        config.put(TopicConfig.SEGMENT_BYTES_CONFIG, "1073741824");
        //El tamaño máximo que puede tener un mensaje antes de que se rechace.
        //Es en bytes. Por defecto es 1048576 (1MB)
        config.put(TopicConfig.MAX_MESSAGE_BYTES_CONFIG, "10485760");

        return TopicBuilder.name("bookstore")
                .partitions(2)
                .replicas(2)
                .configs(config)
                .build();
    }
}
