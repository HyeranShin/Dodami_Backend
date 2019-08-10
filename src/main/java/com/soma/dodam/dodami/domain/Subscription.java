package com.soma.dodam.dodami.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Subscription {

    @Id
    private Integer idx;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private Integer price;
}
