package com.telegram.core.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@Builder
@Entity
public class Route implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String cost;
    private String currency;
    private String link;
    private String title;
    private String aroundCost;
    private String description;
    private String destination;
    private String club;

    public Route() {
    }

    @Override
    public String toString() {
        return "Route{" +
                "cost='" + cost + '\'' +
                ", currency='" + currency + '\'' +
                ", link='" + link + '\'' +
                ", nameTrip='" + destination + '\'' +
                ", title='" + title + '\'' +
                ", aroundCost='" + aroundCost + '\'' +
                ", Description='" + description + '\'' +
                '}';
    }

    public String getDescriptionForMessage(int i){
        return String.format("%d. %s%s</a>\n%s%s%s\n", i, String.format("<a href='%s'>", this.getLink()), this.getTitle(), this.getDescription() == null ? "" : (this.getDescription() + "\n"), String.format(" Стоимость: %s ", this.getCost() == null ?
                this.getAroundCost() == null ? "" : this.getAroundCost() : this.getCost()), this.getCurrency() == null ? "" : this.getCurrency());
    }

    private String constructCost(){
        return this.getCost() == null ?
                this.getAroundCost() == null ? "" : this.getAroundCost() : this.getCost();
    }
}
