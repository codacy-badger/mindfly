package slancer.mindfly.service.account.service.account.username;

import slancer.mindfly.service.account.service.account.IAccountService;
import slancer.mindfly.service.account.utils.formatcheck.property.UserNameFormatCheckProperty;
import slancer.mindfly.service.account.dao.UserNameAccountDAO;
import slancer.mindfly.service.account.error.AccountErrorCodeEnum;
import slancer.mindfly.service.account.service.UserService;
import slancer.mindfly.service.account.utils.formatcheck.checkers.PasswordFormatChecker;
import slancer.mindfly.service.account.utils.formatcheck.checkers.UserNameFormatChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import slancer.mindfly.core.exception.ExceptionBuilder;
import slancer.mindfly.service.account.controller.account.dto.UserNameAccountLoginDTO;
import slancer.mindfly.service.account.controller.account.dto.UserNameAccountRegDTO;
import slancer.mindfly.service.account.entity.UserEntity;
import slancer.mindfly.service.account.entity.account.UserNameAccountEntity;
import slancer.mindfly.service.account.entity.account.UserNameAccountEntity;
import slancer.mindfly.service.account.service.UserService;

/**
 * class UserNameAccountService
 *
 * @author xiayy860612
 * @date 2018/5/20
 */
@Service
public class UserNameAccountService
        implements IAccountService<UserNameAccountEntity> {

    @Autowired
    private UserService userService;

    @Autowired
    private UserNameAccountDAO accountDAO;

    @Autowired
    private UserNameFormatChecker userNameFormatChecker;
    @Autowired
    private PasswordFormatChecker passwordFormatChecker;

    @Transactional(rollbackFor = Exception.class)
    public UserEntity reg(UserNameAccountRegDTO regDTO) {

        UserNameAccountEntity accountEntity = new UserNameAccountEntity()
                .setUserName(regDTO.getUserName())
                .setPassword(regDTO.getPassword());

        UserEntity userEntity = new UserEntity()
                .setNickName(regDTO.getUserName())
                .setGender(regDTO.getGender())
                .setBirthday(regDTO.getBirthday());

        return this.reg(accountEntity, userEntity);
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public UserEntity login(UserNameAccountLoginDTO loginDTO) {
        UserNameAccountEntity entity = new UserNameAccountEntity()
                .setUserName(loginDTO.getUserName())
                .setPassword(loginDTO.getPassword());
        return this.login(entity);
    }

    @Override
    public UserEntity reg(UserNameAccountEntity info, UserEntity userInfo) {
        verifyNewAccount(info);

        UserEntity output = userService.create(userInfo);

        // bind to account
        UserNameAccountEntity entity = info.setUserId(output.getId());
        accountDAO.insert(entity);
        return output;
    }

    @Override
    public UserEntity login(UserNameAccountEntity info) {
        UserNameAccountEntity accountEntity = accountDAO.getByUserName(info.getUserName());
        if (accountEntity == null) {
            throw ExceptionBuilder.build(AccountErrorCodeEnum.UserNameAccountNotExisted);
        }

        return userService.get(accountEntity.getUserId());
    }

    @Override
    public void bind(UserNameAccountEntity info, String userId) {
        verifyNewAccount(info);
        UserNameAccountEntity entity = info.setUserId(userId);
        accountDAO.insert(entity);
    }

    private void verifyNewAccount(UserNameAccountEntity regInfo) {
        if (!userNameFormatChecker.check(regInfo.getUserName())
                || !passwordFormatChecker.check(regInfo.getPassword())) {
            throw ExceptionBuilder.build(AccountErrorCodeEnum.UserNameOrPasswordFormatInvalid);
        }

        // check if account existed
        UserNameAccountEntity entity = accountDAO.getByUserName(regInfo.getUserName());
        if (entity != null) {
            throw ExceptionBuilder.build(AccountErrorCodeEnum.UserNameAccountExisted,
                    String.format("username[%s] already existed", regInfo.getUserName()));
        }
    }
}
