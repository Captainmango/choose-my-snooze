package com.org.choosemysnooze.internal;

import an.awesome.pipelinr.Pipeline;
import com.org.choosemysnooze.domain.users.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseController
{
    @Autowired
    protected Pipeline pipeline;

    @Autowired
    protected UserAuthService userAuthService;
}
