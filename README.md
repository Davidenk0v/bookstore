# Bookstore
Una aplicación basada en microservicios, que trata de una librería, donde hay usuarios y libros, pudiendo hacer préstamos. Toda la app tiene que estar segurizada con JWT, además hay que crear un streamer de logs para tener un registro de logs de lo que pasa en la app, todos se tiene que poder gestionar desde un front en REACT + Typescript

## Stack Tecnológico

### Backend:

Framework: Spring Boot 3.x
Seguridad: Spring Security + JWT
Gateway: Spring Cloud Gateway
Comunicación entre servicios: Spring Cloud OpenFeign / RestTemplate
Mensajería: Spring Kafka
WebSockets: Spring WebSocket


### Bases de Datos:

PostgreSQL: Para servicios de Usuarios y Préstamos (transaccionales)
MongoDB: Para servicio de Libros (flexible para metadatos, mejores capacidades de búsqueda)
Elasticsearch: Para logging centralizado



### Microservicios (Spring Boot)
#### 1. Servicio de Usuarios

Base de datos: PostgreSQL

Responsabilidades:

Gestión de usuarios y roles
Perfil de usuario
Historial de actividad
Produce eventos a Kafka para auditoría



2. Servicio de Libros

Base de datos: MongoDB

Responsabilidades:

CRUD de libros
Búsqueda avanzada mediante MongoDB
Gestión de géneros/categorías
Produce eventos a Kafka cuando el inventario cambia



3. Servicio de Préstamos

Base de datos: PostgreSQL

Responsabilidades:

Gestión de préstamos y devoluciones
Fechas de vencimiento y multas
Verificación de disponibilidad
Comunicación con Servicio de Libros mediante Feign/RestTemplate



4. Servicio de Notificaciones

Responsabilidades:

Suscriptor de Kafka para eventos
Envío de notificaciones en tiempo real vía WebSockets
Almacenamiento de notificaciones no leídas

## Bases de datos
![base de datos](https://github.com/user-attachments/assets/85bd2040-ed1c-44ac-ac78-b324eef5cef7)

## Arquitectura
![Arquitectura](https://github.com/user-attachments/assets/c16d27e1-9634-4c26-9874-e83191981288)

## Flujo de autenticación.
![Flujo de autenticación y seguridad](https://github.com/user-attachments/assets/bff52552-666e-4502-b75c-a83f861b5cca)

## Prototipo de la interfaz
### Login
![Login](https://github.com/user-attachments/assets/5b78eb21-ac36-4c01-8cbc-0c43ae8d6111)

### Register
![Register](https://github.com/user-attachments/assets/41d53ce3-dc37-4c0c-90e2-2195d7d0211a)

### Books
![Books](https://github.com/user-attachments/assets/44335b92-dbd4-46dd-91e0-187c05173dca)

### Profile
![Profile](https://github.com/user-attachments/assets/1e7aaf8e-0bc7-4fa9-9925-d2dc6cb78c5e)




