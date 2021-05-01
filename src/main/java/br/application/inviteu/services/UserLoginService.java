package br.application.inviteu.services;

import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.application.inviteu.entities.Role;
import br.application.inviteu.entities.User;
import br.application.inviteu.repositories.UserRepository;

@Transactional
@Service
public class UserLoginService implements UserDetailsService{

   private UserRepository userRepository;

   public UserLoginService(UserRepository userRepository){
      this.userRepository = userRepository;
   }

   @Override
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      try {
         User user = userRepository.findByUsername(username);
         if(user == null){
            return null;
         }

         return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthorities(user));
      } catch (Exception e) {
         throw new UsernameNotFoundException("User not found");
      }
   }

   private Set<GrantedAuthority> getAuthorities(User user) {
      Set<GrantedAuthority> authorities = new HashSet<>();
      for(Role role: user.getRoles()){
         GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getRole());
         authorities.add(grantedAuthority);
      }
      return authorities;
   }

   public User getUserLogged(){
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      if (!(authentication instanceof AnonymousAuthenticationToken)) {
         String currentUserName = authentication.getName();
         return userRepository.findByUsername(currentUserName);
      }
      return null;
   }
   
}
