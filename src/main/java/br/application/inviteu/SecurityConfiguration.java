package br.application.inviteu;

import br.application.inviteu.repositories.UserRepository;
import br.application.inviteu.services.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

   @Bean
   public static BCryptPasswordEncoder passwordEncoder(){
      return new BCryptPasswordEncoder();
   }

   @Autowired
   private UserLoginService userLoginService;

   @Autowired
   private UserRepository userRepository;

   @Override
   public UserDetailsService userDetailsServiceBean() throws Exception{
      return new UserLoginService(userRepository);
   }

   @Override
   protected void configure(HttpSecurity http) throws Exception{
      http.authorizeRequests()
            .antMatchers("/", "/login", "/h2-console/**").permitAll()
            //.antMatchers("/api/roles/**").hasAnyAuthority("ADMIN")
            //.antMatchers("/api/**").authenticated()
            .and()
            .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            .logoutSuccessUrl("/login").permitAll()
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