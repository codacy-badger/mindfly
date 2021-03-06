package slancer.mindfly.service.account.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import slancer.mindfly.core.exception.ExceptionBuilder;
import slancer.mindfly.service.account.error.AccountErrorCodeEnum;

/**
 * class IndexController
 *
 * @author xiayy860612
 * @date 2018/5/19
 */
@RestController
@RequestMapping(value = "/")
public class IndexController {

    @GetMapping(value = "/")
    public String index() {
        return "hello world";
    }

    @GetMapping(value = "/unauth")
    public void unAuth() {
        throw ExceptionBuilder.build(AccountErrorCodeEnum.UnAuth, "pls login first");
    }
}
