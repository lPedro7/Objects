package com.esliceu.Objects.interceptors;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Component
@WebFilter( filterName = "csrfGenerateTokenFilter")
public class CsrfGenerateTokenFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {

        if (!req.getMethod().equalsIgnoreCase("post")) {
            chain.doFilter(req, res);
            return;
        }

        Cache<String, Boolean> tokenCache = getCache(req);

        String token = generateTokenString();
        tokenCache.put(token,true);

        req.setAttribute("csrftoken",token);
        chain.doFilter(req, res);
    }

    static Cache<String, Boolean> getCache(HttpServletRequest req) {
        Cache<String, Boolean> cache = (Cache<String, Boolean>) req.getSession().getAttribute("tokencache");
        if (cache == null){
            cache = buildCache();
        }

        req.getSession().setAttribute("tokencache",cache);

        return cache;
    }

    static Cache<String, Boolean> buildCache() {
        return CacheBuilder
                .newBuilder()
                .maximumSize(3000)
                .expireAfterWrite(1, TimeUnit.HOURS)
                .build();
    }

    private String generateTokenString() {
        String lletres = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 20; i++) {
            sb.append(lletres.charAt(random.nextInt(lletres.length())));
        }
        return sb.toString();
    }
}
