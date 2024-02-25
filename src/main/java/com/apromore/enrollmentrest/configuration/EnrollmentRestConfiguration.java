package com.apromore.enrollmentrest.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Slf4j
@ConfigurationProperties(prefix = "enrollmentrest")
@Component
public class EnrollmentRestConfiguration {
    // Modify this class and application*.properties in case some other config parameters are needed in other environments
    private String param1;
    private String param2;

    public String getParam1() {
        return param1;
    }

    public void setParam1(String param1) {
        this.param1 = param1;
    }

    public String getParam2() {
        return param2;
    }

    public void setParam2(String param2) {
        this.param2 = param2;
    }
}
