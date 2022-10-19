As we have how to save passwords in the plain text in Example 38. In this Module we are going to save passwords using
other password saving techniques.

# Password Management

### Encoding

1. Encoding is defined as the process of converting data from one form to another and has nothing to do with
   cryptography.
2. It involves no secret and can be reversible.
3. Encoding can't be used for securing the data, there are various publicly available algorithms used for encoding e.g.
   ASCII, BASE64, UNICODE.

## Encryption

1. Encryption is defined as the process of transforming data in such a way that guarantees confidentiality.
2. To achieve confidentiality, encryption required to use of something secret called "key".
3. Encryption can be reversible by decryption with the help of "key", as long as the key is confidential, encryption can
   be considered as secure.

## Hashing

Note: There could be many hashed values for the same password and all of them can confirm weather the password is
correct or not. Eg.<br>
``plaintext123`` --> ``$2a$12$VKStSJ7PSwrBkBLgg.3O/uK5eF6a4hKC/tNVYjP8YNcSqVguoaq6.`` <br>
``plaintext123`` --> ``$2a$12$aWYbCdHliWaD8leF/PUwEOXgCf3TLD73DQv5v1NX4BokXXR.5khgu`` <br>
``plaintext123`` --> ``$2a$12$pYrohnl0ABQVRcUUp8ZbFO6TWSv.yfFf0XE3cmHiyLE4X4oYmCjO.`` <br>
source: https://bcrypt-generator.com/  <br>

So, all these values can authenticate the password '``plaintext123``'. <br>

This makes hashing more secure that even after watching the hashed code form the database, we still can't figure out the
password.

1. In Hashing the data is converted to the hash value using some hashing function.
2. Once the data is hashed, it's irreversible. ONe can not determine the original data from the hash value generated.
3. Given some arbitrary data along with the output of hashing algorithm, one can verify weather the data matches the
   original input data without needing to see the original data.

#### Note:-

Spring Security provide various ``PasswordEncoders`` to help developers with hashing of the secured data password.
Different Implementations of the PasswordEncoders provided by the Spring are:-

1. NoOpPasswordEncoder (No hashing, stored in plain text)
2. StandardPasswordEncoder
3. Pbkdf2PasswordEncoder
4. BCryptPasswordEncoder (Most Commonly Used)
5. SCryptPasswordEncoder

## Password Validation with Hashing and PasswordEncoder, flow diagram

![img_2.png](img_2.png)

# Implementations

1. Create a bean got BCryptPasswordEncoder inside the ``ProjectConfig`` Class.

````java
 @Bean
public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(); //In order to achieve loose coupling 
        }
````

2. For now, we are saving the plain text values, but from now we are going to save Hashed values inside our DB.
   PersonService -->

````java

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private RolesRepository rolesRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean createNewPerson(Person person) {
        boolean isSaved = false;
        Roles role = rolesRepository.getByRoleName(ModelSchoolConstants.STUDENT_ROLE); // Role from the DB or its entity
        person.setRoles(role);      // Get the person object and assign the role
        person.setPwd(passwordEncoder.encode(person.getPwd())); //getting the plain text password from the object and decoding it.
        person = personRepository.save(person);  // saving the data and hashed password. 
        // if saved return true
        if (null != person && person.getPersonId() > 0) {
            isSaved = true;
        }
        return isSaved;
    }
````

3. Login Configurations, getting the password from the user, hashing it with the Password Encoder, which is
   BCryptPasswordEncoder in our case and checking the hashed values inside our DB against the hashed value from the
   password of the user. Class ``ModelSchoolUsernamePwdAuthenticationProvider``. Important function -
    1. passwordEncoder.matches(rawPwd, storedEncodedPwd).

````java
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
            return new UsernamePasswordAuthenticationToken(person.getName(), null, getGrantedAuthorities(person.getRoles()));
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
````
4. Bypassing JPA validations during saving transaction. Since Spring Boot MVC has validated all the entries for the
first time, and the pwd is encoded, now JPA is validation the fields but the password is encoded, hence the
fields are not matching, so we are bypassing this validation if the password is starting from ``$2a`` because
that's the hashed password. Class ``FieldsValueMatchValidator``

````java
@Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Object fieldValue = new BeanWrapperImpl(value)
                .getPropertyValue(field);
        Object fieldMatchValue = new BeanWrapperImpl(value)
                .getPropertyValue(fieldMatch);
        if (fieldValue != null) {
            if (fieldValue.toString().startsWith("$2a")) {
                return true;
            } else {
                return fieldValue.equals(fieldMatchValue);
            }
        } else {
            return fieldMatchValue == null;
        }
    }
}
````

5. You can also disable the second time validations by putting the following property in the application.properties file.
``spring.jpa.properties.javax.persistance.validation.mode = none``




