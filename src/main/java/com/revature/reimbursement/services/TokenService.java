package com.revature.reimbursement.services;

import com.revature.reimbursement.dtos.response.Principal;
import com.revature.reimbursement.util.JwtConfig;
import com.revature.reimbursement.util.annotations.Inject;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;

import java.util.Date;

public class TokenService {
    @Inject
    private JwtConfig jwtConfig;

    //region <constructors>
    public TokenService(){

    }

    @Inject
    public TokenService(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }
    //endregion


    //region <methods>
    public String generateToken(Principal subject){
        long now = System.currentTimeMillis();
        JwtBuilder tokenBuilder = Jwts.builder()
                .setId(subject.getId()) //get id token is assigned to
                .setIssuer("reimbursement") //token is issued by application
                .setIssuedAt(new Date(now)) //date token is issued
                .setExpiration(new Date(now + jwtConfig.getExpiration())) //date when token expires
                .setSubject(subject.getUsername()) //get username token is assigned to
                .claim("role", subject.getRole()) //get role token is assigned to
                .signWith(jwtConfig.getSigAlg(), jwtConfig.getSigningKey()); //token generation
        return tokenBuilder.compact(); //return in String form
    }

    //extract user details based on their assigned authentication token
    public Principal extractRequesterDetails(String token){
        try{
            Claims claims = Jwts.parser()
                    .setSigningKey(jwtConfig.getSigningKey())
                    .parseClaimsJws(token)
                    .getBody();

            return new Principal(claims.getId(), claims.getSubject(), claims.get("role", String.class));
        } catch(Exception e){ //to be specified
            return null;
        }
    }
    //endregion
}
