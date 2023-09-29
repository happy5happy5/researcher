//package com.oneday.researcher.util;
//
//import com.oneday.researcher.service.TokenService;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.jetbrains.annotations.NotNull;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//import java.util.logging.Logger;
//
//@Component
//public class JwtAuthFilter extends OncePerRequestFilter {
//
//    private final Logger logger = Logger.getLogger(JwtAuthFilter.class.getName());
//    private final TokenService tokenService;
//
//
//    @Autowired
//    public JwtAuthFilter(TokenService tokenService) {
//        this.tokenService = tokenService;
//    }
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain filterChain) throws ServletException, IOException {
//        logger.info("JwtAuthFilter doFilterInternal");
//        String token = request.getHeader("Authorization");
//        if (token != null) {
//            String username = tokenService.getUsername(token);
//            if (username != null) {
//                request.setAttribute("username", username);
//                request.setAttribute("roles", tokenService.getRoles(token));
//                request.setAttribute("subject", tokenService.getSubject(token));
//            }
//        }
//        filterChain.doFilter(request, response);
//    }
//}
