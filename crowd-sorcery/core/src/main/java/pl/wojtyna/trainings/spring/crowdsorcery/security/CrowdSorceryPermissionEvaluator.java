package pl.wojtyna.trainings.spring.crowdsorcery.security;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import pl.wojtyna.trainings.spring.crowdsorcery.portfolio.Portfolio;
import pl.wojtyna.trainings.spring.crowdsorcery.relationships.RelationshipsManager;

import java.io.Serializable;

public class CrowdSorceryPermissionEvaluator implements PermissionEvaluator {

    private final RelationshipsManager relationshipsManager;

    public CrowdSorceryPermissionEvaluator(RelationshipsManager relationshipsManager) {
        this.relationshipsManager = relationshipsManager;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication,
                                 Serializable targetId,
                                 String targetType,
                                 Object permission) {
        if (Portfolio.class.getCanonicalName().equals(targetType)) {
            if ("FRIEND_OF".equals(permission)) {
                if (targetId instanceof String targetInvestorId) {
                    return relationshipsManager.areFriends(targetInvestorId, authentication.getName());
                }
            }
        }
        return false;
    }
}
