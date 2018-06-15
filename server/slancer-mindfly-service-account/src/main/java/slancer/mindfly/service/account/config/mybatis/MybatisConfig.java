package slancer.mindfly.service.account.config.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("slancer.mindfly.service.account.dao")
public class MybatisConfig {
}
