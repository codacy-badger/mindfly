package slancer.mindfly.service.account.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import slancer.mindfly.core.config.AbS2u2mServiceConfig;
import slancer.mindfly.service.account.auth.argresolver.UserEntityMethodArgResolver;
import slancer.mindfly.service.account.auth.argresolver.UserEntityMethodArgResolver;

import java.util.List;

/**
 * class AccountServiceConfig
 *
 * @author xiayy860612
 * @date 2018/6/7
 */
@Configuration
public class AccountServiceConfig extends AbS2u2mServiceConfig {

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        super.addArgumentResolvers(argumentResolvers);
        argumentResolvers.add(new UserEntityMethodArgResolver());
    }
}
