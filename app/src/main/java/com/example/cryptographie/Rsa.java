package com.example.cryptographie;
import java.math.BigInteger;

public class Rsa {

    long key[];


    public Rsa(long e, long n){
        key = new long[2];
        this.key[0] = e;
        this.key[1] = n;
    }

    public Rsa(String k) {

    }

    public int pgcd(int m, int n) {
        int tmp = 0;

        while(n != 0)
        {
            tmp=m%n;
            m=n;
            n=tmp;
        }

        return m;

    }

    public String crypt(String text){

        long p1 = ASUtil.StringTolong(text);

        int size = (int) Math.ceil(text.length()/8);

        byte tmp[][] = new byte[size][8];
        int k =0;
        for (int i = 0; i < size; i++) {

            for (int j = 0; j < 8; j++) {
                if(k < text.length()){
                    tmp[k][j] = (byte) ASUtil.getCode(text.charAt(k));
                } else {
                    tmp[k][j] = (byte) ASUtil.getCode(' ');
                }
                k++;
            }

        }


        String message[] = new String[size];
        String mfinal = "";

        for (int i =0; i< size*8;i++){
            BigInteger tm = new BigInteger(tmp[i]);
            long m = tm.longValue();
            m =  (long) (Math.pow(m, key[0]) % key[1]);
            message[i] = new String(BigInteger.valueOf(m).toByteArray());
            mfinal += message[i];
        }

        return mfinal;
    }

    public String decrypt(String text){

        byte message[] = text.getBytes();
        BigInteger m = new BigInteger(message).modPow(BigInteger.valueOf(key[0]),BigInteger.valueOf(key[1]));
        message = m.toByteArray();

        String fmessage = "";
        for (int i = 0; i < message.length; i++) {
            fmessage += ASUtil.getAscii(message[i]);
        }

        return fmessage;
    }








}
