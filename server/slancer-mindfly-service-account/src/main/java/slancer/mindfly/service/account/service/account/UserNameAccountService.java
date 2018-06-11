package slancer.mindfly.service.account.service.account;

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
public class UserNameAccountService {

    @Autowired
    private UserService userService;

    @Autowired
    private UserNameFormatCheckProperty accountProperty;

    @Autowired
    private UserNameAccountDAO accountDAO;

    @Autowired
    private UserNameFormatChecker userNameFormatChecker;
    @Autowired
    private PasswordFormatChecker passwordFormatChecker;

    @Transactional
    public UserEntity reg(UserNameAccountRegDTO regDTO) {
        // check username and password
        if (!userNameFormatChecker.check(regDTO.getUserName())
                || !passwordFormatChecker.check(regDTO.getPassword())) {
            throw ExceptionBuilder.build(AccountErrorCodeEnum.UserNameOrPasswordFormatInvalid);
        }

        // check if account existed
        UserNameAccountEntity entity = accountDAO.getByUserName(regDTO.getUserName());
        if (entity != null) {
            throw ExceptionBuilder.build(AccountErrorCodeEnum.UserNameAccountExisted,
                    String.format("username[%s] already existed", regDTO.getUserName()));
        }

        // create user
        UserEntity input = new UserEntity()
                .setNickName(regDTO.getUserName())
                .setGender(regDTO.getGender())
                .setBirthday(regDTO.getBirthday());
        UserEntity output = userService.create(input);

        // bind to account
        entity = new UserNameAccountEntity()
                .setUserId(output.getId())
                .setUserName(regDTO.getUserName())
                .setPassword(regDTO.getPassword());
        accountDAO.insert(entity);

        return output;
    }

    @Transactional(readOnly = true)
    public UserEntity login(UserNameAccountLoginDTO loginDTO) {

        UserNameAccountEntity accountEntity = accountDAO.getByUserName(loginDTO.getUserName());
        if (accountEntity == null) {
            throw ExceptionBuilder.build(AccountErrorCodeEnum.UserNameAccountNotExisted);
        }

        return userService.get(accountEntity.getUserId());
    }
}
