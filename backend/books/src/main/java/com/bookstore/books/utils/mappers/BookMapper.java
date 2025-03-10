package com.bookstore.books.utils.mappers;

import com.bookstore.books.models.dtos.BookDto;
import com.bookstore.books.models.entities.Author;
import com.bookstore.books.models.entities.Book;
import com.bookstore.books.models.entities.Category;
import com.bookstore.books.models.http.response.BookResponseDto;
import com.bookstore.books.repositories.AuthorRepository;
import com.bookstore.books.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class BookMapper implements IDtoMapper<Book, BookDto, BookResponseDto> {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public Book dtoToEntity(BookDto dto) {
        Optional<Category> category = categoryRepository.findByName(dto.getCategoryName());
        Optional<Author> author = authorRepository.findByName(dto.getAuthorName());
        Category newCategory = new Category();
        Author newAuthor = new Author();
        if(category.isEmpty()) {
             newCategory.setName("Sin categoría");
        }else {
            newCategory = category.get();
        }

        if(author.isEmpty()) {
            newAuthor.setName("Sin autor");
        }else {
            newAuthor = author.get();
        }
            return Book.builder()
                    .isbn(dto.getIsbn())
                    .title(dto.getTitle())
                    .author(newAuthor)
                    .available_quantity(dto.getAvailable_quantity())
                    .total_quantity(dto.getTotal_quantity())
                    .category(newCategory)
                    .status(dto.getStatus())
                    .build();

    }

    @Override
    public BookDto entityToDto(Book entity) {

        return BookDto.builder()
                .isbn(entity.getIsbn())
                .title(entity.getTitle())
                .authorName(entity.getAuthor().getName())
                .available_quantity(entity.getAvailable_quantity())
                .total_quantity(entity.getTotal_quantity())
                .categoryName(entity.getCategory().getName())
                .status(entity.getStatus())
                .build();
    }

    @Override
    public BookResponseDto entityToResponseDto(Book entity, String message, HttpStatus status) {
        return null;
    }

    @Override
    public List<BookDto> entityListToResponseDtoList(List<Book> entityList) {
        return entityList
                .stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }
}
