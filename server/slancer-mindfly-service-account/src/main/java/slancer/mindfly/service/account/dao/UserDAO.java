package slancer.mindfly.service.account.dao;

import org.apache.ibatis.annotations.Param;
import slancer.mindfly.service.account.entity.UserEntity;

/**
 * interface UserDAO
 *
 * @author xiayy860612
 * @date 2018/5/19
 */
public interface UserDAO {

    int create(UserEntity entity);

    UserEntity getById(String id);

    int update(@Param("uid") String uid, @Param("entity") UserEntity entity);
}
