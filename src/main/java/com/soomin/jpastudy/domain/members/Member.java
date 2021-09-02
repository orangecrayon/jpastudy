package com.soomin.jpastudy.domain.members;

import lombok.*;

import javax.persistence.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "MEMBER")
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
    // 데이터베이스에서는 FK를 설정할 때, 항상 Many인 쪽이 FK임.
    // 즉 ManyToOne, OneToMany 양방향 관계에서 Many쪽이 FK 이고 연관관계의 주인
    // 연관관계의 주인은 항상 FK가 있는 쪽.
    private Team team;

    public void setTeam(Team team) {
        if(this.team != null) {
            this.team.getMembers().remove(this);
        }

        this.team = team;
        team.getMembers().add(this);
    }

    @Override
    public String toString() {
        return "ID : " + id
                + "NAME : " + username
                + "AGE : " + age;
    }
}
