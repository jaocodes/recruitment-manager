package br.com.jotace.recruitment_manager.modules.company.repositories;

import br.com.jotace.recruitment_manager.modules.company.entities.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JobRepository extends JpaRepository<JobEntity, UUID> {
    
}
