package com.kbrom.charity_lens_backend.auth.security;

import com.kbrom.charity_lens_backend.user.model.User;
import com.kbrom.charity_lens_backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<User> userOptional;
        if (login.contains("@")){
            userOptional=userRepository.findByEmail(login);
        }
        else {
            userOptional = userRepository.findByUsername(login);
        }
        User user = userOptional.orElseThrow(()->new UsernameNotFoundException("User "+login+" Not Found"));
        return CustomUserDetails.from(user);
    }

}
