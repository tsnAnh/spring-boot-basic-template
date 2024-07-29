package com.example.demo;

import com.example.demo.config.ApplicationProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.env.ConfigurableEnvironment;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Optional;

@SpringBootApplication
@EnableConfigurationProperties(ApplicationProperties.class)
public class DemoApplication {
    private static final Logger logger = LoggerFactory.getLogger(DemoApplication.class);

    public static void main(String[] args) {
        System.out.println("Environment: " + System.getenv());
        final var env = SpringApplication.run(DemoApplication.class, args).getEnvironment();

        logApplicationStartup(env);
    }

    private static void logApplicationStartup(ConfigurableEnvironment env) {
        final var protocol = "http";
        final var port = env.getProperty("server.port");
        final var contextPath = Optional.ofNullable(env.getProperty("server.servlet.context-path")).orElse("/");
        var hostAddress = "localhost";
        try {
            hostAddress = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            logger.error("Hostname can not be resolved, localhost will be used as fallback");
        }

        final var info = """
                ----------------------------------------------------------
                Application is running!
                Access URLs:
                Local: \t\t%s://localhost:%s%s
                External: \t%s://%s:%s%s
                Profile(s): \t%s
                ----------------------------------------------------------
                """;
        System.out.printf(info, protocol, port, contextPath, protocol, hostAddress, port, contextPath, Arrays.toString(env.getActiveProfiles()));
    }
}
