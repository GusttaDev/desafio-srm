package br.com.desafio.srm.entities;

public enum IdentifierType {
    CPF,
    CNPJ;

    public static IdentifierType getType(String identifier) {
        if (identifier.length() == 11) {
            return IdentifierType.CPF;
        }
        return IdentifierType.CNPJ;
    }
}
