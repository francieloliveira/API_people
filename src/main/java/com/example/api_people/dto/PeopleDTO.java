package com.example.api_people.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

@Data
public class PeopleDTO {

    private Long id;
    @NotBlank
    @CPF
    private String cpf;
    @NotBlank
    private String name;
    @NotBlank
    @Email
    private String email;
    private String telephone;

    public PeopleDTO(Long id, String cpf, String name, String email, String phone) {
        this.id = id;
        this.cpf = cpf;
        this.name = name;
        this.email = email;
        this.telephone = phone;
    }
}
