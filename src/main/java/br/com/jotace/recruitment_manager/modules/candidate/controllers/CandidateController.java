package br.com.jotace.recruitment_manager.modules.candidate.controllers;

import br.com.jotace.recruitment_manager.exceptions.UserFoundException;
import br.com.jotace.recruitment_manager.modules.candidate.CandidateEntity;
import br.com.jotace.recruitment_manager.modules.candidate.CandidateRepository;
import br.com.jotace.recruitment_manager.modules.candidate.useCases.CreateCandidateUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/candidate")
public class CandidateController {
    @Autowired
    private CreateCandidateUseCase createCandidateUseCase;

    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity candidate) {

        try{
            var result =  this.createCandidateUseCase.execute(candidate);
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
