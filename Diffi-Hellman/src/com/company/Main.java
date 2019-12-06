package com.company;

public class Main {

    public static void main(String[] args) {
	Client a = new Client();
	Client b = new Client();
	Public p = new Public();
	a.carculateY(p);
	b.carculateY(p);
	a.carculateK(b,p);
	b.carculateK(a,p);
        System.out.println(a.getK() + "   |   " + b.getK());


    }
}
