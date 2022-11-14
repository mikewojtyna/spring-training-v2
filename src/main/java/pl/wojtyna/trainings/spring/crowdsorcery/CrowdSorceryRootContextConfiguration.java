package pl.wojtyna.trainings.spring.crowdsorcery;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import pl.wojtyna.trainings.spring.crowdsorcery.eventpublisher.EventPublisherModuleConfiguration;
import pl.wojtyna.trainings.spring.crowdsorcery.investor.InvestorModuleConfiguration;

@Configuration
@Import({InvestorModuleConfiguration.class, EventPublisherModuleConfiguration.class})
public class CrowdSorceryRootContextConfiguration {

}
