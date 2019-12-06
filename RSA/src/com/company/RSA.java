package com.company;


import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class RSA {
    private int p ;
    private int q;
    private int phi;
    private int n;
    private int e, d;
    //private int publicKey,privateKey;
    Random random = new Random();

    public RSA()
    {
        this.q = generateRandomPrime(random);
        this.p = generateRandomPrime(random);
        this.phi = (p-1)*(q-1);
        this.n = p*q;
        this.e = generateProperE(random);
        this.d = generateProperD(random);
    }

    public int[] encryprion(String msg){
        int[] encryptedLetters= new int[msg.length()];
        byte[] msgBytes = msg.getBytes();
        for (int i = 0; i < msg.length(); i++) {
            int h = (int)((Math.pow(msgBytes[i],e))%n);
            encryptedLetters[i] = h;
        }
        return encryptedLetters;
    }
    public String encryption1(String message)
    {
        BigInteger msgBytes = new BigInteger(message.getBytes());
        msgBytes.pow(e).mod(BigInteger.valueOf(n));
        return msgBytes.toString();
    }
    public String decryption(int[] encryptedLetters){

        byte[] msgBytes = new byte[encryptedLetters.length];
        for (int i = 0; i < encryptedLetters.length ; i++) {
            int h =  (int)Math.pow(encryptedLetters[i],d)%n;
            msgBytes[i] = (byte)h;
        }

        //return new String(msgBytes);
        return msgBytes.toString();
    }
    public String decryption1(String message) {
        BigInteger msgBytes = new BigInteger(message);
        msgBytes.modPow(BigInteger.valueOf(d), BigInteger.valueOf(n));
        return new String(msgBytes.toByteArray(), StandardCharsets.UTF_8);
    }


    public static int NWD_1(int pierwsza, int druga)
    {
        while (pierwsza != druga) // dopóki dwie liczby nie są sobie równe
        {
            if (pierwsza > druga)  // sprawdzamy, która z nich jest większa
            {
                pierwsza = pierwsza - druga; // odejmujemy mniejszą liczbę
            }                               // od większej
            else
            {
                druga = druga - pierwsza;
            }
        }
        return pierwsza;
    }
    public int generateProperE(Random rand)
    {
        int temp;
        while(true)
        {
            int p = rand.nextInt(10000);
            if(NWD_1(phi,p) == 1&& p!=1 && ifPrime(p) == true ) {
                temp = p;
                break;
            }
        }
        return temp;
    }
    public int generateProperD(Random rand)
    {
        int i = 3;
        while(true){
            if(i*e%phi==1){
                break;
            }
            i++;
        }

        System.out.println(i);
        return i;
    }
    public int generateRandomPrime(Random rand)
    {
        int temp;
        while(true)
        {
            int p = rand.nextInt(9999)+1000;

            if(ifPrime(p)==true) {
                temp = p;
                break;
            }
        }
        return temp;
    }
    public boolean ifPrime(int n)
    {
        if(n<2)
            return false; //gdy liczba jest mniejsza niż 2 to nie jest pierwszą

        for(int i=2;i*i<=n;i++)
            if(n%i==0)
                return false; //gdy znajdziemy dzielnik, to dana liczba nie jest pierwsza
        return true;
    }
}
