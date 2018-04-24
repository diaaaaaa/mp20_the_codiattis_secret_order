package at.refugeescode.mp20_the_codiattis_secret_order.initialise;

import at.refugeescode.mp20_the_codiattis_secret_order.persistence.User;
import at.refugeescode.mp20_the_codiattis_secret_order.persistence.UserRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Configuration
public class UserInitialisation {

    @Bean
    ApplicationRunner initialiseUsers(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        return args -> {
            User user = new User();
            user.setUsername("user");
            user.setPassword(passwordEncoder.encode("user"));
            user.setAuthorities(Stream.of("USER").collect(Collectors.toSet()));

            userRepository.save(user);
        };
    }


}
