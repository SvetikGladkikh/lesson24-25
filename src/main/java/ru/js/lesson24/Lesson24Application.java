package ru.js.lesson24;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.js.lesson24.dao.DAO;
import ru.js.lesson24.model.Author;
import ru.js.lesson24.model.Book;
import ru.js.lesson24.model.Genre;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@SpringBootApplication
public class Lesson24Application {

    private static DAO<Author> daoAuthor;
    private static DAO<Genre> daoGenre;
    private static DAO<Book> daoBook;

    @Autowired
    public Lesson24Application(DAO<Author> daoAuthor, DAO<Genre> daoGenre, DAO<Book> daoBook) {
        this.daoAuthor = daoAuthor;
        this.daoBook = daoBook;
        this.daoGenre = daoGenre;
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(Lesson24Application.class, args);
        Lesson24Application runnerInstance = run.getBean(Lesson24Application.class);
        runnerInstance.init();
    }

    private void init(){
        try(Scanner in = new Scanner(System.in)){
            mainMenu(in);
        }
    }

    private void mainMenu(Scanner in){
        System.out.println();
        System.out.println("Choose entity type: ");
        System.out.println("1. Authors");
        System.out.println("2. Books");
        System.out.println("3. Genres");
        System.out.println("0. Quit");
        int entityTypeNum = in.nextInt();
        switch (entityTypeNum){
            case 1: {
                processAuthorEntityType(in);
                break;
            }
            case 2: {
                processBookEntityType(in);
                break;
            }
            case 3: {
                processGenreEntityType(in);
                break;
            }
            case 0: System.exit(0);
        }
    }

    private void processBookEntityType(Scanner in) {
        System.out.println();
        System.out.println("Choose action: ");
        System.out.println("1. Get book by ID");
        System.out.println("2. Get all books");
        System.out.println("3. Create book");
        System.out.println("4. Update book");
        System.out.println("5. Delete book");
        System.out.println("0. To main menu");
        int actionNum = in.nextInt();
        switch (actionNum){
            case 1: {
                getBookById(in);
                processBookEntityType(in);
                break;
            }
            case 2: {
                getAllBooks(in);
                processBookEntityType(in);
                break;
            }
            case 3: {
                createBook(in);
                processBookEntityType(in);
                break;
            }
            case 4: {
                updateBook(in);
                processBookEntityType(in);
                break;
            }
            case 5: {
                deleteBook(in);
                processBookEntityType(in);
                break;
            }
            case 0: {
                mainMenu(in);
                break;
            }
        }
    }

    private void updateBook(Scanner in) {
        System.out.print("Enter book ID: ");
        int bookId = in.nextInt();

        System.out.print("Enter book name: ");
        String name = in.next();

        System.out.print("Enter genre id: ");
        Long genre_id = in.nextLong();

        System.out.print("Enter author id: ");
        Long author_id = in.nextLong();

        Book book = new Book(name,author_id,genre_id);
        daoBook.update(book,bookId);
        System.out.println("Book updated success.");
    }

    private void deleteBook(Scanner in) {
        System.out.print("Enter book ID: ");
        long bookId = in.nextLong();

        daoBook.delete(bookId);
        System.out.println("Genre deleted success.");
    }

    private void createBook(Scanner in) {
        System.out.print("Enter book name: ");
        String name = in.next();

        System.out.print("Enter genre id: ");
        Long genre_id = in.nextLong();

        System.out.print("Enter author id: ");
        Long author_id = in.nextLong();

        Book book = new Book(name,author_id,genre_id);
        daoBook.create(book);
        System.out.println("Book created success.");
    }

    private void getAllBooks(Scanner in) {
        List<Book> books = daoBook.list();
        books.forEach(System.out::println);
    }

    private void getBookById(Scanner in) {
        System.out.print("Enter book ID: ");
        int bookId = in.nextInt();
        Optional<Book> firstBook = daoBook.get(bookId);
        System.out.println("Genre: " + firstBook.get());
    }

    private void processGenreEntityType(Scanner in) {
        System.out.println();
        System.out.println("Choose action: ");
        System.out.println("1. Get genre by ID");
        System.out.println("2. Get all genres");
        System.out.println("3. Create genre");
        System.out.println("4. Update genre");
        System.out.println("5. Delete genre");
        System.out.println("0. To main menu");
        int actionNum = in.nextInt();
        switch (actionNum){
            case 1: {
                getGenreById(in);
                processGenreEntityType(in);
                break;
            }
            case 2: {
                getAllGenres(in);
                processGenreEntityType(in);
                break;
            }
            case 3: {
                createGenre(in);
                processGenreEntityType(in);
                break;
            }
            case 4: {
                updateGenre(in);
                processGenreEntityType(in);
                break;
            }
            case 5: {
                deleteGenre(in);
                processGenreEntityType(in);
                break;
            }
            case 0: {
                mainMenu(in);
                break;
            }
        }
    }

    private void deleteGenre(Scanner in) {
        System.out.print("Enter genre ID: ");
        int genreId = in.nextInt();

        daoGenre.delete(genreId);
        System.out.println("Genre deleted success.");
    }

    private void updateGenre(Scanner in) {
        System.out.print("Enter genre ID: ");
        int genreId = in.nextInt();
        System.out.print("Enter genre: ");
        String genreStr = in.next();

        Genre genre = new Genre(genreStr);
        daoGenre.update(genre, genreId);
        System.out.println("Genre updated success.");
    }

    private void createGenre(Scanner in) {
        System.out.print("Enter genre: ");
        String genreStr = in.next();

        Genre genre = new Genre(genreStr);
        daoGenre.create(genre);
        System.out.println("Genre created success.");
    }

    private void getAllGenres(Scanner in) {
        List<Genre> genres = daoGenre.list();
        genres.forEach(System.out::println);
    }

    private void getGenreById(Scanner in) {
        System.out.print("Enter genre ID: ");
        int genreId = in.nextInt();
        Optional<Genre> firstGenre = daoGenre.get(genreId);
        System.out.println("Genre: " + firstGenre.get());
    }



    private void processAuthorEntityType(Scanner in){
        System.out.println();
        System.out.println("Choose action: ");
        System.out.println("1. Get author by ID");
        System.out.println("2. Get all authors");
        System.out.println("3. Create author");
        System.out.println("4. Update author");
        System.out.println("5. Delete author");
        System.out.println("0. To main menu");
        int actionNum = in.nextInt();
        switch (actionNum){
            case 1: {
                getAuthorById(in);
                processAuthorEntityType(in);
                break;
            }
            case 2: {
                getAllAuthors(in);
                processAuthorEntityType(in);
                break;
            }
            case 3: {
                createAuthor(in);
                processAuthorEntityType(in);
                break;
            }
            case 4: {
                updateAuthor(in);
                processAuthorEntityType(in);
                break;
            }
            case 5: {
                deleteAuthor(in);
                processAuthorEntityType(in);
                break;
            }
            case 0: {
                mainMenu(in);
                break;
            }
        }
    }

    private void getAuthorById(Scanner in){
        System.out.print("Enter author ID: ");
        int authorId = in.nextInt();
        Optional<Author> firstAuthor = daoAuthor.get(authorId);
        System.out.println("Author: " + firstAuthor.get());
    }

    private void createAuthor(Scanner in){
        System.out.print("Enter author first name: ");
        String authorFirstName = in.next();
        System.out.print("Enter author last name: ");
        String authorLastName = in.next();

        Author author = new Author(authorFirstName, authorLastName);
        daoAuthor.create(author);
        System.out.println("Author created success.");
    }

    private void updateAuthor(Scanner in){
        System.out.print("Enter author ID: ");
        int authorId = in.nextInt();
        System.out.print("Enter author first name: ");
        String authorFirstName = in.next();
        System.out.print("Enter author last name: ");
        String authorLastName = in.next();

        Author author = new Author(authorFirstName, authorLastName);
        daoAuthor.update(author, authorId);
        System.out.println("Author updated success.");
    }

    private void deleteAuthor(Scanner in){
        System.out.print("Enter author ID: ");
        int authorId = in.nextInt();

        daoAuthor.delete(authorId);
        System.out.println("Author deleted success.");
    }

    private void getAllAuthors(Scanner in){
        List<Author> authors = daoAuthor.list();
        authors.forEach(System.out::println);
    }

}
