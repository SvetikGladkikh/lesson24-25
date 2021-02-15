package ru.js.lesson24.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.js.lesson24.model.Genre;

import java.util.List;
import java.util.Optional;

@Component
public class GenreJdbcDAO implements DAO<Genre>{
    private static final Logger log = LoggerFactory.getLogger(GenreJdbcDAO.class);
    private JdbcTemplate jdbcTemplate;

    public GenreJdbcDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Maps a row in the database to a Course
     */
    RowMapper<Genre> rowMapper = (rs, rowNum) -> {
        Genre genre = new Genre();
        genre.setId(rs.getLong("id"));
        genre.setGenre(rs.getString("genre"));
        return genre;
    };

    @Override
    public List<Genre> list() {
        String sql = "SELECT id,genre from TBL_GENRE";
        return jdbcTemplate.query(sql,rowMapper);
    }

    @Override
    public void create(Genre genre) {
        String sql = "insert into TBL_GENRE(genre) values(?)";
        int insert = jdbcTemplate.update(sql,genre.getGenre());

        if (insert == 1) {
            log.info("New Genre Created: " + genre.getGenre());
        }
    }

    @Override
    public Optional<Genre> get(long id) {
        String sql = "SELECT id,genre from TBL_GENRE where id = ?";
        Genre genre = null;

        try {
            genre = jdbcTemplate.queryForObject(sql, new Object[]{id}, rowMapper);
        } catch (DataAccessException ex) {
            log.info("Genre not found: " + id);
        }
        return Optional.ofNullable(genre);
    }

    @Override
    public void update(Genre genre, long id) {
        String sql = "update TBL_GENRE set genre = ? where id = ?";
        int update = jdbcTemplate.update(sql, genre.getGenre(), id);

        if (update == 1) {
            log.info("Genre Updated: " + genre.getGenre());
        }
    }

    @Override
    public void delete(long id) {
        String sql = "delete from TBL_GENRE where id = ?";
        int delete = jdbcTemplate.update(sql, id);

        if(delete == 1) {
            log.info("Genre Deleted: " + id);
        }
    }
}
