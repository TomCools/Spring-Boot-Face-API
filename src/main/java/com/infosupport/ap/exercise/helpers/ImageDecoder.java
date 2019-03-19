package com.infosupport.ap.exercise.helpers;

import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class ImageDecoder {
    public byte[] decodeImage(String input) {
        return Base64.getDecoder().decode(input);
    }
}
