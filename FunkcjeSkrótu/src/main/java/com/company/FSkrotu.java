package com.company;
import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;

public abstract class FSkrotu {
    public MessageDigest getShortcutType() {
        return shortcutType;
    }

    public void setShortcutType(MessageDigest shortcutType) {
        this.shortcutType = shortcutType;
    }

    private MessageDigest shortcutType;
    public byte[] generateShortcut(String msg) {
        this.getShortcutType().update(msg.getBytes());
        byte[] digest = getShortcutType().digest();
        return digest;
    }
    public void generateAndPrintShortcut(String msg){
        String myHash = DatatypeConverter.printHexBinary(generateShortcut(msg)).toUpperCase();
        System.out.println(myHash);
    }
    public void checkTime(String msg){
        double start ,stop, counter = 0, duration;
        generateAndPrintShortcut(msg);
        int iloscWykonan = 10000;
        for (int i = 0; i < iloscWykonan ; i++) {
            start = System.currentTimeMillis();
            generateShortcut(msg);
            stop = System.currentTimeMillis();
            duration = (stop - start);
            counter+=duration;
        }
        double srednia = counter/iloscWykonan;

        System.out.println("sredni czas ze 100 wykonan: "+srednia+"ms");

    }
}
