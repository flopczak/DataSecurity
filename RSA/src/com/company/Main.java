package com.company;

public class Main {

    public static void main(String[] args) {
	RSA rsa = new RSA();
	String encryptred = rsa.encryption1("A B C D E F G H I J K L M N O P Q R S T U V W X Y Z A B C D E F G H I J K L M N O P Q R S T U V W X Y Z");
	String decrypted1 = rsa.decryption1(encryptred);
	System.out.println("decrypted1 msg: "+decrypted1);


    }
}
