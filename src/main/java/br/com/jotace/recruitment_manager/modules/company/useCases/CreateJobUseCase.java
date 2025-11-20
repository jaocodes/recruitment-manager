package br.com.jotace.recruitment_manager.modules.company.useCases;

import br.com.jotace.recruitment_manager.modules.company.entities.JobEntity;
import br.com.jotace.recruitment_manager.modules.company.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateJobUseCase {
    @Autowired
    private JobRepository jobRepository;

    public JobEntity execute(JobEntity job){
        return this.jobRepository.save(job);

    }
}
