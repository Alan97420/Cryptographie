package com.example.cryptographie;

public  class ASUtil {

    public static final char[] EXTENDED = { 0x00C7, 0x00FC, 0x00E9, 0x00E2,
            0x00E4, 0x00E0, 0x00E5, 0x00E7, 0x00EA, 0x00EB, 0x00E8, 0x00EF,
            0x00EE, 0x00EC, 0x00C4, 0x00C5, 0x00C9, 0x00E6, 0x00C6, 0x00F4,
            0x00F6, 0x00F2, 0x00FB, 0x00F9, 0x00FF, 0x00D6, 0x00DC, 0x00A2,
            0x00A3, 0x00A5, 0x20A7, 0x0192, 0x00E1, 0x00ED, 0x00F3, 0x00FA,
            0x00F1, 0x00D1, 0x00AA, 0x00BA, 0x00BF, 0x2310, 0x00AC, 0x00BD,
            0x00BC, 0x00A1, 0x00AB, 0x00BB, 0x2591, 0x2592, 0x2593, 0x2502,
            0x2524, 0x2561, 0x2562, 0x2556, 0x2555, 0x2563, 0x2551, 0x2557,
            0x255D, 0x255C, 0x255B, 0x2510, 0x2514, 0x2534, 0x252C, 0x251C,
            0x2500, 0x253C, 0x255E, 0x255F, 0x255A, 0x2554, 0x2569, 0x2566,
            0x2560, 0x2550, 0x256C, 0x2567, 0x2568, 0x2564, 0x2565, 0x2559,
            0x2558, 0x2552, 0x2553, 0x256B, 0x256A, 0x2518, 0x250C, 0x2588,
            0x2584, 0x258C, 0x2590, 0x2580, 0x03B1, 0x00DF, 0x0393, 0x03C0,
            0x03A3, 0x03C3, 0x00B5, 0x03C4, 0x03A6, 0x0398, 0x03A9, 0x03B4,
            0x221E, 0x03C6, 0x03B5, 0x2229, 0x2261, 0x00B1, 0x2265, 0x2264,
            0x2320, 0x2321, 0x00F7, 0x2248, 0x00B0, 0x2219, 0x00B7, 0x221A,
            0x207F, 0x00B2, 0x25A0, 0x00A0 };

    public static char getAscii(int code) {
        if (code >= 0x80 && code <= 0xFF) {
            return EXTENDED[code - 0x80];
        }
        return (char) code;
    }

    public static int getCode(char c) {
        int code = (int) c;

        if (code >= 0x80) {
            int i = 0;
            for (i = 0; i < EXTENDED.length; i++) {
                if(EXTENDED[i] == code) {
                    break;
                }
            }


            return ((i+128)%256);
        }

        return code;
    }

    public static int getCode(short c) {
        int code = (int) c;

        if (code >= 0x80) {
            int i = 0;
            for (i = 0; i < EXTENDED.length; i++) {
                if(EXTENDED[i] == code) {
                    break;
                }
            }


            return ((i+128)%256);
        }

        return code;
    }

    public static String StringToHexString(String m) {
        String hex = "";
        String tmp = "";
        for (int i = 0; i < m.length(); i++) {
            tmp = Integer.toBinaryString(getCode(m.charAt(i)));
            if(tmp.length()<2) {
                tmp = 0+tmp;
            }
            hex += tmp;
        }


        return hex;
    }

    public static String HexStringToString(String m) {
        String str = "";
        char tmp ;

        for (int i = 0; i < m.length(); i+=2) {
            tmp = getAscii(Integer.parseInt(m.substring(i,i+2),16));
            str += tmp;
        }

        return str;
    }

    public static String CharToString(char[] m) {
        String text = "";

        for (int i = 0; i < m.length; i++) {
            text += getAscii(m[i]);
        }

        return text;
    }

    public static String ShortToString(short[] m) {
        String text = "";

        for (int i = 0; i < m.length; i++) {
            text += getAscii(m[i]);
        }

        return text;
    }

    public static short[] StringToShort(String m) {
        short text[] = new short[m.length()];

        for (int i = 0; i < m.length(); i++) {
            text[i] = (short) getCode(m.charAt(i));
        }

        return text;
    }

    public static long StringTolong(String m) {
        long s = 0;
        String text = "";
        for (int i = 0; i < m.length(); i++) {
            text += "" + getCode(m.charAt(i));
        }
        return Long.parseLong(text);
    }

    public static String LongToString(long n) {
        String m = Long.toBinaryString(n);
        int csize = m.length() %9;

        for (int i = 0; i < csize; i++) {
            m = "0" + m;
        }
        String t = "";
        for(int i = 0; i < m.length();i+=8) {
            t += getAscii(Integer.parseInt(m.substring(i, i+7)));
        }

        return t;

    }


}