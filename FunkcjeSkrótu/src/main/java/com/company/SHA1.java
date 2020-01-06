package com.company;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA1 extends FSkrotu {
    SHA1() throws NoSuchAlgorithmException {
        super();
        this.setShortcutType(MessageDigest.getInstance("SHA-1"));
    }
}
