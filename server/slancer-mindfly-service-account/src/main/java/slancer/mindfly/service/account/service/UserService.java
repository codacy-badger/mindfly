package slancer.mindfly.service.account.service;

import slancer.mindfly.core.exception.ExceptionBuilder;
import slancer.mindfly.service.account.dao.UserDAO;
import slancer.mindfly.service.account.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import slancer.mindfly.core.uid.SnowFlakeUidGenerator;
import slancer.mindfly.service.account.error.AccountErrorCodeEnum;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.time.Instant;
import java.util.Date;

/**
 * class UserService
 *
 * @author xiayy860612
 * @date 2018/5/20
 */
@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    SnowFlakeUidGenerator uidGenerator;

    @Transactional(rollbackFor = Exception.class)
    public UserEntity create(UserEntity entity) {
        String id = uidGenerator.nextIdByString();
        entity.setId(id);
        entity.setCreateTime(Date.from(Instant.now()));
        entity.setDeleteFlag(false);

        userDAO.create(entity);
        return entity;
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public UserEntity get(String id) {
        return userDAO.getById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public void update(String userId, UserEntity input) {
        UserEntity user = userDAO.getById(userId);
        if (user == null) {
            throw ExceptionBuilder.build(AccountErrorCodeEnum.UserNotExisted,
                    String.format("User[%s] not existed", userId));
        }

        userDAO.update(userId, input);
    }
}
