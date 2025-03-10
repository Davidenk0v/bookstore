package com.bookstore.notifications.config;

import com.bookstore.notifications.websocket.SimpleWebSocketHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
// Marca la clase como una clase de configuración de Spring. Spring procesará esta clase para configurar beans y otras opciones.
@EnableWebSocket  // Habilita la funcionalidad de WebSocket en la aplicación Spring.
public class WebSocketConfig implements WebSocketConfigurer {  // Implementa WebSocketConfigurer para configurar los WebSockets

    // Este método se llama para registrar los controladores de WebSocket en la aplicación
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        // Registra el controlador WebSocket (SimpleWebSocketHandler) para la ruta "/ws"
        registry.addHandler(new SimpleWebSocketHandler(), "/ws")
                // Permite solicitudes WebSocket desde cualquier origen (*)
                .setAllowedOrigins("*");
    }
}
