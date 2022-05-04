package com.clubtur.core.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Setter
@Getter
@Entity
@Builder
@AllArgsConstructor
@ToString
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String address;
    private String phone;
    private String owner;

    @OneToMany(cascade = CascadeType.ALL,
    mappedBy = "company", orphanRemoval = true)
    private List<Destination> destinations;

    public Company() {

    }
}
