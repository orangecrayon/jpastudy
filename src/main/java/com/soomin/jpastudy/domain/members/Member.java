package com.soomin.jpastudy.domain.members;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "MEMBER")
@Builder
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class Member {
    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "NAME")
    @NonNull
    private String username;

    private Integer age;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    @Override
    public String toString() {
        return "ID : " + id
                + "NAME : " + username
                + "AGE : " + age;
    }
}
