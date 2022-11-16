package br.com.desafio.srm.utils;

import br.com.desafio.srm.exception.BusinessException;
import org.springframework.http.HttpStatus;

import java.util.InputMismatchException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    public static String removeSpecialCharacters(String identifier) {
        return identifier.replaceAll("[^\\d ]", "");
    }

    public static String formatCNPJ(String cnpf) {

        Pattern pattern = Pattern.compile("(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2})");
        Matcher matcher = pattern.matcher(cnpf);

        if (matcher.matches()) {
            cnpf = matcher.replaceAll("$1.$2.$3/$4-$5");
        }

        return cnpf;
    }

    public static String formatCPF(String cpf) {

        Pattern pattern = Pattern.compile("(\\d{3})(\\d{3})(\\d{3})(\\d{2})");
        Matcher matcher = pattern.matcher(cpf);

        if (matcher.matches()) {
            cpf = matcher.replaceAll("$1.$2.$3-$4");
        }

        return cpf;
    }

    public static String maskAsteriskCPF(String cpf) {

        var maskCPF = "";
        Pattern pattern = Pattern.compile("([0-9]{3}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[-]?[0-9]{2})");
        Matcher matcher = pattern.matcher(cpf);

        if (matcher.matches()) {
            var topThree = cpf.substring(0, 3);
            var lastThree = cpf.substring(8, 11);

            maskCPF = cpf.replaceAll(topThree, "***").replaceAll(lastThree, "***");

        }
        return maskCPF;
    }

    public static void isValidCpf(String cpf) {

        if (cpf.equals("00000000000") || cpf.equals("11111111111")
                || cpf.equals("22222222222") || cpf.equals("33333333333")
                || cpf.equals("44444444444") || cpf.equals("55555555555")
                || cpf.equals("66666666666") || cpf.equals("77777777777")
                || cpf.equals("88888888888") || cpf.equals("99999999999"))
            throw new BusinessException(HttpStatus.BAD_REQUEST, "CPF não é válido!");

        int d1, d2;
        int digito1, digito2, resto;
        int digitoCPF;
        String nDigResult;

        d1 = d2 = 0;
        digito1 = digito2 = resto = 0;

        for (int nCount = 1; nCount < cpf.length() - 1; nCount++) {
            digitoCPF = Integer.valueOf(cpf.substring(nCount - 1, nCount)).intValue();

            d1 = d1 + (11 - nCount) * digitoCPF;
            d2 = d2 + (12 - nCount) * digitoCPF;
        }

        resto = (d1 % 11);

        if (resto < 2)
            digito1 = 0;
        else
            digito1 = 11 - resto;

        d2 += 2 * digito1;
        resto = (d2 % 11);

        if (resto < 2)
            digito2 = 0;
        else
            digito2 = 11 - resto;

        String nDigVerific = cpf.substring(cpf.length() - 2, cpf.length());
        nDigResult = String.valueOf(digito1) + String.valueOf(digito2);

        if (!(nDigVerific.equals(nDigResult))) {
            throw new BusinessException(HttpStatus.BAD_REQUEST, "CPF não é válido!");
        }
    }

    public static void isValidCNPJ(String CNPJ) {
        if (CNPJ.equals("00000000000000") || CNPJ.equals("11111111111111") ||
                CNPJ.equals("22222222222222") || CNPJ.equals("33333333333333") ||
                CNPJ.equals("44444444444444") || CNPJ.equals("55555555555555") ||
                CNPJ.equals("66666666666666") || CNPJ.equals("77777777777777") ||
                CNPJ.equals("88888888888888") || CNPJ.equals("99999999999999") ||
                (CNPJ.length() != 14))
            throw new BusinessException(HttpStatus.BAD_REQUEST, "CNPJ não é válido!");

        char dig13, dig14;
        int sm, i, r, num, peso;

        try {
            sm = 0;
            peso = 2;
            for (i = 11; i >= 0; i--) {

                num = (int) (CNPJ.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso + 1;
                if (peso == 10)
                    peso = 2;
            }

            r = sm % 11;
            if ((r == 0) || (r == 1))
                dig13 = '0';
            else dig13 = (char) ((11 - r) + 48);

            sm = 0;
            peso = 2;
            for (i = 12; i >= 0; i--) {
                num = (int) (CNPJ.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso + 1;
                if (peso == 10)
                    peso = 2;
            }

            r = sm % 11;
            if ((r == 0) || (r == 1))
                dig14 = '0';
            else dig14 = (char) ((11 - r) + 48);

            if (!((dig13 == CNPJ.charAt(12)) && (dig14 == CNPJ.charAt(13))))
                throw new BusinessException(HttpStatus.BAD_REQUEST, "CNPJ não é válido!");
        } catch (InputMismatchException erro) {
            throw new BusinessException(HttpStatus.BAD_REQUEST, "CNPJ não é válido!");
        }
    }
}