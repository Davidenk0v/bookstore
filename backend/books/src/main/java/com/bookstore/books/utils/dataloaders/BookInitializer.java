package com.bookstore.books.utils.dataloaders;

import com.bookstore.books.models.entities.Author;
import com.bookstore.books.models.entities.Book;
import com.bookstore.books.models.entities.Category;
import com.bookstore.books.models.enums.EStatus;
import com.bookstore.books.repositories.AuthorRepository;
import com.bookstore.books.repositories.BookRepository;
import com.bookstore.books.repositories.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class BookInitializer {

    @Bean
    @Order(2) // Asegura que este inicializador se ejecute después del de categorías y autores
    CommandLineRunner initBooks(
            BookRepository bookRepository,
            AuthorRepository authorRepository,
            CategoryRepository categoryRepository) {

        return args -> {
            // Comprobar si ya hay libros en la base de datos
            if (bookRepository.count() == 0) {
                // Obtener categorías y autores de la base de datos
                List<Category> categories = categoryRepository.findAll();
                List<Author> authors = authorRepository.findAll();

                // Verificar que existen categorías y autores
                if (categories.isEmpty() || authors.isEmpty()) {
                    System.out.println("No se pueden inicializar los libros: faltan categorías o autores");
                    return;
                }

                // Lista para almacenar los libros a guardar
                List<Book> books = new ArrayList<>();

                // Ejemplo de libro 1
                Category novelaCategory = categoryRepository.findByName("Novela").get();
                Author marquezAuthor = authorRepository.findByName("Gabriel García Márquez").get();

                if (novelaCategory != null && marquezAuthor != null) {
                    books.add(new Book(
                            null, // ISBN nulo para que MongoDB genere un ID único
                            "Cien años de soledad",
                            marquezAuthor,
                            novelaCategory,
                            EStatus.AVAILABLE,
                            5,
                            5
                    ));
                }

                // Ejemplo de libro 2
                Category fantasiaCategory = categoryRepository.findByName("Fantasía").get();
                Author rowlingAuthor = authorRepository.findByName("J.K. Rowling").get();

                if (fantasiaCategory != null && rowlingAuthor != null) {
                    books.add(new Book(
                            null, // ISBN nulo
                            "Harry Potter y la piedra filosofal",
                            rowlingAuthor,
                            fantasiaCategory,
                            EStatus.AVAILABLE,
                            3,
                            3
                    ));
                }

                // Ejemplo de libro 3
                Author allendeAuthor = authorRepository.findByName("Isabel Allende").get();

                if (novelaCategory != null && allendeAuthor != null) {
                    books.add(new Book(
                            null, // ISBN nulo
                            "La casa de los espíritus",
                            allendeAuthor,
                            novelaCategory,
                            EStatus.AVAILABLE,
                            2,
                            2
                    ));
                }

                // Ejemplo de libro 4
                Author murakamiAuthor = authorRepository.findByName("Haruki Murakami").get();

                if (novelaCategory != null && murakamiAuthor != null) {
                    books.add(new Book(
                            null, // ISBN nulo
                            "Tokio Blues",
                            murakamiAuthor,
                            novelaCategory,
                            EStatus.LOANED,
                            4,
                            4
                    ));
                }

                // Ejemplo de libro 5
                Category cienciaFiccionCategory = categoryRepository.findByName("Ciencia Ficción").get();

                if (cienciaFiccionCategory != null && murakamiAuthor != null) {
                    books.add(new Book(
                            null, // ISBN nulo
                            "1Q84",
                            murakamiAuthor,
                            cienciaFiccionCategory,
                            EStatus.LOST,
                            2,
                            2
                    ));
                }

                // Ejemplo de libro 6
                Author cortazarAuthor = authorRepository.findByName("Julio Cortázar").get();

                if (novelaCategory != null && cortazarAuthor != null) {
                    books.add(new Book(
                            null, // ISBN nulo
                            "Rayuela",
                            cortazarAuthor,
                            novelaCategory,
                            EStatus.AVAILABLE,
                            3,
                            3
                    ));
                }

                // Guardar todos los libros
                bookRepository.saveAll(books);
                System.out.println("Se han inicializado " + books.size() + " libros correctamente");
            } else {
                System.out.println("Los libros ya estaban inicializados");
            }
        };
    }
}
