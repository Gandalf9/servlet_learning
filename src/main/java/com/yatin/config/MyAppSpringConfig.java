package com.yatin.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"com.yatin.resource","com.yatin.service"})
public class MyAppSpringConfig {

}
