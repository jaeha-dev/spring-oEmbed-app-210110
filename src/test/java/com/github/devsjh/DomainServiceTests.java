package com.github.devsjh;

import com.github.devsjh.application.DomainService;
import com.github.devsjh.exception.DomainNotSupportedException;
import com.github.devsjh.model.Domain;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@SpringBootTest(properties = "classpath:application.yml")
class DomainServiceTests {

    @Autowired
    private DomainService domainService;

    @Test
    @DisplayName("Success: getByUrl()")
    void getByUrl_Success() {
        // given: 검색할 URL 지정, 예상 결과 값 지정
        String url = "https://www.youtube.com/1";
        Domain expected = new Domain("youtube", "https://www.youtube.com/oembed?url=");

        // when: getByUrl() 메서드 호출
        Domain actual = domainService.getByUrl(url);

        // then: 도메인 이름, 엔드포인트 비교
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getEndpoint(), actual.getEndpoint());
    }

    @Test
    @DisplayName("Failure: getByUrl()")
    void getByUrl_Failure() {
        // given: 검색할 URL 지정 (잘못된 URL)
        String url = "1/moc.ebutuoy.www//:sptth";

        // when: getByUrl() 메서드 호출
        // then: DomainNotSupportedException 예외 발생
        Throwable exception = assertThrows(DomainNotSupportedException.class, () -> domainService.getByUrl(url));
        assertEquals(DomainNotSupportedException.class, exception.getClass());
    }

    @Test
    @DisplayName("Success: getName()")
    void getName_Success() {
        // given: 검색할 도메인 이름, 예상 결과 값 지정
        String name = "youtube";
        String expected = "youtube";

        // when: getName() 메서드 호출
        String actual = domainService.getName(name);

        // then: 도메인 이름 비교
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Failure: getName()")
    void getName_Failure() {
        // given: 검색할 도메인 이름 지정 (지원하지 않는 도메인)
        String name = "google";

        // when: getName() 메서드 호출
        // then: DomainNotSupportedException 예외 발생
        Throwable exception = assertThrows(DomainNotSupportedException.class, () -> domainService.getName(name));
        assertEquals("Domain Not Supported", exception.getMessage());
    }

    @Test
    @DisplayName("Success: getEndpoint()")
    void getEndpoint_Success() {
        // given: 검색할 도메인 이름, 예상 결과 값 지정
        String name = "youtube";
        String expected = "https://www.youtube.com/oembed?url=";

        // when: getName() 메서드 호출
        String actual = domainService.getEndpoint(name);

        // then: 도메인 엔드포인트 비교
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Failure: getEndpoint()")
    void getEndpoint_Failure() {
        // given: 검색할 도메인 이름 지정 (지원하지 않는 도메인)
        String name = "google";

        // when: getEndpoint() 메서드 호출
        // then: DomainNotSupportedException 예외 발생
        Throwable exception = assertThrows(DomainNotSupportedException.class, () -> domainService.getEndpoint(name));
        assertEquals("Domain Not Supported", exception.getMessage());
    }
}