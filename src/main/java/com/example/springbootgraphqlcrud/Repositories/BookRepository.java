package com.example.springbootgraphqlcrud.Repositories;

import com.example.springbootgraphqlcrud.Entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> { }
