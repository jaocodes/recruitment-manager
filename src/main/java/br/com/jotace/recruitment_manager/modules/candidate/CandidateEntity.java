package br.com.jotace.recruitment_manager.modules.candidate;

import lombok.Data;
import java.util.UUID;

@Data
public class CandidateEntity {
    private UUID id;
    private String name;
    private  String username;
    private String email;
    private String password;
    private String description;
    private String curriculum;
}
