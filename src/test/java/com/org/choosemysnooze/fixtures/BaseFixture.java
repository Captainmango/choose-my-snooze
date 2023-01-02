package com.org.choosemysnooze.fixtures;

import org.springframework.stereotype.Component;

@Component
public interface BaseFixture
{
    void run() throws Exception;
}
