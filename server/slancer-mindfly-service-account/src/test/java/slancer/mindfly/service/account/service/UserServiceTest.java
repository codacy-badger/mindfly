package slancer.mindfly.service.account.service;

import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import slancer.mindfly.service.account.dao.UserDAO;
import slancer.mindfly.test.AbS2u2mSpringTest;
import slancer.mindfly.service.account.dao.UserDAO;
import slancer.mindfly.service.account.entity.UserEntity;
import slancer.mindfly.service.account.enums.GenderEnum;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;

public class UserServiceTest extends AbS2u2mSpringTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDAO userDAO;

    private String nickName = "test";
    private GenderEnum gender = GenderEnum.Female;

    @Test
    @Transactional
    public void create_success() {
        UserEntity input = new UserEntity()
                .setNickName(nickName)
                .setGender(gender);

        UserEntity entity = userService.create(input);

        assertNotNull(entity.getId());
        assertNotEquals("", entity.getId());
        assertNotNull(entity.getCreateTime());
        assertFalse(entity.getDeleteFlag());

        UserEntity exp = userDAO.getById(entity.getId());
        assertNotNull(exp);
    }

    @Test
    @Transactional
    public void update_success() {
        UserEntity input = new UserEntity()
                .setNickName(nickName)
                .setGender(gender);

        UserEntity exp = userService.create(input);
        String updateNickName = "test_update";

        UserEntity update = new UserEntity()
                .setNickName(updateNickName);

        userService.update(exp.getId(), update);

        UserEntity rst = userDAO.getById(exp.getId());
        assertTrue(rst.getNickName().equals(updateNickName));
    }
}