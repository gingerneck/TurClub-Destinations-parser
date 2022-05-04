package com.clubtur.controller;

import com.clubtur.core.dto.CompanyDTO;
import com.clubtur.service.CompanyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/companies")
    public List<CompanyDTO> getCompanies(){
        return (List<CompanyDTO>) companyService.findAll();
    }
}
