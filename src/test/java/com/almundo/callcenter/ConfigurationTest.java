package com.almundo.callcenter;

import com.almundo.callcenter.configuration.ApplicationConfiguration;
import com.almundo.callcenter.configuration.PropertyConfig;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Configuration test
 *
 * @author Lorenzo Pinango.
 * @version 1.0-SNAPSHOT
 * @since 1.8
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@Ignore
public class ConfigurationTest {

    @Configuration
    @ComponentScan(
            basePackages = {
                    "com.almundo.callcenter.service"
            },
            basePackageClasses = {
                    PropertyConfig.class,
                    ApplicationConfiguration.class
            }
    )
    public static class Config {

    }

}
