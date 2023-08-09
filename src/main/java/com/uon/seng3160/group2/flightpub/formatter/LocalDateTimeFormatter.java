package com.uon.seng3160.group2.flightpub.formatter;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.springframework.format.Formatter;

public class LocalDateTimeFormatter implements Formatter<LocalDateTime> {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public LocalDateTime parse(String dateString, Locale locale) throws ParseException {
        return LocalDateTime.parse(dateString, formatter);
    }

    @Override
    public String print(LocalDateTime date, Locale locale) {
        return formatter.format(date);
    }
}