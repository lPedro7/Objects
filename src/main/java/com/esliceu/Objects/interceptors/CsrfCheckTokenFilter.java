package com.esliceu.Objects.interceptors;

import com.google.common.cache.Cache;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@WebFilter(filterName = "csrfCheckTokenFilter")
public class CsrfCheckTokenFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {


        if(!req.getMethod().equalsIgnoreCase("post")){
            chain.doFilter(req,res);
            return;
        }

        Cache<String, Boolean> tokenCache = CsrfGenerateTokenFilter.getCache(req);

        String csrf = req.getParameter("csrftoken");


        if (tokenCache.getIfPresent(csrf) != null){
            res.sendError(403);
            return;
        }

        chain.doFilter(req,res);
    }

}
