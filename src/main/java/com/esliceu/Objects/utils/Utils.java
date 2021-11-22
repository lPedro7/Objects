package com.esliceu.Objects.utils;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class Utils {

    public String DateToString(Date date) {
        String res;
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        res = df.format(date);
        return res;
    }

}
