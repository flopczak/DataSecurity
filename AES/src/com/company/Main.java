package com.company;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Main {
    public static void CBC1(String plainText) throws Exception {
        CBC cbc = new CBC();

        String key = "abcdefghijklmop";
        byte[] encrypted = cbc.encrypt(plainText, key);
        encrypted[17]=1;
        String result = cbc.decrypt(encrypted, key);
        long start = System.currentTimeMillis();
        encrypted = cbc.encrypt(plainText, key);
        long end = System.currentTimeMillis();
        System.out.println("CBC - encryption time:"+
                (end - start) + "ms");
        start = System.currentTimeMillis();
        result = cbc.decrypt(encrypted, key);
        end = System.currentTimeMillis();
        System.out.println("CBC - decrption time:"+
                (end - start) + "ms");
        char[] resultChar = result.toCharArray();
        char[] plainTextChar = plainText.toCharArray();
        int counter = 0;
        for (int i = 0; i < resultChar.length ; i++) {
            if(resultChar[i]!=plainTextChar[i])
            {
                counter+=1;
            }
        }
        System.out.println("liczba bledow cbc: "+counter);
    }
    public static void CFB(String plainText) throws Exception {
        CFB cfb = new CFB();
        String key = "abcdefghijklmop";
        long start = System.currentTimeMillis();
        byte[] encrypted = cfb.encrypt(plainText, key);
        encrypted[17]=0;
        long end = System.currentTimeMillis();
        System.out.println("CFB - encryption time:"+
                (end - start) + "ms");
        start = System.currentTimeMillis();
        String result = cfb.decrypt(encrypted, key);
        end = System.currentTimeMillis();
        System.out.println("CFB - decrption time:"+
                (end - start) + "ms");
        char[] resultChar = result.toCharArray();
        char[] plainTextChar = plainText.toCharArray();
        int counter = 0;
        for (int i = 0; i < resultChar.length ; i++) {
            if(resultChar[i]!=plainTextChar[i])
            {
                counter++;
            }
        }
        System.out.println("liczba bledow cfb: "+counter);
    }
    public static void OFB(String plainText) throws Exception {
        OFB ofb = new OFB();
        String key = "abcdefghijklmop";
        long start = System.currentTimeMillis();
        byte[] encrypted = ofb.encrypt(plainText, key);
        encrypted[17]=0;
        long end = System.currentTimeMillis();
        System.out.println("OFB - encryption time:"+
                (end - start) + "ms");
        start = System.currentTimeMillis();
        String result = ofb.decrypt(encrypted, key);
        end = System.currentTimeMillis();
        System.out.println("OFB - decrption time:"+
                (end - start) + "ms");
        char[] resultChar = result.toCharArray();
        char[] plainTextChar = plainText.toCharArray();
        int counter = 0;
        for (int i = 0; i < resultChar.length ; i++) {
            if(resultChar[i]!=plainTextChar[i])
            {
                counter++;
            }
        }
        System.out.println("liczba bledow ofb: "+counter);
    }
    public static void ECB(String plainText) throws Exception {
        ECB ecb = new ECB();
        String key = "abcdefghijklmop";
        long start = System.currentTimeMillis();
        byte[] encrypted = ecb.encrypt(plainText, key);
        encrypted[17]=0;
        long end = System.currentTimeMillis();
        System.out.println("ECB - encryption time:"+
                (end - start) + "ms");
        start = System.currentTimeMillis();
        String result = ecb.decrypt(encrypted, key);
        end = System.currentTimeMillis();
        System.out.println("ECB - decrption time:"+
                (end - start) + "ms");
        char[] resultChar = result.toCharArray();
        char[] plainTextChar = plainText.toCharArray();
        int counter = 0;
        for (int i = 0; i < resultChar.length ; i++) {
            if(resultChar[i]!=plainTextChar[i])
            {
                counter++;
            }
        }
        System.out.println("liczba bledow ECB: "+counter);
    }
    public static void CTR(String plainText) throws Exception {
        CRT crt = new CRT();
        String key = "abcdefghijklmop";
        long start = System.currentTimeMillis();
        byte[] encrypted = crt.encrypt(plainText, key);
        encrypted[17]=0;
        long end = System.currentTimeMillis();
        System.out.println("CTR - encryption time:"+
                (end - start) + "ms");
        start = System.currentTimeMillis();
        String result = crt.decrypt(encrypted, key);
        end = System.currentTimeMillis();
        System.out.println("CTR - decrption time:"+
                (end - start) + "ms");
        char[] resultChar = result.toCharArray();
        char[] plainTextChar = plainText.toCharArray();
        int counter = 0;
        for (int i = 0; i < resultChar.length ; i++) {
        if(resultChar[i]!=plainTextChar[i])
        {
            counter++;
        }
        }
        System.out.println("liczba bledow ctr: "+counter);
    }
    public static void main(String[] args) throws Exception {
        System.out.println("tekst20MB");
        String text = txtToString("C:\\Users\\user\\Desktop\\moje studia 2k17-18\\semestr5\\podstawy ochrony danych\\AES\\tekst.txt");

        CBC1(text);
        ECB(text);
        OFB(text);
        CFB(text);
        CTR(text);
        System.out.println("tekst10MB");
        String text1 = txtToString("C:\\Users\\user\\Desktop\\moje studia 2k17-18\\semestr5\\podstawy ochrony danych\\AES\\tekst2.txt");
        CBC1(text1);
        ECB(text1);
        OFB(text1);
        CFB(text1);
        CTR(text1);
        System.out.println("tekst5MB");
        String text2 = txtToString("C:\\Users\\user\\Desktop\\moje studia 2k17-18\\semestr5\\podstawy ochrony danych\\AES\\tekst1.txt");
        CBC1(text2);
        ECB(text2);
        OFB(text2);
        CFB(text2);
        CTR(text2);
        errPropagation();
    }

    static public String txtToString(String filePath) throws Exception{
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines( Paths.get(filePath), StandardCharsets.UTF_8))
        {
            stream.forEach(s -> contentBuilder.append(s));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return contentBuilder.toString();
    }
    public static void errPropagation() throws Exception{
        OFB ofb = new OFB();
        String key ="abcdefghijklmop";
        String txt = "Ala ma kota ale kot nie ma ali hehe maklowicz jest piata najsilniejsza osoba w polsce";
        System.out.println(txt);
        byte[] arr = ofb.encrypt(txt,key);
        arr[40]=0;
        String result = ofb.decrypt(arr,key);
        System.out.println(result);
    }

}
