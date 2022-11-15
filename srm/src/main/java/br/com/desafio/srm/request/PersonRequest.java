package br.com.desafio.srm.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ApiModel("PersonRequest")
public class PersonRequest implements Serializable {

    private static final long serialVersionUID = 2004619843722838702L;

    @ApiModelProperty(value = "Campo referente ao nome do cliente", name = "name", dataType = "String", example = "Person Test")
    @NotBlank(message = "Nome não pode estar vazio.")
    private String name;

    @ApiModelProperty(value = "Campo referente ao número do documento do cliente que pode ser CPF ou CNPJ de acordo com a quantidade de dígito", name = "identifier", dataType = "String", example = "06228104055")
    @NotBlank(message = "Identificador não pode estar vazio.")
    private String identifier;
}
