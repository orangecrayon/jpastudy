package com.soomin.jpastudy.domain.members;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Team {
    @Id
    @Column(name = "TEAM_ID")
    private String id;

    private String name;
}
