package com.telegram.core.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
@Entity
public class Destination {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nameDestination;
    private String link;

    public Destination() {
    }

    @Override
    public String toString() {
        return "Destination{" +
                "nameDestination='" + nameDestination + '\'' +
                ", link='" + link +
                '}';
    }
}
