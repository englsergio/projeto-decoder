package com.lsalmeida.course.client;

import com.lsalmeida.course.model.dto.CourseUserDto;
import com.lsalmeida.course.model.dto.ResponsePageDto;
import com.lsalmeida.course.model.dto.UserCourseDto;
import com.lsalmeida.course.model.dto.UserDto;
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
public class UserClient {

    @Value("${API_USER_BASEURL}")
    private String baseurl;
    @Value("${API_USER_ENDPOINT}")
    private String endpoint;
    @Value("${API_USER_ENDPOINT_PROPAG_SUBS}")
    private String endpointPropagSubs;
    private final RestClient restClient;

    public Page<UserDto> getAllUsersByCourse(UUID courseId, Pageable pageable) {
        return restClient.get()
                .uri(UriComponentsBuilder
                        .fromHttpUrl(baseurl)
                        .pathSegment(endpoint)
                        .queryParam("courseId", courseId)
                        .queryParam("page", pageable.getPageNumber())
                        .queryParam("size", pageable.getPageSize())
                        .queryParam("sort", pageable.getSort().toString().replace(": ", ","))
                        .buildAndExpand(courseId)
                        .toUri())
                .retrieve()
                .onStatus(new DefaultResponseErrorHandler())
                .body(new ParameterizedTypeReference<ResponsePageDto<UserDto>>() {});
    }

    public UserDto getUserById(UUID userId) {
        return restClient.get()
                .uri(UriComponentsBuilder
                        .fromHttpUrl(baseurl)
                        .pathSegment("users")
                        .pathSegment(userId.toString())
                        .toUriString())
                .retrieve()
                .onStatus(new DefaultResponseErrorHandler())
                .body(new ParameterizedTypeReference<>() {});
    }

    public UserCourseDto assignCourseToUser(CourseUserDto courseUserDto) {
        return restClient.post()
                .uri(UriComponentsBuilder
                        .fromHttpUrl(baseurl)
                        .pathSegment(endpointPropagSubs)
                        .buildAndExpand(courseUserDto.userId())
                        .toUri())
                .body(courseUserDto)
                .retrieve()
                .onStatus(new DefaultResponseErrorHandler())
                .body(UserCourseDto.class);
    }

}
