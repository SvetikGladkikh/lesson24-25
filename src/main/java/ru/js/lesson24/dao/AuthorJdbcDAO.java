package ru.js.lesson24.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.js.lesson24.model.Author;
import ru.js.lesson24.model.Genre;

import java.util.List;
import java.util.Optional;

@Component
public class AuthorJdbcDAO implements DAO<Author>{

    private static final Logger log = LoggerFactory.getLogger(AuthorJdbcDAO.class);
    private JdbcTemplate jdbcTemplate;

    public AuthorJdbcDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Maps a row in the database to a Course
     */
    RowMapper<Author> rowMapper = (rs, rowNum) -> {
        Author author = new Author();
        author.setId(rs.getLong("id"));
        author.setFirstName(rs.getString("first_name"));
        author.setLastName(rs.getString("last_name"));
        return author;
    };

    @Override
    public List<Author> list() {
        String sql = "SELECT id,first_name,last_name from TBL_AUTHOR";
        return jdbcTemplate.query(sql,rowMapper);
    }

    @Override
    public void create(Author author) {
        String sql = "insert into TBL_AUTHOR(first_name,last_name) values(?,?)";
        int insert = jdbcTemplate.update(sql,author.getFirstName(),author.getLastName());

        if (insert == 1) {
            log.info("New Author Created: " + author.getFirstName() + author.getLastName());
        }
    }

    @Override
    public Optional<Author> get(long id) {
        String sql = "SELECT id,first_name,last_name from TBL_AUTHOR where id = ?";
        Author author = null;

        try {
            author = jdbcTemplate.queryForObject(sql, new Object[]{id}, rowMapper);
        } catch (DataAccessException ex) {
            log.info("Author not found: " + id);
        }
        return Optional.ofNullable(author);
    }

    @Override
    public void update(Author author, long id) {
        String sql = "update TBL_AUTHOR set first_name = ?, last_name = ? where id = ?";
        int update = jdbcTemplate.update(sql, author.getFirstName(), author.getLastName(), id);

        if (update == 1) {
            log.info("Author Updated: " + author.getFirstName() + author.getLastName());
        }
    }

    @Override
    public void delete(long id) {
        String sql = "delete from TBL_AUTHOR where id = ?";
        int delete = jdbcTemplate.update(sql, id);

        if(delete == 1) {
            log.info("Author Deleted: " + id);
        }
    }
}
