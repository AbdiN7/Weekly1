package com.company.model;

public class Book {
    private Integer bookId;
    private String bookName;
    private int authorId;
    private int publisherId;

    public Book() {
        System.out.println("no parameters set");
    }
    public Book(int bId, String bName, int aId, int pId )
    {
        bookId = bId;
        bookName = bName;
        authorId = aId;
        publisherId = pId;
        }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public int getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(int publisherId) {
        this.publisherId = publisherId;
    }
}
