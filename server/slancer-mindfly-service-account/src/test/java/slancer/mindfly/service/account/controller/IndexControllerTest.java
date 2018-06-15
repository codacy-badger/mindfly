package slancer.mindfly.service.account.controller;

import slancer.mindfly.test.AbS2u2mControllerTest;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Ignore
public class IndexControllerTest extends AbS2u2mControllerTest {

    @Test
    public void index() throws Exception {
        mockMvc.perform(get("/")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
//                .andDo(rst -> System.out.println(rst.getResponse().getContentAsString()));
                .andDo(documentAPI("pingpong"));

    }
}