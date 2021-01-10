package com.github.devsjh.application;

import com.github.devsjh.exception.DomainNotSupportedException;
import com.github.devsjh.model.Domain;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

@Slf4j
@Getter
@Component
@ConfigurationProperties(prefix = "domain")
public class DomainService {

    private List<Domain> types;

    public void setTypes(List<Domain> types) {
        // @Setter: yaml 설정 값으로 생성된 types 객체를 주입 받는다. (types -> this.types)
        this.types = types;
    }

    public Domain getByUrl(String url) {
        String host = getHost(url);

        return types.stream()
                .filter(domain -> host.contains(domain.getName()))
                .findAny()
                .orElseThrow(DomainNotSupportedException::new);
    }

    public String getName(String name) {
        return types.stream()
                .filter(domain -> domain.getName().equals(name))
                .findAny()
                .orElseThrow(DomainNotSupportedException::new)
                .getName();
    }

    public String getEndpoint(String name) {
        return types.stream()
                .filter(domain -> domain.getName().equals(name))
                .findAny()
                .orElseThrow(DomainNotSupportedException::new)
                .getEndpoint();
    }

    public String getHost(String url) {
        String host = "";
        try {
            // (https://www.xxx.com/1) -> (host: www.xxx.com)
            host = new URL(url).getHost();
        } catch (NullPointerException | MalformedURLException e) {
            throw new DomainNotSupportedException();
        }

        return host;
    }
}