package com.github.devsjh.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@RequiredArgsConstructor
@Service
public class ApiCallService {

    private final WebClient webClient;

    public String getContents(String url) {
        return webClient.mutate().baseUrl(url).build()
                .get()
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    // 페이스북, 인스타그램 데이터 요청 시 토큰을 헤더에 포함한다.
    public String getContents(String url, String token) {
        return webClient.mutate().baseUrl(url).build()
                .get()
                .headers(header -> header.setBearerAuth(token))
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}