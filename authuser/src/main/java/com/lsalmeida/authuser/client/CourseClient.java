package com.lsalmeida.authuser.client;

import com.lsalmeida.authuser.model.dto.CourseDto;
import com.lsalmeida.authuser.model.dto.ResponsePageDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Component
public class CourseClient {

    @Value("${api.client.base-url}")
    private String baseurl;
    @Value("${api.client.endpoint}")
    private String endpoint;
    private final RestClient restClient;

    @Retry(name = "retryInstance", fallbackMethod = "fallback")
    @CircuitBreaker(name = "circuitbreakerInstance")
    public Page<CourseDto> getAllCoursesByUser(UUID userId, Pageable pageable) {
        return restClient.get()
                .uri(UriComponentsBuilder
                        .fromHttpUrl(baseurl)
                        .pathSegment(endpoint)
                        .queryParam("userId", userId)
                        .queryParam("page", pageable.getPageNumber())
                        .queryParam("size", pageable.getPageSize())
                        .queryParam("sort", pageable.getSort().toString().replace(": ", ","))
                        .buildAndExpand(userId)
                        .toUri())
                .retrieve()
                .onStatus(new DefaultResponseErrorHandler())
                .body(new ParameterizedTypeReference<ResponsePageDto<CourseDto>>() {});
    }

    public Page<CourseDto> fallback() {
        log.error("all retries failed. Executing fallback method...");
        return new PageImpl<>(new ArrayList<>());
    }

}