package com.example.springbootgraphqlcrud;

import com.example.springbootgraphqlcrud.Entities.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.GraphQlTester;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureGraphQlTester
public class BookControllerTest {
    @Autowired
    private GraphQlTester graphQlTester;

    @Test
    void createBook() {

        String query = "mutation { createBook(bookInput: {title: \"GraphQL basics\", author: \"Avijeet\", price: 23.44}) { id, title,author } }";


        Book savedBook = graphQlTester.document(query)
                .execute()
                .path("data.createBook")
                .entity(Book.class)
                .get();


        Assertions.assertNotNull(savedBook);
        Assertions.assertEquals("GraphQL basics", savedBook.getTitle());
        Assertions.assertNotNull(savedBook.getId());
    }

    @Test
    void findAllBooks() {
        String query = " { findAllBooks { id title author} }";
        List<Book> books = graphQlTester.document(query)
                .execute()
                .path("data.findAllBooks[*]")
                .entityList(Book.class)
                .get();

        Assertions.assertNotNull(books);
    }

    @Test
    void deleteBook() {
        String query = "mutation { deleteBook(id: 7) }";

        Boolean result = graphQlTester.document(query)
                .execute()
                .path("data.deleteBook")
                .entity(Boolean.class)
                .get();

        Assertions.assertTrue(result);
    }
}
