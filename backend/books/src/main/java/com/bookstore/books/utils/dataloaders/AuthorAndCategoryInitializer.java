package com.bookstore.books.utils.dataloaders;

import com.bookstore.books.models.entities.Author;
import com.bookstore.books.models.entities.Category;
import com.bookstore.books.repositories.AuthorRepository;
import com.bookstore.books.repositories.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class AuthorAndCategoryInitializer {

    @Bean
    CommandLineRunner initDatabase(CategoryRepository categoryRepository, AuthorRepository authorRepository) {
        return args -> {
            // Inicializar categorías si la colección está vacía
            if (categoryRepository.count() == 0) {
                List<Category> categories = Arrays.asList(
                        new Category(null, "Novela"),
                        new Category(null, "Ciencia Ficción"),
                        new Category(null, "Fantasía"),
                        new Category(null, "Historia"),
                        new Category(null, "Biografía"),
                        new Category(null, "Ensayo"),
                        new Category(null, "Poesía"),
                        new Category(null, "Misterio"),
                        new Category(null, "Romántica"),
                        new Category(null, "Aventura")
                );
                categoryRepository.saveAll(categories);
                System.out.println("Categorías inicializadas correctamente");
            } else {
                System.out.println("Las categorías ya estaban inicializadas");
            }

            // Inicializar autores si la colección está vacía
            if (authorRepository.count() == 0) {
                List<Author> authors = Arrays.asList(
                        new Author(
                                null,
                                "Gabriel García Márquez",
                                "Escritor colombiano, premio Nobel de Literatura en 1982. Conocido por obras como 'Cien años de soledad'.",
                                "Colombia"
                        ),
                        new Author(
                                null,
                                "J.K. Rowling",
                                "Escritora británica, autora de la saga Harry Potter que ha vendido más de 500 millones de ejemplares.",
                                "Reino Unido"
                        ),
                        new Author(
                                null,
                                "Isabel Allende",
                                "Escritora chilena, conocida por novelas como 'La casa de los espíritus' y 'Eva Luna'.",
                                "Chile"
                        ),
                        new Author(
                                null,
                                "Haruki Murakami",
                                "Escritor japonés de renombre internacional, conocido por su estilo surrealista y obras como 'Tokio Blues'.",
                                "Japón"
                        ),
                        new Author(
                                null,
                                "Julio Cortázar",
                                "Escritor argentino, maestro del relato corto y autor de 'Rayuela'.",
                                "Argentina"
                        )
                );

                authorRepository.saveAll(authors);
                System.out.println("Autores inicializados correctamente");
            } else {
                System.out.println("Los autores ya estaban inicializados");
            }
        };
    }
}