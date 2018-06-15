package slancer.mindfly.service.account.controller.account;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.*;
import slancer.mindfly.core.dto.S2u2mResponseBody;
import slancer.mindfly.service.account.controller.account.dto.LoginInfoDTO;
import slancer.mindfly.service.account.controller.account.dto.UserNameAccountLoginDTO;
import slancer.mindfly.service.account.controller.account.dto.UserNameAccountRegDTO;
import slancer.mindfly.service.account.entity.UserEntity;
import slancer.mindfly.service.account.service.account.username.UserNameAccountService;

/**
 * class UserNameAccountController
 *
 * @author xiayy860612
 * @date 2018/5/19
 */
@Profile(value = {"dev", "local", "test"})
@RestController
@RequestMapping(value = "/account/username")
public class UserNameAccountController {

    @Autowired
    private UserNameAccountService accountService;

    @S2u2mResponseBody
    @PostMapping(value = "/reg")
    public LoginInfoDTO reg(@RequestBody UserNameAccountRegDTO regDTO) {
        UserEntity entity = accountService.reg(regDTO);
        // get token
        String token = entity.getId();
        return new LoginInfoDTO().setToken(token);
    }

    @S2u2mResponseBody
    @PostMapping(value = "/login")
    public LoginInfoDTO login(@RequestBody UserNameAccountLoginDTO loginDTO) {
        UserEntity entity = accountService.login(loginDTO);
        return new LoginInfoDTO().setToken(entity.getId());
    }
}
