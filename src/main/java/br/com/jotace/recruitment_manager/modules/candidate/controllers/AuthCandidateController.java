package br.com.jotace.recruitment_manager.modules.candidate.controllers;

import br.com.jotace.recruitment_manager.modules.candidate.dto.AuthCandidateRequestDTO;
import br.com.jotace.recruitment_manager.modules.candidate.useCases.AuthCandidateUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;

@RestController
@RequestMapping("/auth")
public class AuthCandidateController {

    @Autowired
    private AuthCandidateUseCase authCandidateUseCase;

    @PostMapping("/candidate")
    public ResponseEntity<Object> create(@RequestBody AuthCandidateRequestDTO authCandidateRequestDTO){

        try {
            var authCandidateResponseDTO = this.authCandidateUseCase.execute(authCandidateRequestDTO);
            return ResponseEntity.ok().body(authCandidateResponseDTO);

        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest()
            .body(e.getMessage());
        }


    }
}
