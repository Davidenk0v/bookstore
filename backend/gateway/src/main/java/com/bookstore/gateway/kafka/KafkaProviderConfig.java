package com.bookstore.gateway.kafka;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

// Indica que esta clase es una configuración de Spring
@Configuration
public class KafkaProviderConfig {

    // Inyecta el valor de la propiedad 'spring.kafka.bootstrapServers' desde el archivo de configuración
    @Value("${spring.kafka.bootstrapServers}")
    private String bootstrapServers;

    // Método que configura las propiedades del productor de Kafka
    public Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<>();

        // Establece la dirección del servidor de Kafka
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);

        // Define la clase que serializará las claves de los mensajes
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        // Define la clase que serializará los valores de los mensajes
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        return props;
    }

    // Crea y expone un bean de ProducerFactory, que se encargará de crear instancias de productores de Kafka
    @Bean
    public ProducerFactory<String, String> providerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    // Crea y expone un bean de KafkaTemplate, que proporciona métodos convenientes para enviar mensajes a Kafka
    @Bean
    public KafkaTemplate<String, String> kafkaTemplate(ProducerFactory<String, String> providerFactory) {
        return new KafkaTemplate<>(providerFactory);
    }
}
