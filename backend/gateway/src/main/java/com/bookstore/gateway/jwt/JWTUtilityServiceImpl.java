package com.bookstore.gateway.jwt;

import com.bookstore.gateway.clients.UserClient;
import com.bookstore.gateway.dtos.UserDto;
import com.bookstore.gateway.dtos.http.UserRequest;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;
import com.nimbusds.jwt.JWTClaimsSet;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.text.ParseException;
import java.util.Base64;
import java.util.Date;


@Service
public class JWTUtilityServiceImpl {

    @Value("classpath:jwtKeys/private_key.pem")
    private Resource privateKeyResource;

    @Value("classpath:jwtKeys/public_key.pem")
    private Resource publicKeyResource;

    @Value("${jwt.expiration}")
    private long validityInMilliseconds;

    @Value("${jwt.refreshExpiration}")
    private long refreshValidityInMilliseconds;

    @Autowired
    private UserClient userClient;


    //Metodo que genera el token
    public String generateToken(Long userId) throws NoSuchAlgorithmException, InvalidKeySpecException, IOException, JOSEException {
        PrivateKey privateKey = getPrivateKey(privateKeyResource);
        JWSSigner signer = new RSASSASigner(privateKey);
        UserRequest userRequest = new UserRequest();
        String userName = "Anonymous";
        try {
            userRequest = userClient.getUserById(userId);
            if(userRequest.getStatus().value() == 200){
                userName = userRequest.getData().get(0).getUsername();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);
        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .subject(userId.toString())
                .claim("username", userName)
                .claim("roles", "USER")
                .issueTime(now)
                .expirationTime(validity)
                .build();
        SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.RS256), claimsSet);
        signedJWT.sign(signer);

        return signedJWT.serialize();
    }

    public String generateRefreshToken(Long userId) throws NoSuchAlgorithmException, InvalidKeySpecException, IOException, JOSEException {
        PrivateKey privateKey = getPrivateKey(privateKeyResource);
        JWSSigner signer = new RSASSASigner(privateKey);
        Date now = new Date();
        Date validity = new Date(now.getTime() + refreshValidityInMilliseconds);
        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .subject(userId.toString())
                .issueTime(now)
                .expirationTime(validity)
                .build();
        SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.RS256), claimsSet);
        signedJWT.sign(signer);

        return signedJWT.serialize();
    }

    public JWTClaimsSet parseJWT(String jwt) throws NoSuchAlgorithmException, InvalidKeySpecException, IOException, ParseException, JOSEException, ParseException {
        PublicKey publicKey = getPublicKey(publicKeyResource);

        SignedJWT signedJWT = SignedJWT.parse(jwt);

        JWSVerifier verifier = new RSASSAVerifier((RSAPublicKey) publicKey);
        if(!signedJWT.verify(verifier)) {
            throw new JOSEException("Invalid signature");
        }

        JWTClaimsSet claimsSet = signedJWT.getJWTClaimsSet();

        if(claimsSet.getExpirationTime().before(new Date())) {
            throw new JOSEException("Expired token");
        }
        return claimsSet;
    }

    public boolean validateToke(String token){
        try{
            parseJWT(token);
            return true;
        } catch (NoSuchAlgorithmException | InvalidKeySpecException | ParseException | IOException | JOSEException e) {
            throw new RuntimeException(e);
        }
    }

    public Long getUserIdFromToken(String token){
        try {
            return Long.parseLong(parseJWT(token).getSubject());
        } catch (NoSuchAlgorithmException | InvalidKeySpecException | IOException | ParseException | JOSEException e) {
            throw new RuntimeException(e);
        }
    }

    //Metodo que genera la clave privada recogiendola del directorio dónde la tenemos guardada y transformandola
    private PrivateKey getPrivateKey(Resource resource) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] keyBytes = Files.readAllBytes(Paths.get(resource.getURI()));
        String privateKeyPEM = new String(keyBytes, StandardCharsets.UTF_8)
                .replace("-----BEGIN PRIVATE KEY-----", "")
                .replace("-----END PRIVATE KEY-----", "")
                .replaceAll("\\s", "");

        byte[] decodedKey = Base64.getDecoder().decode(privateKeyPEM);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");

        return keyFactory.generatePrivate(new PKCS8EncodedKeySpec(decodedKey));
    }

    //Metodo que genera la clave pública recogiendola del directorio dónde la tenemos guardada y transformandola
    private PublicKey getPublicKey(Resource resource) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchAlgorithmException {
        byte[] keyBytes = Files.readAllBytes(Paths.get(resource.getURI()));
        String publicKeyPEM = new String(keyBytes, StandardCharsets.UTF_8)
                .replace("-----BEGIN PUBLIC KEY-----", "")
                .replace("-----END PUBLIC KEY-----", "")
                .replaceAll("\\s", "");

        byte[] decodedKey = Base64.getDecoder().decode(publicKeyPEM);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");

        return keyFactory.generatePublic(new X509EncodedKeySpec(decodedKey));
    }
}
