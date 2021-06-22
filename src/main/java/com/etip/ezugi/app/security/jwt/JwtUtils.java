package com.etip.ezugi.app.security.jwt;

import com.etip.ezugi.app.constants.AppConstants;
import com.etip.ezugi.app.implementors.ClientDetailsServiceImpl;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(JwtUtils.class);

    @Value("${etip.app.jwtSecret}")
    private String jwtSecret;

    @Value("${etip.app.jwtExpirationMs}")
    private int jwtExpirationMs;

    public String generateJwtToken(Authentication authentication) {

        ClientDetailsServiceImpl userPrincipal = (ClientDetailsServiceImpl) authentication
                .getPrincipal();

        return Jwts.builder().setSubject((userPrincipal.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(
                        new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token)
                .getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            LOGGER.error(AppConstants.INVALID_JWT_SIGNATURE, e.getMessage());
        } catch (MalformedJwtException e) {
            LOGGER.error(AppConstants.INVALID_JWT_TOKEN, e.getMessage());
        } catch (ExpiredJwtException e) {
            LOGGER.error(AppConstants.JWT_TOKEN_EXPIRED, e.getMessage());
        } catch (UnsupportedJwtException e) {
            LOGGER.error(AppConstants.JWT_TOKEN_UNSUPPORTED, e.getMessage());
        } catch (IllegalArgumentException e) {
            LOGGER.error(AppConstants.JWT_TOKEN_CLAIMS_EMPTY, e.getMessage());
        }

        return false;
    }
}
