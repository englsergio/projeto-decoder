package com.lsalmeida.authuser.client;

import com.lsalmeida.authuser.model.dto.CourseDto;
import com.lsalmeida.authuser.model.dto.ResponsePageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@RequiredArgsConstructor
@Component
public class CourseClient {

    @Value("${api.client.base-url}")
    private String baseurl;
    @Value("${api.client.endpoint}")
    private String endpoint;
    @Value("${api.client.endpoint-propag-del}")
    private String endpointPropagDel;
    private final RestClient restClient;

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

}
