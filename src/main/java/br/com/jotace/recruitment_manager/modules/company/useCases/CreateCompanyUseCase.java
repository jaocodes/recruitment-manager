package br.com.jotace.recruitment_manager.modules.company.useCases;

import br.com.jotace.recruitment_manager.exceptions.UserFoundException;
import br.com.jotace.recruitment_manager.modules.company.entities.CompanyEntity;
import br.com.jotace.recruitment_manager.modules.company.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateCompanyUseCase {
    @Autowired
    private CompanyRepository companyRepository;

    public CompanyEntity execute(CompanyEntity company){
        this.companyRepository.findByUsernameOrEmail(company.getUsername(), company.getEmail())
                .ifPresent((user) -> {
                    throw new UserFoundException();
                });

        return this.companyRepository.save(company);
    }
}
