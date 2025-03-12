package me.yeochan.springbootdeveloper;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static java.nio.file.Paths.get;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class QuizControllerTest {
    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    // objectMapper : Jackson 라이브러리에서 제공하는 클래스, 객체와 JSON 간의 변환을 처리해 줌(객체 직렬화)
    private ObjectMapper objectMapper;

    @Test
    public void mockMvcSetup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @DisplayName("quiz(): GET /quiz?code=1 이면 응답 코드는 201, 응답 본문은 Created!를 리턴한다.")
    @Test
    public void getQuiz1() throws Exception {
        // given
        final String url = "/quiz";
        // when
        final ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get(url).param("code", "1"));
        // then
        result.andExpect(status().isCreated())
                .andExpect((ResultMatcher) content().string("Created!"));

    }

    @DisplayName("quiz(): GET /quiz?code=2 이면 응답 코드는 400, 응답 본문은 Bad Request!를 리턴한다.")
    @Test
    public void getQuiz2() throws Exception {
        // given
        final String url = "/quiz";
        // when
        final ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get(url).param("code", "2"));
        // then
        result.andExpect(status().isBadRequest())
                .andExpect((ResultMatcher) content().string("Bad Request!"));
    }

    @DisplayName("quiz() : POST /quiz?code=1 이면 응답 코드는 403 Forbidden 리턴")
    @Test
    public void postQuiz1() throws Exception {
        // given
        final String url = "/quiz";
        // when
        final ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get(url).accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new Code(1))));
        // then
        result.andExpect(status().isForbidden())
                .andExpect((ResultMatcher) content().string("Forbidden!"));

    }

    @DisplayName("quiz() : POST /quiz?code=13 이면 응답 코드는 200 Ok! 리턴")
    @Test
    public void postQuiz13() throws Exception {
        final String url = "/quiz";
        final ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get(url)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new Code(13))));
        result.andExpect(status().isOk())
                .andExpect((ResultMatcher) content().string("Ok!"));

    }

    record Code(int code) {}
}