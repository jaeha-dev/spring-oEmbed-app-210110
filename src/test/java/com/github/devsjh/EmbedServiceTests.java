package com.github.devsjh;

import com.github.devsjh.application.ApiCallService;
import com.github.devsjh.application.DomainService;
import com.github.devsjh.application.EmbedService;
import com.github.devsjh.model.Domain;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class EmbedServiceTests {

    @Mock
    private DomainService domainService;

    @Mock
    private ApiCallService apiCallService;

    @InjectMocks
    private EmbedService embedService;

    @Test
    @DisplayName("Success: getEmbedContents()")
    void getEmbedContents_Success() {
        // given: 검색할 URL, 도메인, 예상 결과 값 지정
        String url = "https://www.twitter.com/1";
        Domain domain = new Domain("twitter", "https://publish.twitter.com/?url=");
        String expected = "{\"url\": \"https://twitter.com/1\"}";

        given(domainService.getByUrl(url)).willReturn(domain);
        given(apiCallService.getContents(anyString())).willReturn(expected);

        // when: getEmbedContents() 메서드 호출
        String actual = embedService.getEmbedContents(url);

        // then: 결과 값 비교
        assertEquals(expected, actual);
    }

    // @Test
    // @DisplayName("Failure: getEmbedContents()")
    // public void getEmbedContents_Failure() {
    // }
}