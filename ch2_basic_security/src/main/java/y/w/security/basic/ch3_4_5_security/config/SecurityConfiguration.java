package y.w.security.basic.ch3_4_5_security.config;

import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * Method 1 to configure the security.
 */
@RequiredArgsConstructor
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final CustomAuthenticationProvider customAuthenticationProvider;

    @Value("${test.username}") private String username;
    @Value("${test.password}") private String password;

    /**
     * Configure authentication provider.
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(customAuthenticationProvider);
    }

    @Bean
    public UserDetailsService userDetailsService() {
        Objects.requireNonNull(username, "Specify test.username as JVM property or system environment.");
        Objects.requireNonNull(password, "Specify test.password as JVM property or system environment.");

        // Create an instance of UserDetailsService
        var userDetailsService =
            new InMemoryUserDetailsManager();

        // Add a test user
        var user = User.withUsername(username)
            .password(password)
            .authorities("read")
            .build();

        userDetailsService.createUser(user);

        return userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic();

        // All requests require authentication
        http.authorizeRequests()
            .anyRequest()
            .authenticated();

        // Permit all. No security
//        http.authorizeRequests()
//            .anyRequest()
//            .permitAll();
    }

}
