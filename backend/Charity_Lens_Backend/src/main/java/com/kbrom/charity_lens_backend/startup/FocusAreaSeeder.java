package com.kbrom.charity_lens_backend.startup;

import com.kbrom.charity_lens_backend.model.FocusArea;
import com.kbrom.charity_lens_backend.repository.FocusAreaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.util.List;

@Configuration
@RequiredArgsConstructor
public class FocusAreaSeeder {
    private final FocusAreaRepository focusAreaRepository;

    @Bean
    CommandLineRunner seedFocusAreas() {
        return args -> focusAreaRepository.saveAll(List.of(new  FocusArea("","",""),
                                                                    new FocusArea("","",""),
                                                                    new FocusArea("","",""),
                                                                    new FocusArea("","","")));
    }


}
