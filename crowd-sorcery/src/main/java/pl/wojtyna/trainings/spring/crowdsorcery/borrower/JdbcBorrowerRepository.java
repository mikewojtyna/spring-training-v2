package pl.wojtyna.trainings.spring.crowdsorcery.borrower;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class JdbcBorrowerRepository implements BorrowerRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcBorrowerRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public void save(Borrower borrower) {
        jdbcTemplate.update("INSERT INTO borrowers VALUES(?, ?, ?)", borrower.id(), borrower.name(), false);
    }

    @Override
    public List<Borrower> findAll() {
        return jdbcTemplate.query("""
                                  SELECT id, name FROM borrowers
                                  """,
                                  (rs, rowNum) -> {
                                      var id = rs.getString("id");
                                      var name = rs.getString("name");
                                      return new Borrower(id, name);
                                  });
    }
}
