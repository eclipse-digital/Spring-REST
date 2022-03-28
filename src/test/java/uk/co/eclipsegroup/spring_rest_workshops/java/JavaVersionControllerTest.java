package uk.co.eclipsegroup.spring_rest_workshops.java;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
class JavaVersionControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void javaVersions_canBeStored_andRead() throws Exception {
        var firstJava = new JavaVersion("JDK 1.0", 1.0);
        store(firstJava);

        var fetchedJavaVersions = fetchAll();

        assertThat(fetchedJavaVersions).isEmpty();
    }

    @Test
    void multipleJavaVersions_canBeStored_andRead() throws Exception {
        var firstJava = new JavaVersion("JDK 1.0", 1.0);
        var secondJava = new JavaVersion("JDK 1.1", 1.1);
        store(firstJava, secondJava);

        var fetchedJavaVersions = fetchAll();

        assertThat(fetchedJavaVersions).containsExactly(firstJava, secondJava);
    }

    @Test
    void javaVersions_canBeRemoved() throws Exception {
        var firstJava = new JavaVersion("JDK 1.0", 1.0);
        var secondJava = new JavaVersion("JDK 1.1", 1.1);

        store(firstJava);
        store(secondJava);

        mvc.perform(delete("/java/JDK 1.0"));

        var fetchedJavaVersions = fetchAll();

        assertThat(fetchedJavaVersions).containsExactly(secondJava);
    }

    private void store(JavaVersion... javaVersions) throws Exception {
        mvc.perform(post("/java")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(javaVersions)));
    }

    private List<JavaVersion> fetchAll() throws Exception {
        return objectMapper.readValue(mvc.perform(get("/java"))
                .andReturn()
                .getResponse()
                .getContentAsString(), new TypeReference<>() {
        });
    }
}