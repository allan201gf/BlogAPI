package br.com.allangf.BlogAPI.rest;

import br.com.allangf.BlogAPI.domain.exception.RuleOfException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Helpers {

    public static LocalDate stringForDate (String dateString) {
        try {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return LocalDate.parse(dateString, format);
        } catch (Exception e) {
            throw new RuleOfException("Data invalida ou nula, formato correto: dd/mm/aaaa ");
        }

    }

}
