package com.kbrom.charity_lens_backend.auth.security;

import com.kbrom.charity_lens_backend.auth.service.CustomUserDetailsService;
import com.kbrom.charity_lens_backend.auth.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter{
     private final JwtService jwtService;
     private final CustomUserDetailsService customUserDetailsService;
     protected void doFilterInternal(@NonNull HttpServletRequest request,
                                     @NonNull HttpServletResponse response,
                                     @NonNull FilterChain filterChain) throws ServletException, IOException {
         String header = request.getHeader("Authorization");
         if(header==null || !header.startsWith("Bearer ")){
             filterChain.doFilter(request, response);
             return;
         }
         String jwt=header.substring(7);
         String username=jwtService.getSubjectFromToken(jwt);
         if(username!=null&& SecurityContextHolder.getContext().getAuthentication()==null){
             UserDetails userDetails=customUserDetailsService.loadUserByUsername(username);
             if(userDetails!=null&&jwtService.isTokenValid(jwt,userDetails.getUsername())){
                 UsernamePasswordAuthenticationToken authToken = new
                         UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                 SecurityContextHolder.getContext().setAuthentication(authToken);
                 authToken.setDetails(new WebAuthenticationDetailsSource());
             }
         }
         filterChain.doFilter(request, response);
     }

}