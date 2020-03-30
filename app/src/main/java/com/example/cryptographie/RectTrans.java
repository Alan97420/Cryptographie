package com.example.cryptographie;// Code pour la cryptographie par transposition Rectangulaire



public class RectTrans {

    String key;
    int ckey[];
    int cpkey[];

    public RectTrans(String key) {
       this.key = key;
       this.cpkey = new int[key.length()];
       this.ckey = new int[key.length()];
       for (int i = 0; i < ckey.length; i++) {
           ckey[i] = ASUtil.getCode(key.charAt(i));
       }

        for(int i = 0; i<key.length(); i++) {
            cpkey[i] = -1;
        }

        int pp = 0;
        int ind = -1;
        int cpp = 9999;
        while(pp != key.length()) {
            for (int i = 0; i < ckey.length; i++) {
                if(ckey[i] < cpp) {
                    cpp = ckey[i];
                    ind = i;
                }
            }
            ckey[ind] = 9999;
            cpp = 9999;
            cpkey[pp] = ind;
            pp++;

        }

    }

    public String crypt(String text) {
        int row = (text.length() +key.length() -1) / key.length();
        int column = key.length();
        byte tab[][] = new byte[row][column];




        for(int i = 0; i < row; i++) {
            for(int j = 0; j < column; j++) {
                if((((i*(column-1)+i)+j)-1 <= text.length()-((row*column)-text.length()))) {
                    tab[i][j] = (byte) ASUtil.getCode(text.charAt((i*(column-1)+i)+j));
                } else {
                    tab[i][j] = (byte) ASUtil.getCode('z');
                }

            }
        }





        String textcrypt = "";

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                textcrypt += ASUtil.getAscii(tab[i][cpkey[j]]);
            }
        }

        return textcrypt;
    }

    public String decrypt(String text) {
        int row = (text.length() +key.length() -1) / key.length();
        int column = key.length();

        byte tab[][] = new byte[row][column];




        for(int i = 0; i < row; i++) {
            for(int j = 0; j < column; j++) {
                if((((i*(column-1)+i)+j) <= text.length()-((row*column)-text.length()))) {
                    tab[i][j] = (byte) ASUtil.getCode(text.charAt((i*(column-1)+i)+j));
                } else {
                    tab[i][j] = (byte) ASUtil.getCode('z');
                }

            }
        }

        String textdecrypt = "";

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                textdecrypt += ASUtil.getAscii(tab[i][cpkey[j]]);
            }
        }
        return textdecrypt;
    }
}
