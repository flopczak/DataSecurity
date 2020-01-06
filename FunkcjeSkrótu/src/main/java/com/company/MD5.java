package com.company;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 extends FSkrotu {

    MD5() throws NoSuchAlgorithmException {
        super();
        this.setShortcutType(MessageDigest.getInstance("MD5"));
    }
}
