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

        String tmp[] = new String[size];
        int k =0;
        for (int i = 0; i < size; i++) {

            for (int j = 0; j < 8; j++) {
                if(k < text.length()){
                    tmp[j] +=  text.charAt(k);
                } else {
                    tmp[j] +=  ' ';
                }
                k++;
            }

        }


        String message = "";
        String mfinal = "";

        for (int i =0; i< size;i++){

            long m = ASUtil.StringTolong(tmp[i]);
            m =  (long) (Math.pow(m, key[0]) % key[1]);
            message = Long.toHexString(m);
            if(message.length()<4) {
                for (int j = 0; j < message.length()%5; j++) {
                    message = '0' + message;
                }
            }

            mfinal += message;
        }

        return mfinal;
    }

    public String decrypt(String text){
        long m =0;
        String mess = "";
        for (int i = 0; i < text.length(); i+=4) {
            m = Integer.parseInt(text.substring(i,i+5),16);
            m = (long) Math.pow((double)m,key[0]) % key[1];
            mess += ASUtil.LongToString(m);
        }



        return mess;
    }








}
