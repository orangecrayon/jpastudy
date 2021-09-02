package com.soomin.jpastudy.domain.members;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Team {
    @Id
    @Column(name = "TEAM_ID")
    private String id;

    private String name;

    @Setter
    @OneToMany(mappedBy = "team")
    // mappedBy = 양방향 매핑이 되는 반대쪽 필드 이름, mappedBy 로 지정된 쪽이 연관관계의 주인
    // 주인쪽에서 FK를 관리하기 때문에 주인이 아닌 이곳에서 FK 등록을 하려고하면 실패함
    // team.getMembers().add(member1)  <-- 이런코드는 주인이 아닌쪽에서 멤버를 등록하려고하기 때문에 안됨
    // member1.setTeam(team) <-- 주인인 member에서 team을 등록하는것만 가능
    private List<Member> members = new ArrayList<Member>();

}
