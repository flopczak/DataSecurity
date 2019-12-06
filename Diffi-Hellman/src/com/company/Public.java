package com.company;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Public {
    private BigInteger g;
    private BigInteger n;
    SecureRandom random = new SecureRandom();

    public Public(){
        this.n = BigInteger.probablePrime(16,random);
        this.g = getPrimitiveRoot();
    }


    private Map<Integer, Integer> getPrimeFactor(int p) {
        Map<Integer, Integer> map = new HashMap<>();
        while (p % 2 == 0) {
            insertToMap(2, map);
            p /= 2;
        }
        for (int i = 3; i <= Math.sqrt(p); i += 2) {
            while (p % i == 0) {
                insertToMap(i, map);
                p /= i;
            }
        }
        if (p > 2)
            insertToMap(p, map);
        return map;
    }

    private void insertToMap(int i, Map<Integer, Integer> map) {
        map.merge(i, 1, Integer::sum);
    }

    private BigInteger getPrimitiveRoot()
    {
        int m = this.n.intValue() - 1;
        int primeRoot = 0;
        Map<Integer, Integer> primeFactor = getPrimeFactor(m);
        primeFactor.replaceAll((k, v) -> m / k);
        for (int i = 2; i <= m; i++) {
            boolean notPrimeRoot = false;
            for (Map.Entry<Integer, Integer> map : primeFactor.entrySet()) {
                if(BigInteger.valueOf(i).modPow(BigInteger.valueOf(map.getValue()), BigInteger.valueOf(this.n.intValue())).equals(BigInteger.ONE))
                    notPrimeRoot = true;
            }
            if (!notPrimeRoot) {
                primeRoot = i;
                break;
            }
        }
        return new BigInteger(String.valueOf(primeRoot));
    }
    public BigInteger generateRandomPrime() {
        BigInteger temp;
        while(true)
        {
            BigInteger p = BigInteger.valueOf(random.nextInt(10000000)+1000000);

            if(ifPrime(p)==true) {
                temp = p;
                break;
            }
        }
        return temp;
    }
    public boolean ifPrime(BigInteger n) {
        if(n.intValue()<2)
            return false; //gdy liczba jest mniejsza niż 2 to nie jest pierwszą

        for(int i=2;i*i<=n.intValue();i++)
            if(n.intValue()%i==0)
                return false; //gdy znajdziemy dzielnik, to dana liczba nie jest pierwsza
        return true;
    }
    public BigInteger getG() {
        return g;
    }
    public void setG(BigInteger g) {
        this.g = g;
    }
    public BigInteger getN() {
        return n;
    }
    public void setN(BigInteger n) {
        this.n = n;
    }
}
