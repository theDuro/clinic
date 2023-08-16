package com.example.hospital.domain.logic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SortConfiguration {
    @Bean
    public SortDoctors sortDoctors(){
        return new SortDoctors();
    }
    @Bean
    public SortPatiernts sortPatiernts(){
        return new SortPatiernts();
    }
}
