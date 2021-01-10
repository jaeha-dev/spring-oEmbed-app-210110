package com.github.devsjh.application;

import com.github.devsjh.model.Domain;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import static com.github.devsjh.model.DomainType.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class EmbedService {

    @Value("${token.types.facebook}")
    private String token;

    private final DomainService domainService;
    private final ApiCallService apiCallService;

    public String getEmbedContents(String url) {
        Domain domain = domainService.getByUrl(url);

        switch (domain.getName()) {
            case FACEBOOK:
            case INSTAGRAM:
                return apiCallService.getContents(domain.getEndpoint() + url, token);
            default:
                return apiCallService.getContents(domain.getEndpoint() + url);
        }
    }
}