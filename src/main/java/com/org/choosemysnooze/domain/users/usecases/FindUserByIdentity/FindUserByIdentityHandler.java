package com.org.choosemysnooze.domain.users.usecases.FindUserByIdentity;

import an.awesome.pipelinr.Command;
import an.awesome.pipelinr.Pipeline;
import com.org.choosemysnooze.common.exceptions.NotFoundException;
import com.org.choosemysnooze.domain.users.User;
import com.org.choosemysnooze.domain.users.UserRepository;
import com.org.choosemysnooze.domain.users.usecases.GetIdentity.GetIdentityRequest;
import org.springframework.stereotype.Component;

@Component
public class FindUserByIdentityHandler implements Command.Handler<FindUserByIdentityRequest, User>
{
    private final UserRepository userRepository;

    private final Pipeline pipeline;

    public FindUserByIdentityHandler(UserRepository userRepository, Pipeline pipeline) {
        this.userRepository = userRepository;
        this.pipeline = pipeline;
    }

    @Override
    public User handle(FindUserByIdentityRequest command)
    {
        var identity = new GetIdentityRequest().execute(pipeline);

        return userRepository.findByIdentity(identity)
                .orElseThrow(
                        () -> new NotFoundException("Could not find user with identity " + identity)
                );
    }
}
