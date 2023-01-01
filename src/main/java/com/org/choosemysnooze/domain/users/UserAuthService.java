package com.org.choosemysnooze.domain.users;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class UserAuthService
{
    private final UserRepository userRepository;

    public UserAuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String getIdentity(Authentication authentication)
    {
        OAuth2User principal = (OAuth2User) authentication.getPrincipal();

        var userIdentity = (String) principal.getAttributes().get("sub");

        if (! userRepository.existsByIdentity(userIdentity)) {
            var user = User.builder().identity(userIdentity).build();
            userRepository.save(user);
        }

        return userIdentity;
    }
}
