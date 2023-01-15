package com.org.choosemysnooze.domain.users.usecases.GetIdentity;

import an.awesome.pipelinr.Command;
import com.org.choosemysnooze.domain.users.User;
import com.org.choosemysnooze.domain.users.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Component
public class GetIdentityUseCase implements Command.Handler<GetIdentityRequest, String>
{
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final UserRepository userRepository;

    public GetIdentityUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String handle(GetIdentityRequest command)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        try {
            Jwt principal = (Jwt) authentication.getPrincipal();

            var userIdentity = (String) principal.getClaims().get("sub");

            if (! userRepository.existsByIdentity(userIdentity)) {
                var user = User.builder().identity(userIdentity).build();
                userRepository.save(user);
            }

            return userIdentity;
        } catch (Exception exception) {
            logger.error("Authentication wasn't what was expected. Expected Jwt. Got {}", authentication.getClass());

            throw exception;
        }
    }
}
