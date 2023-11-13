package com.construcao.api.security;

import org.springframework.web.bind.annotation.RequestMapping;

public class SecurityConstats {

    public static final String SECRET = "SecretKeyToGenJWTs";
    public static final long EXPIRATION_TIME = 864_000_000;
    public static final String TOKEN_PREFIX = "Bearer";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/login";
    public static final String STATUS_URL = "/status";

}
