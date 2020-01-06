package com.company;

import java.math.BigInteger;
import java.security.SecureRandom;

public class Client {
    private BigInteger x;
    private BigInteger y;
    private BigInteger k;
    SecureRandom random = new SecureRandom();
    public Client()
    {
        this.x = BigInteger.valueOf(random.nextInt(1000000000)+10000000);
        //this.x.equals(BigInteger.valueOf(random.nextInt(1000000)+10000));
    }



    public void carculateK(Client c,Public p){
        this.k = c.getY().modPow(this.x,p.getN());
    }
    public void carculateY(Public p){
        this.y = p.getG().modPow(this.x,p.getN());
    }
    public BigInteger getX() {
        return x;
    }
    public void setX(BigInteger x) {
        this.x = x;
    }
    public BigInteger getY() {
        return y;
    }
    public void setY(BigInteger y) {
        this.y = y;
    }
    public BigInteger getK() {
        return k;
    }
    public void setK(BigInteger k) {
        this.k = k;
    }
}
