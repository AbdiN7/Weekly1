package com.company.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Author {
    private Integer authorId;
    private String authorName;

    private List<Book> books;


    public Author () {
        this.books = new ArrayList<Book>();
    }
    public Author (Integer aId, String aName)
    {
        authorId = aId;
        authorName = aName;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
}
