package com.org.choosemysnooze.domain.users.usecases.GetIdentity;

import an.awesome.pipelinr.Command;
import com.org.choosemysnooze.domain.users.User;
import com.org.choosemysnooze.domain.users.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

@Component
public class GetIdentityUseCase implements Command.Handler<GetIdentityRequest, String>
{
    private final UserRepository userRepository;

    public GetIdentityUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String handle(GetIdentityRequest command)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();

        if (principal instanceof OAuth2User oauth2Principal) {
            var userIdentity = (String) oauth2Principal.getAttributes().get("sub");

            if (! userRepository.existsByIdentity(userIdentity)) {
                var user = User.builder().identity(userIdentity).build();
                userRepository.save(user);
            }

            return userIdentity;
        }

        var defaultPrincipal = (UserDetails) authentication.getPrincipal();

        return defaultPrincipal.getUsername();
    }
}
