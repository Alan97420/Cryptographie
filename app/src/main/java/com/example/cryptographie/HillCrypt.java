package com.example.cryptographie;

public class HillCrypt {

    int matriceCle[][];
    int matriceCleInv;
    int det;
    int message[];

    public HillCrypt(String key) {
        this.matriceCle = new int[2][2];
        int k = 0;
        for (int i = 0; i < 2; i++)
        {
            for (int j = 0; j < 2; j++)
            {
                matriceCle[i][j] = (ASUtil.getAscii(key.charAt(k)));
                k++;
            }
        }
        this.det = (matriceCle[0][0]*matriceCle[1][1] - matriceCle[0][1]*matriceCle[1][0])%256;
        matriceCleInv = -1;

        for (int i = 0; i < 256; i++) {
            int invt = det*i;
            if (invt%256 ==1) {
                matriceCleInv = i;
                break;
            } else {
                continue;
            }
        }



    }

    public String crypt(String text) {
        if(matriceCleInv == -1) {
            return "cle non valide !";
        }
        int lmsg = text.length();
        if (lmsg % 2 != 0) {
            lmsg++;
        }
        message = new int[lmsg];
        for(int i = 0; i< lmsg; i++) {
            if(i >= text.length()) {
                message[i] = ASUtil.getCode('x');
            } else {
                message[i] = ASUtil.getCode(text.charAt(i));
            }

        }
        int j =0;
        int mMessage[][] = new int[2][lmsg/2];
        for(int i =0; i<=(lmsg/2)+1;i+=2) {
            mMessage[0][j] = message[i];
            mMessage[1][j] = message[i+1];
            j++;
        }

        String textCrypt = "";

        for (int i = 0; i < (lmsg/2); i++) {
            int t1 = mMessage[0][i] * matriceCle[0][0] + mMessage[1][i]*matriceCle[0][1];
            textCrypt += ASUtil.getCode((char)(t1 % 256));
            int t2 = mMessage[0][i] * matriceCle[1][0] + mMessage[1][i]*matriceCle[1][1];
            textCrypt += ASUtil.getCode((char)(t2 % 256));
        }



        return textCrypt;
    }

    public String decrypt(String text) {
        if(matriceCleInv == -1) {
            return "cle non valide !";
        }
        int lmsg = text.length();
        if (lmsg % 2 != 0) {
            lmsg++;
        }
        message = new int[lmsg];
        for(int i = 0; i< lmsg; i++) {
            if(i > text.length()) {
                message[i] = 0;
            } else {
                message[i] = ASUtil.getCode(text.charAt(i));
            }

        }
        int j = 0;
        int mMessage[][] = new int[2][lmsg/2];
        for(int i =0; i<=(lmsg/2)+1;i+=2) {
            mMessage[0][j] = message[i];
            mMessage[1][j] = message[i+1];
            j++;
        }

        int tp1 = matriceCle[0][0];
        matriceCle[0][0] = matriceCle[1][1];
        matriceCle[1][1]= tp1;
        matriceCle[0][1] = (matriceCle[0][1] * -1)%256;
        matriceCle[1][0] = (matriceCle[1][0] * -1)%256;

        for (int i = 0; i < 2; i++) {
            for ( j = 0; j < 2; j++) {
                matriceCle[i][j] = (matriceCle[i][j] * matriceCleInv)%256;
            }
        }





        String textDecrypt = "";

        for (int i = 0; i < lmsg/2; i++) {
            int t1 = mMessage[0][i] * matriceCle[0][0] + mMessage[1][i]*matriceCle[0][1];
            textDecrypt += (char) ASUtil.getAscii((char) (t1 % 256));
            int t2 = mMessage[0][i] * matriceCle[1][0] + mMessage[1][i]*matriceCle[1][1];
            textDecrypt += (char) ASUtil.getAscii((char) (t2 % 256));
        }

        return  textDecrypt;
    }


}
