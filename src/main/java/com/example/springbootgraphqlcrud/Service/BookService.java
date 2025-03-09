package com.example.springbootgraphqlcrud.Service;

import com.example.springbootgraphqlcrud.Entities.Book;
import com.example.springbootgraphqlcrud.Model.BookDto;
import com.example.springbootgraphqlcrud.Repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public Boolean deleteById(Long id) {
        // check if the book exists in the first place before deleting
        Optional<Book> optionalBook = bookRepository.findById(id);

        if(optionalBook.isPresent()) {
            bookRepository.deleteById(id);
            return true;
        }

        return false;
    }

    public BookDto findById(Long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);

        if(optionalBook.isPresent()) {
            Book book = optionalBook.get();
            BookDto bookDto = new BookDto();

            bookDto.setId(book.getId());
            bookDto.setAuthor(book.getAuthor());
            bookDto.setTitle(book.getTitle());
            bookDto.setPrice(book.getPrice());

            return bookDto;
        }

        return null;
    }

    public List<BookDto> findAll() {
        List<Book> books = bookRepository.findAll();
        List<BookDto> bookDtos = new ArrayList<>();

        books.forEach(book -> {
            BookDto bookDto = new BookDto();
            bookDto.setId(book.getId());
            bookDto.setAuthor(book.getAuthor());
            bookDto.setTitle(book.getTitle());
            bookDto.setPrice(book.getPrice());

            bookDtos.add(bookDto);
        });

        return bookDtos;
    }

    public BookDto update(BookDto bookDto) {
        Optional<Book> optionalBook = bookRepository.findById(bookDto.getId());
        if(optionalBook.isPresent()) {
            Book book = optionalBook.get();

            book.setTitle(bookDto.getTitle());
            book.setAuthor(bookDto.getAuthor());
            book.setPrice(bookDto.getPrice());

            Book savedBook = bookRepository.save(book);

            return new BookDto(savedBook.getId(),
                               savedBook.getAuthor(),
                               savedBook.getTitle(),
                               savedBook.getPrice());
        }

        return null;
    }
}
