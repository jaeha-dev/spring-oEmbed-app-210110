package com.github.devsjh;

import com.github.devsjh.exception.DomainNotSupportedException;
import com.github.devsjh.exception.GlobalExceptionHandler;
import com.github.devsjh.web.EmbedApiController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = EmbedApplication.class)
class EmbedApiControllerTests {

    @Autowired
    private EmbedApiController embedApiController;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(embedApiController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .alwaysDo(print())
                .build();
    }

    @Test
    @DisplayName("Success: embed()")
    void embed_Success() throws Exception {
        String url = "https://twitter.com/hellopolicy/status/867177144815804416";
        String expected = "{\"url\":\"https:\\/\\/twitter.com\\/hellopolicy\\/status\\/867177144815804416\",\"author_name\":\"???? ??\",\"author_url\":\"https:\\/\\/twitter.com\\/hellopolicy\",\"html\":\"\\u003Cblockquote class=\\\"twitter-tweet\\\"\\u003E\\u003Cp lang=\\\"ko\\\" dir=\\\"ltr\\\"\\u003E???? ???? ?? ??? ?? ?? ??? ? ?? ???????! ???? ?? ?? ?? &#39;???&#39; ?????? ??? ? ??? ?? ???????. \\u003Ca href=\\\"https:\\/\\/t.co\\/eXKCjQIFJ2\\\"\\u003Epic.twitter.com\\/eXKCjQIFJ2\\u003C\\/a\\u003E\\u003C\\/p\\u003E&mdash; ???? ?? (@hellopolicy) \\u003Ca href=\\\"https:\\/\\/twitter.com\\/hellopolicy\\/status\\/867177144815804416?ref_src=twsrc%5Etfw\\\"\\u003EMay 24, 2017\\u003C\\/a\\u003E\\u003C\\/blockquote\\u003E\\n\\u003Cscript async src=\\\"https:\\/\\/platform.twitter.com\\/widgets.js\\\" charset=\\\"utf-8\\\"\\u003E\\u003C\\/script\\u003E\\n\",\"width\":550,\"height\":null,\"type\":\"rich\",\"cache_age\":\"3153600000\",\"provider_name\":\"Twitter\",\"provider_url\":\"https:\\/\\/twitter.com\",\"version\":\"1.0\"}";

        MvcResult result = mockMvc.perform(post("/api/embed")
                .param("url", url))
                .andExpect(status().isOk())
                .andReturn();

        assertEquals(expected, result.getResponse().getContentAsString());
    }

    @Test
    @DisplayName("Failure: embed()")
    void embed_Failure() throws Exception {
        String url = "https://www.xxx.com/1";

        MvcResult result = mockMvc.perform(post("/api/embed")
                .param("url", url))
                .andExpect(mvcResult -> assertTrue(mvcResult.getResolvedException() instanceof DomainNotSupportedException))
                .andReturn();
    }
}