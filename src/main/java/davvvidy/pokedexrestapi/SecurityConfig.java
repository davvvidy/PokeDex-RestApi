package davvvidy.pokedexrestapi;

import davvvidy.pokedexrestapi.user.UserService;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private UserService userService;
    private PasswordEncoderConfig passwordEncoderConfig;

    public SecurityConfig(UserService userService, PasswordEncoderConfig passwordEncoderConfig) {
        this.userService = userService;
        this.passwordEncoderConfig = passwordEncoderConfig;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/**")
                .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user1")
                .password(passwordEncoderConfig.passwordEncoder().encode("pass1"))
                .roles("USER")
                .and()
                .withUser("admin1")
                .password(passwordEncoderConfig.passwordEncoder().encode("pass1"))
                .roles("ADMIN");
    }
}
