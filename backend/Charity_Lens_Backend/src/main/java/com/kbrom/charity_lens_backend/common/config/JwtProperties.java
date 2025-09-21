package com.kbrom.charity_lens_backend.common.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
@Getter
@Setter
@ConfigurationProperties(prefix="jwt")
public class JwtProperties {
    private String authJwtSecret;
    private String emailJwtSecret;
    private long emailJwtValiditySeconds;
    private long authJwtValiditySeconds;
}
