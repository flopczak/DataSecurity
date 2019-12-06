package com.company;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64.*;


public class CBCimp {

    public static byte[] generateRandomIv(){
        int ivSize = 16;
        byte[] iv = "xdweoszoldhtczbr".getBytes();
//        SecureRandom random = new SecureRandom();
//        random.nextBytes(iv);

        return iv;
    }

    public static byte[] xorImput(byte[] iv,byte[] clean){
        byte[] xored = new byte[iv.length];
        int i =0;
        for (byte b : iv) {
            xored[i] = (byte) (b ^ clean[i++]);
        }

        return xored;
    }

public static String toString(byte[] b){
        String temp = "";
    for (byte c : b) {
        temp+=String.valueOf(c);
    }

        return temp;
}

    public static String encrypt(String input, String key) throws UnsupportedEncodingException {

        String cryptedString ="" ;
        byte[] imputByte = input.getBytes();
        String temp;
        byte[] encryptedText;
        java.util.Base64.Encoder encoder = java.util.Base64.getEncoder();
//        int i = 0;
//        int j = 0;
//        while(i<imputByte.length%16)
//        {
//            while(j%16<=15){
//
//                j++;
//            }
//
//            i++;
//        }


        try {
            SecretKeySpec skey = new SecretKeySpec(key.getBytes(), "AES");

            Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
            cipher.init(Cipher.ENCRYPT_MODE, skey);
            int i=0;
            byte[] iv = generateRandomIv();

            while(i<imputByte.length)
            {
                byte[] crypted = new byte[16];
                if(i+16>input.length()) {
                    temp = input.substring(i, input.length());
                    temp = String.format("%1$-16s",temp );
                    System.out.println("ENCYPTION-PADDED-TEMP"+temp);
                    byte[] bruh = xorImput(iv,temp.getBytes());
                    crypted = cipher.doFinal(bruh);

                    cryptedString += new String(crypted);
                }
                else{
                    temp = input.substring(i, i+16);
                    byte[] bruh = xorImput(iv,temp.getBytes());
                    byte[] lol = bruh;
                    System.out.println("ENCRYPTION-NOPADDED-TEMP_XORED(tostring method): "+bruh.toString());
                    System.out.println("ENCRYPTION-NOPADDED-TEMP_XORED(valueof method): "+ new String(bruh));
                    System.out.println("ENCRYPTION-NOPADDED-TEMP_XORED(encoder method): "+encoder.encodeToString(lol));
                    crypted = cipher.doFinal(bruh);

                    cryptedString += crypted.toString();

                }
                iv=crypted;
                i+=16;
            }


        } catch (Exception e) {
            System.out.println(e.toString());
        }


        return cryptedString;
    }
    public static byte[] encrypt1(String input, String key) throws UnsupportedEncodingException {
        byte[] grandFinalle = new byte[input.length()];
        byte[] imputByte = input.getBytes();
        String temp;
        try {
            SecretKeySpec skey = new SecretKeySpec(key.getBytes(), "AES");

            Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
            cipher.init(Cipher.ENCRYPT_MODE, skey);
            int i=0;
            byte[] iv = generateRandomIv();

            while(i<imputByte.length)
            {
                byte[] crypted = new byte[16];
                if(i+16>input.length()) {
                    temp = input.substring(i, input.length());
                    temp = String.format("%1$-16s",temp );
                    System.out.println("ENCYPTION-PADDED-TEMP"+temp);
                    byte[] bruh = xorImput(iv,temp.getBytes());
                    crypted = cipher.doFinal(bruh);
                }
                else{
                    temp = input.substring(i, i+16);
                    byte[] bruh = xorImput(iv,temp.getBytes());
                    crypted = cipher.doFinal(bruh);
                    int z = 0;
                    for (int j = i; j < i+16; j++) {
                        grandFinalle[j]=crypted[z];
                    z++;
                    }
                    z = 0;

                }
                iv=crypted;
                i+=16;
            }


        } catch (Exception e) {
            System.out.println(e.toString());
        }


        return grandFinalle;
    }

    public static String decrypt(String input, String key) {
        byte[] output = null;
        byte[] crypted;
        byte[] iv = generateRandomIv();
        String encrptedString = "";
        String temp="";
        java.util.Base64.Encoder encoder = java.util.Base64.getEncoder();
        try {
            SecretKeySpec skey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
            cipher.init(Cipher.DECRYPT_MODE, skey);
            int i =0;
            while(i<input.length())
            {

                    temp = input.substring(i, i+16);
                    temp = String.format("%1$-16s",temp );
                    System.out.println("temp:"+temp);
                    crypted = cipher.doFinal(temp.getBytes());
                    System.out.println("ECB-DEC_b4_XOR: "+ crypted.toString());
                    crypted = xorImput(iv,crypted);
                    encrptedString += new String(crypted);


                i+=16;
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        return (encrptedString);
    }
    public static String decrypt1(byte[] input, String key) {
        byte[] crypted;
        byte[] iv = generateRandomIv();
        String encrptedString = "";
        try {
            SecretKeySpec skey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
            cipher.init(Cipher.DECRYPT_MODE, skey);
            int i =0;
            while(i<input.length)
            {
                crypted = cipher.doFinal(input);
                System.out.println("ECB-DEC_b4_XOR: "+ crypted.toString());
                crypted = xorImput(iv,crypted);
                encrptedString += new String(crypted);


                i+=16;
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        return (encrptedString);
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        String output = new String(encrypt("ABCDEFGHIJKLEMNO", "abcdefghijklmopn"));
       System.out.println(decrypt(output,"abcdefghijklmopn"));

        System.out.println(decrypt1(encrypt1("jestem kuba a ty", "abcdefghijklmopn"),"abcdefghijklmopn"));
    }
}
