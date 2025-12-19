package pl.wszib.edu.duda.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Configuration //oznaczenie jako klasa konfiguracyjna Springa
@ComponentScan({
        "pl.wszib.edu.duda.authentication",
        "pl.wszib.edu.duda.database",
        "pl.wszib.edu.duda.core",
        "pl.wszib.edu.duda.gui"
})

public class AppConfiguration {
    @Bean
    public Scanner scanner() {return new Scanner(System.in);}
}
