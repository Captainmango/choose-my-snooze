package com.org.choosemysnooze;

import an.awesome.pipelinr.Pipeline;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
public class BaseSystemTest implements CreatesMockUsers
{
    @Spy
    protected Pipeline pipeline;

    protected ObjectMapper mapper = new ObjectMapper();
}
