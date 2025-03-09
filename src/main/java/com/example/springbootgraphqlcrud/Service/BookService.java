package com.example.springbootgraphqlcrud.Service;

import com.example.springbootgraphqlcrud.Entities.Book;
import com.example.springbootgraphqlcrud.Model.BookDto;
import com.example.springbootgraphqlcrud.Repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public BookDto save(BookDto bookDto) {
        // constructing book object from the DTO
        Book book = new Book();
        book.setAuthor(bookDto.getAuthor());
        book.setTitle(bookDto.getTitle());
        book.setPrice(bookDto.getPrice());

        Book savedBook = bookRepository.save(book);

        BookDto savedBookDto = new BookDto();

        savedBookDto.setAuthor(savedBook.getAuthor());
        savedBookDto.setTitle(savedBook.getTitle());
        savedBookDto.setPrice(savedBook.getPrice());
        savedBookDto.setId(savedBook.getId());

        return savedBookDto;
    }
}
