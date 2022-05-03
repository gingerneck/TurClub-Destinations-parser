package com.telegram.core.dto;

import com.telegram.core.model.Destination;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RouteDTO {

    private String cost;
    private String currency;
    private String link;
    private String title;
    private String aroundCost;
    private String description;

    public void setRouteDestination(Destination destination){
        DestinationDTO destinationDto = new DestinationDTO();
        destinationDto.setName(destination.getName());
        destinationDto.setLink(destination.getLink());
        destinationDto.setDestinationCompany(destination.getCompany());
        this.routeDestination = destinationDto;
    }

    private DestinationDTO routeDestination;
}
