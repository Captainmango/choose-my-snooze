package com.org.choosemysnooze;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public interface CreatesMockUsers
{
    String DEFAULT_SUBJECT = "123abc";

    default void createMockUser()
    {
        Set<GrantedAuthority> authorities = new HashSet<>(AuthorityUtils.createAuthorityList("USER"));
        OAuth2User oAuth2User = new DefaultOAuth2User(authorities, Map.of(
                "name", "test",
                "sub", DEFAULT_SUBJECT
        ), "name");
        var authenticationToken = new OAuth2AuthenticationToken(oAuth2User, authorities, "cms-app");

        var securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authenticationToken);
    }
}
