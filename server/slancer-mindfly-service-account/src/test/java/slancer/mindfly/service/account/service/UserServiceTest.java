package slancer.mindfly.service.account.service;

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

    @MockBean
    private UserDAO userDAO;

    @Test
    public void create_success() {
        String nickName = "test";
        GenderEnum gender = GenderEnum.Female;

        doReturn(1).when(userDAO).insert(any(UserEntity.class));

        UserEntity input = new UserEntity()
                .setNickName(nickName)
                .setGender(gender);

        UserEntity entity = userService.create(input);

        assertNotNull(entity.getId());
        assertNotEquals("", entity.getId());

        assertNotNull(entity.getCreateTime());
        assertFalse(entity.getDeleteFlag());
    }
}