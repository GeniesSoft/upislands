package com.geniessoft.backend.security;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@Component
public class JwtConfig {

    @Value("${spring.security.jwt.uri:}")
    private String Uri;

    @Value("${spring.security.jwt.header:}")
    private String header;

    @Value("${spring.security.jwt.prefix:}")
    private String prefix;

    @Value("${spring.security.jwt.expiration:1234}")
    private int expiration;

    @Value("${spring.security.jwt.secret:}")
    private String secret;
}
