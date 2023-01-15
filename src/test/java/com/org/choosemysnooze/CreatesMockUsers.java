package com.org.choosemysnooze;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public interface CreatesMockUsers
{
    String DEFAULT_SUBJECT = "123abc";

    default void createMockUser()
    {
        Set<GrantedAuthority> authorities = new HashSet<>(AuthorityUtils.createAuthorityList("USER"));
        var timeIssued = Instant.now();
        var timeExpire = timeIssued.plus(2, ChronoUnit.DAYS);

        var jwtUser = new Jwt(
                DEFAULT_SUBJECT,
                timeIssued,
                timeExpire,
                Map.of(
                        "alg", "RS256",
                        "typ", "JWT",
                        "kid", "fffffff"
                ),
                Map.of(
                        "realm_access", """
                                            {
                                            "roles": [
                                            "default-roles-choose-my-snooze",
                                            "offline_access",
                                            "uma_authorization",
                                            "USER"
                                            ]}"
                                            """,
                        "email", "test@test.com"
                )
        );

        var authenticationToken = new JwtAuthenticationToken(jwtUser, authorities, "cms-app");

        var securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authenticationToken);
    }
}
