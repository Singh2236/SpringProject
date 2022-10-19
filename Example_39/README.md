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


