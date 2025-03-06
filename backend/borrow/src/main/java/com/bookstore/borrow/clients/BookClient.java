package com.bookstore.borrow.clients;

import com.bookstore.borrow.models.http.request.BookRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "book-service", url = "http://localhost:8083/api/v1/book")
public interface BookClient {

    @GetMapping("/{isbn}")
    public BookRequestDto getBookByIsbn(@PathVariable String isbn);

    @GetMapping("/title/{title}")
    public BookRequestDto getBookByTitle(@PathVariable String title);
}
