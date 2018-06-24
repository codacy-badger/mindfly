package slancer.mindfly.service.account.service.account;

import slancer.mindfly.service.account.entity.UserEntity;

/**
 * interface IAccountService
 *
 * @author xiayy860612
 * @date 2018/6/13
 */
public interface IAccountService<LT, RT, AT> {

    UserEntity reg(RT info);

    UserEntity login(LT info);

    void bind(AT account, UserEntity user);
}
