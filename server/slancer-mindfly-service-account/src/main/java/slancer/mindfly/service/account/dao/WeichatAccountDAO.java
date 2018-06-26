package slancer.mindfly.service.account.dao;

import slancer.mindfly.service.account.entity.UserEntity;
import slancer.mindfly.service.account.entity.account.WeiChatAccountEntity;

/**
 * interface UserDAO
 *
 * @author xiayy860612
 * @date 2018/5/19
 */
public interface WeichatAccountDAO {

    int insert(WeiChatAccountEntity entity);

    WeiChatAccountEntity getById(String openUid);
}
