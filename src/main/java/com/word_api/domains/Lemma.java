package com.word_api.domains;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "lemma")

@NamedQuery(
        name = "Lemma.findWordsByGivenCriteria",
        query = "FROM lemma WHERE rank_number BETWEEN :MIN_RANGE AND :MAX_RANGE AND pos = :TYPE"
    //    query = "FROM lemma WHERE id = 1"
)
public class Lemma {

    @Id
    @Column
    private Long rank_number;
    @Column
    private String lemma;
    @Column
    private String pos;
    @Column
    private double freq;

    public static String[] fields(){
        return new String[]{"rank_number","lemma","pos","freq"};
    }
}
