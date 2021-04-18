package br.application.inviteu;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

   @Bean
   public static BCryptPasswordEncoder passwordEncoder(){
      return new BCryptPasswordEncoder();
   }

   @Override
   protected void configure(HttpSecurity http) throws Exception{
      http.authorizeRequests()
               .antMatchers("/", "/h2-console/**").permitAll()
               .and()
               .httpBasic();
         
      http.csrf().disable();
      http.headers().frameOptions().disable();
   }

   @Override
   protected void configure(AuthenticationManagerBuilder auth) throws Exception{
      auth.userDetailsService(userDetailsServiceBean())
            .passwordEncoder(passwordEncoder());
   }

}