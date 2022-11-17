package pl.wojtyna.trainings.spring.crowdsorcery.investor.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import pl.wojtyna.trainings.spring.crowdsorcery.investor.profile.InvestorProfile;
import pl.wojtyna.trainings.spring.crowdsorcery.investor.service.Investor;

import java.util.List;

public class JdbcInvestorRepository implements InvestorRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcInvestorRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public void save(Investor investor) {
        jdbcTemplate.update("INSERT INTO investors VALUES(?, ?, ?)", investor.id(), investor.name(), false);
        var investorProfile = investor.investorProfile();
        jdbcTemplate.update("INSERT INTO investor_profiles VALUES(?, ?, ?, ?, ?, ?)",
                            investor.id(),
                            investorProfile.isVip(),
                            investorProfile.score(),
                            investorProfile.referralLink(), investor.id(), false);
    }

    @Override
    public List<Investor> findAll() {
        return jdbcTemplate.query("""
                                  SELECT investors.id, investors.name, investor_profiles.isVip, investor_profiles.score, investor_profiles.ref_link
                                  FROM investors
                                  INNER JOIN investor_profiles
                                  ON investor_profiles.investor_id=investors.id
                                  """, (rs, rowNum) -> {
            var id = rs.getString("id");
            var name = rs.getString("name");
            var isVip = rs.getBoolean("isVip");
            var score = rs.getInt("score");
            var refLink = rs.getString("ref_link");
            return new Investor(id, name, new InvestorProfile(score, isVip, refLink));
        });
    }
}
