package br.com.desafio.srm.entities;

import br.com.desafio.srm.request.PersonRequest;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "person")
public class PersonEntity implements Serializable {

    private static final long serialVersionUID = -5582599469031400345L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private String identifier;
    private IdentifierType identifierType;

    public static PersonEntity convert(PersonRequest personRequest) {
        return PersonEntity.builder()
                .name(personRequest.getName())
                .identifier(personRequest.getIdentifier())
                .identifierType(IdentifierType.getType(personRequest.getIdentifier()))
                .build();
    }
}
