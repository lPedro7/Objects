package com.esliceu.Objects.utils;

import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import com.google.protobuf.compiler.PluginProtos;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.Normalizer;
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

    public String getHash(String s){

        String sha256hex = Hashing.sha256()
                .hashString(s, StandardCharsets.UTF_8)
                .toString();

        return sha256hex;
    }

    public String getFileExtension(String s){

        return "."+s.split("\\.")[1];

    }

    public int countChar(String s,char c){
      int res = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i)==c)res++;
        }
        return res;
    }

    public static String unaccent(String src) {
        return Normalizer
                .normalize(src, Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "");
    }

}
