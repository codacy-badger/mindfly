package slancer.mindfly.service.account.controller.user;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import slancer.mindfly.core.dto.S2u2mResponseBody;
import slancer.mindfly.service.account.controller.user.dto.UserInfoDTO;
import slancer.mindfly.service.account.entity.UserEntity;
import slancer.mindfly.service.account.service.UserService;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * class UserController
 *
 * @author xiayy860612
 * @date 2018/6/25
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @S2u2mResponseBody
    @GetMapping(value = "/")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = "token",
                    required = true, dataType = "string")
    })
    public UserInfoDTO getUserInfo(UserEntity user) {
        return new UserInfoDTO()
                .setNickName(user.getNickName())
                .setGender(user.getGender())
                .setBirthday(user.getBirthday());
    }

    @S2u2mResponseBody
    @PostMapping(value = "/")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = "token",
                    required = true, dataType = "string")
    })
    public void updateUserInfo(UserEntity user, @RequestBody UserInfoDTO info) {
        UserEntity input = new UserEntity()
                .setNickName(info.getNickName())
                .setBirthday(info.getBirthday())
                .setGender(info.getGender());
        userService.update(user.getId(), input);
    }
}
