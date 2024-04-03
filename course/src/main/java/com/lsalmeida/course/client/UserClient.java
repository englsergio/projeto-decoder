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

    @Value("${api.user.base-url}")
    private String baseurl;
    @Value("${api.user.endpoint}")
    private String endpoint;
    @Value("${api.user.endpoint-propagate-subscription}")
    private String endpointPropagSubs;
    @Value("${api.user.endpoint-propagate-deletion}")
    private String endpointPropagDel;
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

    public Void deleteCourseInUser(UUID courseId) {
        return restClient.delete()
                .uri(UriComponentsBuilder
                        .fromHttpUrl(baseurl)
                        .pathSegment(endpointPropagDel)
                        .buildAndExpand(courseId)
                        .toUri())
                .retrieve()
                .onStatus(new DefaultResponseErrorHandler())
                .body(Void.class);
    }

}
