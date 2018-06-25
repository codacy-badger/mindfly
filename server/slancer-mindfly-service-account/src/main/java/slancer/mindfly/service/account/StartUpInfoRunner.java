package slancer.mindfly.service.account;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * class StartUpInfoRunner
 *
 * @author xiayy860612
 * @date 2018/6/25
 */
@Profile(value = {"local", "dev"})
@Slf4j
@Component
class StartUpInfoRunner implements ApplicationRunner {

    @Value("${server.port}")
    private int serverPort;

    @Value("${mindfly.druid.admin.user}")
    private String adminUser;
    @Value("${mindfly.druid.admin.pwd}")
    private String adminPassword;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // swagger url
        String swaggerUrl = String.format("http://localhost:%s/swagger-ui.html", serverPort);
        log.info(swaggerUrl);

        // druid url
        String druidUrl = String.format("http://localhost:%s/druid/index.html, user: %s, password: %s",
                serverPort, adminUser, adminPassword);
        log.info(druidUrl);
    }
}
