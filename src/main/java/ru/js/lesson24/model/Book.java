package ru.js.lesson24.model;

public class Book {
    private long id;
    private String name;
    private long author_id;
    private long genre_id;

    public Book() {
    }

    public Book(String name, long author_id, long genre_id) {
        this.name = name;
        this.author_id = author_id;
        this.genre_id = genre_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(long author_id) {
        this.author_id = author_id;
    }

    public long getGenre_id() {
        return genre_id;
    }

    public void setGenre_id(long genre_id) {
        this.genre_id = genre_id;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author_id=" + author_id +
                ", genre_id=" + genre_id +
                '}';
    }
}
