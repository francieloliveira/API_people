package com.example.api_people.controller;

import com.example.api_people.dto.PeopleDTO;
import com.example.api_people.model.PeopleModel;
import com.example.api_people.service.PeopleService;
import jakarta.validation.Valid;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@Data
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/people")
public class PeopleController {

    final PeopleService peopleService;

    @PostMapping
    public ResponseEntity<Object> savePerson(@RequestBody @Valid PeopleDTO peopleDTO){
        if (peopleService.existsByCpf(peopleDTO.getCpf())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito de CPF, já cadastrado!");
        }
        if (peopleService.existsByEmail(peopleDTO.getEmail())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito de Email, este email já está cadastrado!");
        }
        if (peopleService.existsByTelephone(peopleDTO.getTelephone())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito de Telefone, Este Telefone já está cadastrado!");
        }
        var peopleModel = new PeopleModel();
        BeanUtils.copyProperties(peopleDTO, peopleModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(peopleService.save(peopleModel));
    }

    @GetMapping
    public ResponseEntity<Object> getAllPeople(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(peopleService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOnePerson(@PathVariable(value = "id") Long id){
        Optional<PeopleModel> peopleModelOptional = peopleService.findById(id);
        if (!peopleModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa não encontrada.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(peopleModelOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteOnePerson(@PathVariable(value = "id") Long id){
        Optional<PeopleModel> peopleModelOptional = peopleService.findById(id);
        if (!peopleModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa deletada.");
        }
        peopleService.delete(peopleModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Pessoa deletada corretamente.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object>updateOnePerson(@PathVariable(value = "id") Long id, @RequestBody @Valid PeopleDTO peopleDTO){
        Optional<PeopleModel> peopleModelOptional = peopleService.findById(id);
        if (!peopleModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa não encontrada.");
        }
        var peopleModel = new PeopleModel();
        BeanUtils.copyProperties(peopleDTO, peopleModel);
        peopleModel.setId(peopleModelOptional.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body(peopleService.save(peopleModel));
    }

}
