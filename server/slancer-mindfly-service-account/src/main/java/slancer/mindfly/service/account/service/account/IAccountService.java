package slancer.mindfly.service.account.service.account;

import slancer.mindfly.service.account.entity.UserEntity;

/**
 * interface IAccountService
 *
 * @author xiayy860612
 * @date 2018/6/13
 */
public interface IAccountService<AT> {

    UserEntity reg(AT info, UserEntity userInfo);

    UserEntity login(AT info);

    void bind(AT info, String userId);
}
