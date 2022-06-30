package com.alkemy.disney.disney.auth.config;


import com.alkemy.disney.disney.auth.filter.JwtRequestFilter;
import com.alkemy.disney.disney.auth.service.UserDetailsCustomService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@SuppressWarnings("deprecation")
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    /// ATTRIBUTES
    private UserDetailsCustomService userDetailsCustomService;//filtro
    private JwtRequestFilter jwtRequestFilter;//servicio



    /**
     * Configure method to override the service used for UserDetails
     *
     * @param auth
     * @throws Exception
     */

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsCustomService);
    }


    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

    /*@Bean
    public SendGrid sendGrid() {
        return new SendGrid("XX");
    }*/

    /**
     * Configure method to override HttpSecurity behaviour.
     * Csrf disabled, any requests pointing to "/auth/*" lacks of security, and requests sessions are stateless
     * Adds JwtRequestFilter as 'once per request' filter
     *
     * @param httpSecurity
     * @throws Exception
     */
    @Override
    //indicamos a que endpoints se le da seguridad
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                .authorizeRequests().antMatchers("/auth/*").permitAll()
                .anyRequest().authenticated()
                .and().exceptionHandling()
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);//guarda el estado en el que se ejecuto

        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}