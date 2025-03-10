package com.bookstore.gateway.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {

    // Inyecta el valor de la propiedad 'spring.kafka.bootstrapServers' desde el archivo de configuración de la aplicación
    @Value("${spring.kafka.bootstrapServers}")
    private String bootstrapServers;

    // Crea un Map con las propiedades de configuración del consumidor
    public Map<String, Object> consumerConfigs() {
        Map<String, Object> props = new HashMap<>();
        // Establece los servidores bootstrap para el consumidor de Kafka
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        // Establece la clase deserializadora de la clave
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringSerializer.class);
        // Establece la clase deserializadora del valor
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return props;
    }

    // Define un bean para el ConsumerFactory
    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfigs());
    }

    // Define un bean para el KafkaListenerContainerFactory
    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> consumer() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        // Establece la fábrica de consumidores para la fábrica de contenedores de oyentes
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}
