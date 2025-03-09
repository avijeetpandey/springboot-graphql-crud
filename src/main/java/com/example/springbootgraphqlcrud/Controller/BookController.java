package com.example.springbootgraphqlcrud.Controller;

import com.example.springbootgraphqlcrud.Model.BookDto;
import com.example.springbootgraphqlcrud.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
public class BookController {
    @Autowired
    private BookService bookService;

    @MutationMapping
    public BookDto createBook(@Argument BookInput bookInput) {
        try {
            BookDto bookDto = new BookDto();

            bookDto.setAuthor(bookInput.author());
            bookDto.setTitle(bookInput.title());
            bookDto.setPrice(bookInput.price());

            return bookService.save(bookDto);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public record BookInput(String title, String author, Double price) {}
}
