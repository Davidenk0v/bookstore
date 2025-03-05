package com.bookstore.books.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
public class MongoConnectionTest {
    @Autowired
    private MongoTemplate mongoTemplate;

    public void testConnection() {
        try {
            mongoTemplate.getDb().getName();
            System.out.println("Conexión exitosa a MongoDB");
        } catch (Exception e) {
            System.err.println("Error de conexión: " + e.getMessage());
        }
    }
}
