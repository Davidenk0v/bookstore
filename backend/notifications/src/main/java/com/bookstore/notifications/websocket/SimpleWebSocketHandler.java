package com.bookstore.notifications.websocket;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component  // Marca la clase como un componente de Spring, lo que permite que Spring la gestione como un bean
public class SimpleWebSocketHandler extends TextWebSocketHandler {  // La clase hereda de TextWebSocketHandler para manejar mensajes de WebSocket de texto

    // Un mapa para almacenar las sesiones de WebSocket asociadas a un nombre de usuario
    private static final Map<WebSocketSession, String> sessions = new HashMap<>();

    // Este método se llama cada vez que un mensaje de texto es recibido desde una sesión WebSocket
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // Imprime el mensaje recibido en la consola
        System.out.println("Received message: " + message.getPayload());

        // Si el mensaje es un mensaje de login (comienza con "{\"type")
        if (message.getPayload().startsWith("{\"type")) {
            // Extrae el nombre del mensaje, que tiene el formato {"type":"LOGIN","name":"<nombre>"}
            String name = message.getPayload().replace("{\"type\":\"LOGIN\",\"name\":\"", "").replace("\"}", "");
            // Asocia la sesión con el nombre extraído
            sessions.put(session, name);
            // Envía un mensaje de bienvenida al usuario
            session.sendMessage(new TextMessage("Bienvenido, " + name + "!!"));
            return;  // Sale del método ya que se procesó el mensaje de login
        }

        // Si el mensaje no es de login, simplemente responde con un mensaje genérico
    }

    // Método para enviar un mensaje a todos los usuarios, diferenciando según su nombre
    public void sendMessageToUser(String message) throws IOException {
        // Recorre todas las sesiones y sus nombres asociados
        System.out.println("Sending message to all users: " + message);
        for (Map.Entry<WebSocketSession, String> entry : sessions.entrySet()) {
            WebSocketSession session = entry.getKey();  // La sesión WebSocket
            String name = entry.getValue();  // El nombre del usuario asociado a la sesión
            System.out.println("Session: " + sessions);
            // Si la sesión está abierta, se envía un mensaje
            if (session.isOpen()) {
                    session.sendMessage(new TextMessage(message));
                }
            }
        }
}
