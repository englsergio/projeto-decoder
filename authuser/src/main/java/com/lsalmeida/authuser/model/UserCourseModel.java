package com.lsalmeida.authuser.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@With
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "TB_USERS_COURSES")
@EqualsAndHashCode(of = "id", callSuper = false)
public class UserCourseModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private UserModel user;
    @Column(nullable = false)
    private UUID courseId;

}
