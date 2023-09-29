package com.oneday.researcher.util;

import org.springframework.stereotype.Component;

import java.security.KeyPair;
import java.security.KeyPairGenerator;

public class KeyGenerationUtility {

    public static KeyPair generateRSAKey() {

        KeyPair keyPair;
        try {
            KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
            generator.initialize(2048);
            keyPair = generator.generateKeyPair();
        } catch (Exception e) {
            throw new IllegalStateException("RSA key pair generation error");
        }
        return keyPair;
    }
}
