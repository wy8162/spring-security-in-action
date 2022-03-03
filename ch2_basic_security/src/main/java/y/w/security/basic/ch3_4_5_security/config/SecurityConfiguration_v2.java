package y.w.security.basic.ch3_4_5_security.config;

import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * Method 2 to configure the security
 */
@RequiredArgsConstructor
// Uncomment the below to use it.
// @Configuration
public class SecurityConfiguration_v2 extends WebSecurityConfigurerAdapter {
    private final CustomAuthenticationProvider customAuthenticationProvider;

    @Value("${test.username}") private String username;
    @Value("${test.password}") private String password;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        Objects.requireNonNull(username, "Specify test.username as JVM property or system environment.");
        Objects.requireNonNull(password, "Specify test.password as JVM property or system environment.");

        var userDetailsService = new InMemoryUserDetailsManager();

        var user = User.withUsername(username)
            .password(password)
            .authorities("read")
            .build();

        userDetailsService.createUser(user);

        auth.authenticationProvider(customAuthenticationProvider);

        auth.userDetailsService(userDetailsService)
            .passwordEncoder(NoOpPasswordEncoder.getInstance());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic();
        http.authorizeRequests()
            .anyRequest()
            .authenticated();
    }

    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}
