package iit.sign.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "iit.sign")
@MapperScan(basePackages = {
        "iit.sign.sign.repository",
        "iit.sign.approval.repository",
        "iit.sign.review.repository",
        "iit.sign.step.repository"
})

public class SignAutoImportConfig {

}
