package com.telegram.core.dto;

import com.telegram.core.model.Company;
import com.telegram.core.model.Route;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DestinationDTO {
    private String name;
    private String link;
    private CompanyDTO destinationCompany;
    private List<RouteDTO> destinationRoutes;

    public void setDestinationCompany(Company company) {
        CompanyDTO dto = new CompanyDTO();
        dto.setName(company.getName());
        this.destinationCompany = dto;
    }

    public void setDestinationRoutes(List<Route> routes) {
        ModelMapper mapper = new ModelMapper();
        this.destinationRoutes = routes.stream()
                .map(route -> mapper.map(route, RouteDTO.class))
                .collect(Collectors.toList());
    }
}
