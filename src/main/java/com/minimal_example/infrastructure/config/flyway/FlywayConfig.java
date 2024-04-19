package com.minimal_example.infrastructure.config.flyway;

import org.flywaydb.core.Flyway;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlywayConfig {

    private final FlywayProperties flywayProperties;

    public FlywayConfig(FlywayProperties flywayProperties) {
        this.flywayProperties = flywayProperties;
    }

    @Bean
    public FlywayMigrationStrategy flywayMigrationStrategy() {
        return flyway ->
                Flyway.configure()
                        .schemas(flywayProperties.getSchema())
                        .locations(flywayProperties.getLocation())
                        .dataSource(flyway.getConfiguration().getDataSource())
                        .load()
                        .migrate();
    }
}
