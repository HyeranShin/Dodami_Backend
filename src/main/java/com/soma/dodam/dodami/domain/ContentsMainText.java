package com.soma.dodam.dodami.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class ContentsMainText {

    @Id
    @GeneratedValue
    private Long idx;

    @Column
    private Long contentsIdx;

    @Column
    private Long time;

    @Column
    private String text;
}
