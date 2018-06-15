package slancer.mindfly.service.account.service;

import slancer.mindfly.service.account.dao.UserDAO;
import slancer.mindfly.service.account.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import slancer.mindfly.core.uid.SnowFlakeUidGenerator;

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

    @Transactional
    public UserEntity create(UserEntity entity) {
        String id = uidGenerator.nextIdByString();
        entity.setId(id);

        userDAO.insert(entity);
        return entity;
    }

    @Transactional(readOnly = true)
    public UserEntity get(String id) {
        return userDAO.getById(id);
    }
}
