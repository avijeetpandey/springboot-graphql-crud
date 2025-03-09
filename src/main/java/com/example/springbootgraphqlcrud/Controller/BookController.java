package com.example.springbootgraphqlcrud.Controller;

import com.example.springbootgraphqlcrud.Model.BookDto;
import com.example.springbootgraphqlcrud.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

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

    @QueryMapping
    public List<BookDto> findAllBooks() {
        return bookService.findAll();
    }

    @QueryMapping
    public BookDto findBookById(@Argument Long id) {
        return bookService.findById(id);
    }

    @MutationMapping
    public Boolean deleteBook(@Argument Long id) {
        return bookService.deleteById(id);
    }

    @MutationMapping
    public BookDto updateBook(@Argument Long id, @Argument BookInput bookInput) {
        BookDto bookDto = new BookDto();

        bookDto.setAuthor(bookInput.author());
        bookDto.setTitle(bookInput.title());
        bookDto.setPrice(bookInput.price());
        bookDto.setId(id);

        return bookService.update(bookDto);
    }

    public record BookInput(String title, String author, Double price) {}
}
