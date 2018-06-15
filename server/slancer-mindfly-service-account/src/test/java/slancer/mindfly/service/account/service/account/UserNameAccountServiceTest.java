package slancer.mindfly.service.account.service.account;

import slancer.mindfly.service.account.service.UserService;
import slancer.mindfly.service.account.controller.account.dto.UserNameAccountRegDTO;
import slancer.mindfly.service.account.dao.UserNameAccountDAO;
import slancer.mindfly.service.account.entity.account.UserNameAccountEntity;
import slancer.mindfly.service.account.enums.GenderEnum;
import slancer.mindfly.service.account.service.account.username.UserNameAccountService;
import slancer.mindfly.test.AbS2u2mSpringTest;
import slancer.mindfly.service.account.entity.UserEntity;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doReturn;

public class UserNameAccountServiceTest extends AbS2u2mSpringTest {

    @Autowired
    private UserNameAccountService accountService;

    @MockBean
    private UserService userService;

    @MockBean
    private UserNameAccountDAO accountDAO;

    @Test
    public void reg__success() {
        String nickName = "test123456";
        String password = "test@123456";
        String id = "123456";
        GenderEnum gender = GenderEnum.Female;

        // mock
        doReturn(null).when(accountDAO).getByUserName(anyString());
        doReturn(1).when(accountDAO).insert(any(UserNameAccountEntity.class));

        doReturn(new UserEntity().setId(id).setNickName(nickName))
                .when(userService).create(any(UserEntity.class));

        UserNameAccountRegDTO regDTO = new UserNameAccountRegDTO()
                .setUserName(nickName)
                .setPassword(password)
                .setGender(gender);

        UserEntity entity = accountService.reg(regDTO);

        assertTrue(entity.getId().equals(id));
        assertNotNull(entity.getCreateTime());
        assertFalse(entity.getDeleteFlag());

        assertTrue(entity.getNickName().equals(nickName));
    }
}