package com.example.sign.config.datasource;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class OracleRepositoryInitializer implements RepositoryInitializer {

    private final EnvironmentChecker checker;
    private final ScriptRunner scriptRunner;

    @PostConstruct
    @Override
    public void initSchema() {
        if (!checker.check()) {
            return;
        }

        scriptRunner.run("db/init-schema.sql");
    }
}
