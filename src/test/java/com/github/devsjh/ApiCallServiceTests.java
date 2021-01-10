package com.github.devsjh;

import com.github.devsjh.application.ApiCallService;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ApiCallServiceTests {

    private ApiCallService apiCallService;

    private static MockWebServer mockWebServer;
    private static String baseUrl;

    @BeforeAll
    static void setup() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
    }

    @AfterAll
    static void teardown() throws IOException {
        mockWebServer.shutdown();
    }

    @BeforeEach
    void init() {
        // Mock 서버의 URL을 가져온다.
        baseUrl = String.format("http://localhost:%s", mockWebServer.getPort());

        // URL을 바인딩한 WebClient를 생성한다.
        apiCallService = new ApiCallService(WebClient.builder()
                .baseUrl(baseUrl)
                .build());
    }

    @Test
    @DisplayName("Success: getContents()")
    void getContents_Success() throws InterruptedException {
        // given: 예상 결과 값, Mock 서버 응답 지정
        String expected = "{\"url\": \"https://twitter.com/1\"}";
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .setBody(expected));

        // when: getContents() 메서드 호출
        String actual = apiCallService.getContents(baseUrl);

        // then: 결과 값 비교
        RecordedRequest record = mockWebServer.takeRequest();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Success: getContents()")
    void getContents_WithToken_Success() throws InterruptedException {
        // given: 예상 결과 값, Mock 서버 응답 지정
        String expected = "{\"url\": \"https://twitter.com/2\"}";
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .setHeader(HttpHeaders.AUTHORIZATION, "token")
                .setBody(expected));

        // when: getContents() 메서드 호출
        String actual = apiCallService.getContents(baseUrl, "token");

        // then: 결과 값 비교
        RecordedRequest record = mockWebServer.takeRequest();
        assertEquals("Bearer token", record.getHeader("Authorization"));
        assertEquals(expected, actual);
    }
}