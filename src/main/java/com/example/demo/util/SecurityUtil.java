package com.example.demo.util;

import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
import java.util.*;
import com.example.demo.Domain.User;
import com.example.demo.Domain.response.ResLoginDTO;

@Service
public class SecurityUtil {
    private final JwtEncoder jwtEncoder;

    public SecurityUtil (JwtEncoder jwtEncoder){
        this.jwtEncoder = jwtEncoder;
    }

    public static final MacAlgorithm JWT_ALGORITHM = MacAlgorithm.HS512;

    // public String createAccessToken(User user) {
    //     User userToken = new User();
    //     userToken.setEmail(user.getEmail());
    //     userToken.setName(user.getPassword());

    //     Instant now = Instant.now();
    //     Instant validity = now.plus(this.accessTokenExpiration, ChronoUnit.SECONDS);
    // } 
    //     // hardcode permission (for testing)
    //     List<String> listAuthority = new ArrayList<String>();

    //     listAuthority.add("ROLE_USER_CREATE");
    //     listAuthority.add("ROLE_USER_UPDATE");

    //     // @formatter:off
    //     JwtClaimsSet claims = JwtClaimsSet.builder()
    //         .issuedAt(now)
    //         .expiresAt(validity)
    //         .subject(email)
    //         .claim("user", userToken)
    //         .claim("permission", listAuthority)
    //         .build();

    //     JwsHeader jwsHeader = JwsHeader.with(JWT_ALGORITHM).build();
    //     return this.jwtEncoder.encode(JwtEncoderParameters.from(jwsHeader, claims)).getTokenValue();

    // }

}
