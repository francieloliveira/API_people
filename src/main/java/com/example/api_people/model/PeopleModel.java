package com.example.api_people.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "tb_people")
public class PeopleModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true,length = 11)
    private String cpf;
    @Column(nullable = false, length =40)
    private String name;
    @Column(nullable = false, unique = true, length =40)
    private String email;
    @Column(nullable = false, unique = true, length =20)
    private String telephone;;

    public PeopleModel() {
    }

}
