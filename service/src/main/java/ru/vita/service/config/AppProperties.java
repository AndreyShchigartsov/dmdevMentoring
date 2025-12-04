package ru.vita.service.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app")
public record AppProperties(String filename,
                           String filename2,
                           String filename3) {
}
