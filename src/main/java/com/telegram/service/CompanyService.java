package com.telegram.service;

import com.telegram.core.dto.CompanyDTO;
import com.telegram.core.model.Company;
import com.telegram.data.CompanyRepository;
import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyService {

    ModelMapper mapper = new ModelMapper();

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @SneakyThrows
    public void saveAll(Iterable<Company> companies) {
        companyRepository.saveAll(companies);
    }

    @SneakyThrows
    public void save(Company company) {
        companyRepository.save(company);
    }

    public Iterable<CompanyDTO> findAll() {
        List<Company> companies = (List<Company>) companyRepository.findAll();
        return companies.stream()
                .map(company -> {
                    CompanyDTO dto = mapper.map(company, CompanyDTO.class);
                    dto.setCompanyDestinations(company.getDestinations());
                    return dto;
                })
                .collect(Collectors.toList());
    }


    public void deleteAll(){
        companyRepository.deleteAll();
    }

}
