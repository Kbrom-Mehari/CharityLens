package com.kbrom.charity_lens_backend.common.config;

import com.kbrom.charity_lens_backend.focusArea.FocusArea;
import com.kbrom.charity_lens_backend.focusArea.FocusAreaRepository;
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
        //UN-set 17 sustainable development goals
        return args -> focusAreaRepository.saveAll(List.of(
                new FocusArea("No Poverty", "End poverty in all its forms everywhere", "SDG 1"),
                new FocusArea("Zero Hunger", "End hunger, achieve food security and improved nutrition and promote sustainable agriculture", "SDG 2"),
                new FocusArea("Good Health and Well-being", "Ensure healthy lives and promote well-being for all at all ages", "SDG 3"),
                new FocusArea("Quality Education", "Ensure inclusive and equitable quality education and promote lifelong learning opportunities for all", "SDG 4"),
                new FocusArea("Gender Equality", "Achieve gender equality and empower all women and girls", "SDG 5"),
                new FocusArea("Clean Water and Sanitation", "Ensure availability and sustainable management of water and sanitation for all", "SDG 6"),
                new FocusArea("Affordable and Clean Energy", "Ensure access to affordable, reliable, sustainable and modern energy for all", "SDG 7"),
                new FocusArea("Decent Work and Economic Growth", "Promote sustained, inclusive and sustainable economic growth, full and productive employment and decent work for all", "SDG 8"),
                new FocusArea("Industry, Innovation and Infrastructure", "Build resilient infrastructure, promote inclusive and sustainable industrialization and foster innovation", "SDG 9"),
                new FocusArea("Reduced Inequalities", "Reduce inequality within and among countries", "SDG 10"),
                new FocusArea("Sustainable Cities and Communities", "Make cities and human settlements inclusive, safe, resilient and sustainable", "SDG 11"),
                new FocusArea("Responsible Consumption and Production", "Ensure sustainable consumption and production patterns", "SDG 12"),
                new FocusArea("Climate Action", "Take urgent action to combat climate change and its impacts", "SDG 13"),
                new FocusArea("Life Below Water", "Conserve and sustainably use the oceans, seas and marine resources for sustainable development", "SDG 14"),
                new FocusArea("Life on Land", "Protect, restore and promote sustainable use of terrestrial ecosystems", "SDG 15"),
                new FocusArea("Peace, Justice and Strong Institutions", "Promote peaceful and inclusive societies for sustainable development", "SDG 16"),
                new FocusArea("Partnerships for the Goals", "Strengthen the means of implementation and revitalize the global partnership for sustainable development", "SDG 17")
        ));
    }
}
