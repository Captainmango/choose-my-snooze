package com.org.choosemysnooze.common;

import an.awesome.pipelinr.Pipeline;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseController
{
    @Autowired
    protected Pipeline pipeline;
}
