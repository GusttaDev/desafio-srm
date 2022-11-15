package br.com.desafio.srm.service;

import br.com.desafio.srm.entities.PersonEntity;
import br.com.desafio.srm.exception.BusinessException;
import br.com.desafio.srm.repository.PersonRepository;
import br.com.desafio.srm.request.PersonRequest;
import br.com.desafio.srm.response.PersonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import static java.util.Objects.nonNull;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public PersonResponse create(PersonRequest personRequest) {
        PersonEntity entity = PersonEntity.convert(personRequest);
        this.validateIfExistsIdentifier(entity.getIdentifier());

        PersonEntity person = personRepository.save(entity);

        return PersonResponse.convert(person);
    }

    private void validateIfExistsIdentifier(String identifier){
        PersonEntity entity = personRepository.findByIdentifier(identifier);
        if(nonNull(entity)){
            throw new BusinessException(HttpStatus.CONFLICT, "CPF/CNPJ já cadastrado.");
        }
    }
}
