package pl.wojtyna.trainings.spring.crowdsorcery.investor.repository.mongo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import pl.wojtyna.trainings.spring.crowdsorcery.investor.service.Investor;

import java.util.stream.Stream;

public interface MongoSpringInvestorRepository extends MongoRepository<Investor, String>, AdvancedNameFinderRepository {

    Stream<Investor> findAllByName(String name);

    Page<Investor> findByName(String name, Pageable pageable);

    Stream<Investor> findAllByNameContaining(String substring);

    Stream<Investor> findAllByInvestorProfileIsVip(boolean isVip);

    Stream<Investor> findAllByInvestorProfileScore(int score);

    Stream<Investor> findByInvestorProfileScoreGreaterThanEqual(int score);

    @Query("{ 'investorProfile.referralLink': {$regex: 'refLink=:#{#refLink}'} }")
    Stream<Investor> findByInvestorProfileReferralLink(String refLink);
}
