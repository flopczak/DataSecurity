package com.company;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA2 extends FSkrotu {
    SHA2(String type) throws NoSuchAlgorithmException {
        super();
        this.setShortcutType(MessageDigest.getInstance(type));
    }
}
