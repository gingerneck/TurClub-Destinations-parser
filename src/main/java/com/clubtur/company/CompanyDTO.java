package com.clubtur.company;

import com.clubtur.destination.DestinationDTO;
import com.clubtur.destination.Destination;
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
public class CompanyDTO {
    private String name;
    private String address;
    private String phone;
    private String owner;

    private List<DestinationDTO> companyDestinations;

    public void setCompanyDestinations(List<Destination> companyDestinations) {
        ModelMapper mapper = new ModelMapper();
        this.companyDestinations = companyDestinations.stream()
                .map(destination -> {
                    DestinationDTO dto = mapper.map(destination, DestinationDTO.class);
                    dto.setDestinationRoutes(destination.getRoutes());
                    return dto;
                })
                .collect(Collectors.toList());
    }
}
