package ru.js.lesson24.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.js.lesson24.model.Book;
import ru.js.lesson24.model.Genre;

import java.util.List;
import java.util.Optional;

@Component
public class BookJdbcDAO  implements  DAO<Book> {
    private static final Logger log = LoggerFactory.getLogger(BookJdbcDAO.class);
    private JdbcTemplate jdbcTemplate;

    public BookJdbcDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Maps a row in the database to a Course
     */
    RowMapper<Book> rowMapper = (rs, rowNum) -> {
        Book book = new Book();
        book.setId(rs.getLong("id"));
        book.setName(rs.getString("name"));
        book.setGenre_id(rs.getLong("genre_id"));
        book.setAuthor_id(rs.getLong("author_id"));
        return book;
    };

    @Override
    public List<Book> list() {
        String sql = "SELECT id,name,genre_id,author_id from TBL_BOOKS";
        return jdbcTemplate.query(sql,rowMapper);
    }

    @Override
    public void create(Book book) {
        String sql = "insert into TBL_BOOKS(name,genre_id,author_id) values(?,?,?)";
        int insert = jdbcTemplate.update(sql,book.getName(),book.getGenre_id(),book.getAuthor_id());

        if (insert == 1) {
            log.info("New Book Created: " + book.getName());
        }
    }

    @Override
    public Optional<Book> get(long id) {
        String sql = "SELECT id,name,genre_id,author_id from TBL_BOOKS where id = ?";
        Book book = null;

        try {
            book = jdbcTemplate.queryForObject(sql, new Object[]{id}, rowMapper);
        } catch (DataAccessException ex) {
            log.info("Book not found: " + id);
        }
        return Optional.ofNullable(book);
    }

    @Override
    public void update(Book book, long id) {
        String sql = "update TBL_BOOKS set name = ?, genre_id = ?, author_id = ? where id = ?";
        int update = jdbcTemplate.update(sql, book.getName(), book.getGenre_id(), book.getAuthor_id(), id);

        if (update == 1) {
            log.info("Book Updated: " + book.getName());
        }
    }

    @Override
    public void delete(long id) {
        String sql = "delete from TBL_BOOKS where id = ?";
        int delete = jdbcTemplate.update(sql, id);

        if(delete == 1) {
            log.info("Book Deleted: " + id);
        }
    }
}
