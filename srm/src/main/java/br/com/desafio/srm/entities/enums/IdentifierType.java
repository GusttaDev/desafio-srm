package br.com.desafio.srm.entities.enums;

import br.com.desafio.srm.exception.BusinessException;
import org.springframework.http.HttpStatus;

public enum IdentifierType {
    CPF,
    CNPJ;

    public static IdentifierType getType(String identifier) {
        switch (identifier.length()) {
            case 11:
                return IdentifierType.CPF;
            case 14:
                return IdentifierType.CNPJ;
            default:
                throw new BusinessException(HttpStatus.BAD_REQUEST, "Tamanho de identificador inválido!");
        }
    }
}
