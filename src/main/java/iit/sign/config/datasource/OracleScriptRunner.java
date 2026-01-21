package iit.sign.config.datasource;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@Component
@RequiredArgsConstructor
public class OracleScriptRunner implements ScriptRunner {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void run(String filePath) {
        try {
            ClassPathResource resource = new ClassPathResource(filePath);
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8)
            );

            StringBuilder sqlBlock = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.trim().equals("/")) {
                    execute(sqlBlock.toString());
                    sqlBlock.setLength(0);
                } else {
                    sqlBlock.append(line).append("\n");
                }
            }

        } catch (IOException e) {
            throw new IllegalStateException("SQL script load fail : ", e);
        }
    }

    private void execute(String sql) {
        if (sql.trim().isEmpty()) {
            return;
        }
        jdbcTemplate.execute(sql);
    }
}
