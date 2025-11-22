package br.com.jotace.recruitment_manager.modules.candidate.useCases;

import br.com.jotace.recruitment_manager.modules.candidate.CandidateRepository;
import br.com.jotace.recruitment_manager.modules.candidate.dto.AuthCandidateRequestDTO;
import br.com.jotace.recruitment_manager.modules.candidate.dto.AuthCandidateResponseDTO;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;

@Service
public class AuthCandidateUseCase {
    @Value("${security.jwt.secret.candidate}")
    private String secretKey;
    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthCandidateResponseDTO execute(AuthCandidateRequestDTO authCandidateRequestDTO) throws AuthenticationException{
        var candidate = this.candidateRepository.findByUsername(authCandidateRequestDTO.username())
                .orElseThrow(() -> new UsernameNotFoundException("Username/Password incorrect"));

        var isPasswordMatches = this.passwordEncoder
                .matches(authCandidateRequestDTO.password(), candidate.getPassword());

        if (!isPasswordMatches){
            throw new AuthenticationException();
        }
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        var token = JWT.create()
                .withIssuer("javagas")
                .withClaim("roles", List.of("candidate"))
                .withExpiresAt(Instant.now().plus(Duration.ofHours(2)))
                .withSubject(candidate.getId().toString())
                .sign(algorithm);

        return AuthCandidateResponseDTO
                .builder()
                .acess_token(token)
                .build();

    }




}
