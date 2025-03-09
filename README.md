## Springboot CRUD GraphQL :tada: :rocket:
This repository contains the code for developing `GraphQL` application using `SpringBoot`

This repository implements a `CRUD` api to manage books in the database, this uses `Postgres` as main database.

### Schema
```graphql
type Book {
    id: ID!
    title: String!
    author: String!
    price: Float!
}

input BookInput {
    title: String!
    author: String!
    price: Float!
}

type Query {
    findAllBooks: [Book]!
    findBookById(id: ID!): Book
}

type Mutation {
    createBook(bookInput: BookInput!): Book!
    updateBook(id: ID!, bookInput: BookInput!): Book!
    deleteBook(id: ID!): Boolean!
}
```

