package uk.co.eclipsegroup.spring_rest_workshops.java;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
class JavaVersionControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void javaVersions_canBeStored_andFetched() throws Exception {
        var javaVersion = objectMapper.writeValueAsString(new JavaVersion("Java 5", 5.0));
        mockMvc.perform(post("/java")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .content(javaVersion)).andReturn();
        var mvcResult = mockMvc.perform(get("/java")).andReturn();
        var javaVersions = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<List<JavaVersion>>() {
        });

        assertThat(javaVersions).containsExactly(new JavaVersion("Java 5", 5.0));
    }
}