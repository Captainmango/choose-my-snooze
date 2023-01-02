package com.org.choosemysnooze;

import an.awesome.pipelinr.Pipeline;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.choosemysnooze.domain.users.UserAuthService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
public class BaseSystemTest implements CreatesMockUsers
{
    @Spy
    protected Pipeline pipeline;

    @Mock
    protected UserAuthService userAuthService;

    protected ObjectMapper mapper = new ObjectMapper();
}
