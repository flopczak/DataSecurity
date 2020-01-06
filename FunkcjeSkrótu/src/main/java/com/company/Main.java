package com.company;

import java.security.NoSuchAlgorithmException;

public class Main {

    public static void main(String[] args) throws NoSuchAlgorithmException {
	    FSkrotu md5 = new MD5();
	    md5.checkTime("ala ma kota a kot ma ale");
	    FSkrotu sha1 = new SHA1();
	    sha1.checkTime("ala ma kota a kot ma ale");
        FSkrotu sha2a = new SHA2("SHA-224");
        sha2a.checkTime("ala ma kota a kot ma ale");
        FSkrotu sha2b = new SHA2("SHA-256");
        sha2b.checkTime("ala ma kota a kot ma ale");
        FSkrotu sha2c = new SHA2("SHA-384");
        sha2c.checkTime("ala ma kota a kot ma ale");
        FSkrotu sha2d = new SHA2("SHA-512");
        sha2d.checkTime("ala ma kota a kot ma ale");
        FSkrotu sha3 = new SHA3();
        sha3.checkTime("ala ma kota a kot ma ale");
        


    }
}
