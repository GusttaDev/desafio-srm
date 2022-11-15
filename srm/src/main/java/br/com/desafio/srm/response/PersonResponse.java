package br.com.desafio.srm.response;

import br.com.desafio.srm.entities.IdentifierType;
import br.com.desafio.srm.entities.PersonEntity;
import br.com.desafio.srm.utils.Utils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ApiModel("PersonResponse")
public class PersonResponse implements Serializable {
    private static final long serialVersionUID = -66386817291786340L;

    @ApiModelProperty(value = "Campo referente ao id do cliente", name = "id", dataType = "Long", example = "1")
    private Long id;

    @ApiModelProperty(value = "Campo referente ao nome do cliente", name = "name", dataType = "String", example = "Person Test")
    private String name;

    @ApiModelProperty(value = "Campo referente ao número do documento do cliente que pode ser CPF ou CNPJ de acordo com a quantidade de dígito", name = "identifier", dataType = "String", example = "06228104055")
    private String identifier;

    @ApiModelProperty(value = "Campo referente ao tipo do documento do cliente que pode ser CPF ou CNPJ", name = "identifierType", dataType = "Enum", example = "CPF")
    private IdentifierType identifierType;

    public static PersonResponse convert(PersonEntity person) {
        PersonResponse personResponse = new PersonResponse();
        personResponse.setId(person.getId());
        personResponse.setName(person.getName());
        personResponse.setIdentifierType(person.getIdentifierType());

        setIdentifier(person, personResponse);
        return personResponse;
    }

    private static void setIdentifier(PersonEntity person, PersonResponse personResponse) {
        if (IdentifierType.CPF.equals(personResponse.getIdentifierType())) {
            var formattedCpfWithDots = Utils.formatCPF(person.getIdentifier());
            var maskAsteriskCPF = Utils.maskAsteriskCPF(formattedCpfWithDots);

            personResponse.setIdentifier(maskAsteriskCPF);
        } else {
            personResponse.setIdentifier(Utils.formatCNPJ(person.getIdentifier()));
        }
    }
}
