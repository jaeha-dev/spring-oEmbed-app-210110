package com.github.devsjh.web;

import com.github.devsjh.application.EmbedService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class EmbedApiController {

    private final EmbedService embedService;

    @PostMapping("/api/embed")
    public ResponseEntity<?> embed(@RequestParam("url") String url) {
        return new ResponseEntity<>(embedService.getEmbedContents(url), HttpStatus.OK);
    }
}