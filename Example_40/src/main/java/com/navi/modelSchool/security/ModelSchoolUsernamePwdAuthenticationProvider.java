package com.navi.modelSchool.security;

import com.navi.modelSchool.model.Person;
import com.navi.modelSchool.model.Roles;
import com.navi.modelSchool.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component //making bean of this java class
public class ModelSchoolUsernamePwdAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private PersonRepository personRepository; // to check the credentials from the DB

    @Autowired
    private PasswordEncoder passwordEncoder;


    /*
    Authentication authenticate() method for our own custom logic.
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName(); //Authentication is the object from the User LogIn page : here we are getting an Email from that authentication method
        String pwd = authentication.getCredentials().toString();


        Person person = personRepository.readByEmail(email); //getting person object by providing the email, readByEmail: Derived by Query in Person Repo


        /*logic for comparing Credentials from the user with Credentials present in the DB
        check for if we have got the person object by the email we have provided.
        Check if the id of that person is greater than 0
        Check if the passwords are matching, for that, PasswordEncoder has matches(raw, encoded) method.
        if ja:
            return the UserPasswordAuthenticationToken --> Parameters: Name, Password, and Granted Authorities(Role)
            Granted Authorities(Role) of  'Collection<? extends GrantedAuthority> authorities' type, List because it can
            accept multiple roles.
        If no:
            throwing an exception : BadCredentialsException : "Invalid Credentials"
         */
        if (null != person && person.getPersonId() > 0 && passwordEncoder.matches(pwd, person.getPwd())){
            return new UsernamePasswordAuthenticationToken(email, null, getGrantedAuthorities(person.getRoles())); // new token to be saved for the loged in user.
        }else{
            throw new BadCredentialsException("Invalid Credentials");
        }
    }


    // getting the information about the Roles and giving to Authentication object in a way that Spring Security can understand.
    // Roles parameters are the roles from the database and referenced to Roles POJO class
    private List<GrantedAuthority> getGrantedAuthorities(Roles roles) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        // Simple Granted Authority
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + roles.getRoleName())); //Methods from the Roles class
        return grantedAuthorities;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        //checking weather given authentication type object is of same data type UsernamePasswordAuthenticationToken?
        // if it is same, then only Spring Security will try to execute our business logic, that we have written in our
        // authenticate method above.
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
