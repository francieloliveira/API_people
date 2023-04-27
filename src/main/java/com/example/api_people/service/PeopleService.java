package com.example.api_people.service;

import com.example.api_people.model.PeopleModel;
import com.example.api_people.repository.PeopleRepository;
import jakarta.transaction.Transactional;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Data
public class PeopleService {

    final PeopleRepository peopleRepository;

    public boolean existsByCpf(String cpf){
        return peopleRepository.existsByCpf(cpf);
    }
    public boolean existsByEmail(String email){
        return peopleRepository.existsByEmail(email);
    }
    public boolean existsByTelephone(String telephone){
        return peopleRepository.existsByTelephone(telephone);
    }

    @Transactional
    public PeopleModel save(PeopleModel peopleModel){
        return peopleRepository.save(peopleModel);
    }

    public Page<PeopleModel> findAll(Pageable pageable){
        return peopleRepository.findAll(pageable);
    }

    public Optional<PeopleModel> findById(Long id){
        return peopleRepository.findById(id);
    }

    @Transactional
    public void delete(PeopleModel peopleModel){
        peopleRepository.delete(peopleModel);
    }

}
