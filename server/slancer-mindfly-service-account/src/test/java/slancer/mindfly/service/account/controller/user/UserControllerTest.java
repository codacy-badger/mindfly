package slancer.mindfly.service.account.controller.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import slancer.mindfly.service.account.controller.user.dto.UserInfoDTO;
import slancer.mindfly.service.account.entity.UserEntity;
import slancer.mindfly.service.account.enums.GenderEnum;
import slancer.mindfly.service.account.service.UserService;
import slancer.mindfly.test.AbS2u2mControllerTest;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerTest extends AbS2u2mControllerTest {

    @MockBean
    private UserService userService;

    private static final String userId = "123456";
    private static final String nickName = "test001";

    @Before
    public void setUp() {
        UserEntity user = new UserEntity()
                .setId(userId)
                .setNickName(nickName);
        doReturn(user).when(userService).get(anyString());
    }

    @Test
    public void getUserInfo() throws Exception {
        MvcResult rst = mockMvc.perform(get("/user/")
                .contentType(MediaType.APPLICATION_JSON)
                .header("token", userId))
                .andExpect(status().isOk())
                .andDo(documentAPI("getUserInfo"))
                .andReturn();

        UserInfoDTO info = this.convertResponseToObject(rst, UserInfoDTO.class);
        assertEquals(nickName, info.getNickName());
    }

    @Test
    public void updateUserInfo() throws Exception {
        doNothing().when(userService).update(anyString(), any(UserEntity.class));

        UserInfoDTO updateInfo = new UserInfoDTO()
                .setGender(GenderEnum.Female);
        ObjectMapper objectMapper = new ObjectMapper();

        MvcResult rst = mockMvc.perform(post("/user/")
                .contentType(MediaType.APPLICATION_JSON)
                .header("token", userId)
                .content(objectMapper.writeValueAsString(updateInfo)))
                .andExpect(status().isOk())
                .andDo(documentAPI("updateUserInfo"))
                .andReturn();
    }
}