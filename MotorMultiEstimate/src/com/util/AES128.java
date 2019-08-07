package com.util;

import java.security.Key;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;

public class AES128 
{
	public String key = "";
    public String iv = "";

    public AES128() {
        key = getKey();
        iv = key;
    }
    
    public AES128(String _key) {
        if (key.length() == 0) {
            key = _key.substring(0, 16);
        }

        if (iv.length() == 0) {
            iv = _key.substring(0, 16);
        }
    }

    public void setKey(String _key) {
        if (key.length() == 0) {
            key = _key.substring(0, 16);
        }

        if (iv.length() == 0) {
            iv = _key.substring(0, 16);
        }
    }
    
    String getKey() {
        String returnVal = "";

        String[] tempDay = { "0", "V", "B", "V", "Z", "A", "W", "E", "J", "W", "Z", "A", "N", "D", "I", "P", "K", "S", "B", "R", "Q", "K", "O", "I", "R", "G", "D", "E", "S", "C", "L", "T" };
        String[] tempMonth = { "0", "2", "R", "9", "I", "S", "h", "H", "5", "L", "3", "D", "A" };
        String[] tempString = { "0", "9", "G", "6", "1", "i", "Z", "c", "H", "j", "B", "4", "X", "s", "Y", "g", "K", "f", "L", "3", "Q", "a", "O", "o", "W", "h", "H", "1", "S", "9", "M", "s" };

        SimpleDateFormat formatyyyy = new SimpleDateFormat("yyyy");
		SimpleDateFormat formatMM = new SimpleDateFormat("MM");
		SimpleDateFormat formatdd = new SimpleDateFormat("dd");
		Date temp = new Date();
        String dateTempyyyy = formatyyyy.format(temp).toString();
		String dateTempMM = formatMM.format(temp).toString();
		String dateTempdd = formatdd.format(temp).toString();

        int NUMDay = Integer.parseInt(dateTempdd);
        int NUMMonth = Integer.parseInt(dateTempMM);

        try {
            returnVal = "" + dateTempyyyy + "0" + tempDay[NUMDay] + "0" + dateTempdd + tempMonth[NUMMonth] + dateTempMM + tempString[NUMDay] + "Y" + tempMonth[NUMMonth] + "d";

        } catch (Exception e) {
        }
        return returnVal;
    }

    String getKey(String id) {
        String returnVal = "";

        String[] tempDay = { "0", "V", "B", "V", "Z", "A", "W", "E", "J", "W", "Z", "A", "N", "D", "I", "P", "K", "S", "B", "R", "Q", "K", "O", "I", "R", "G", "D", "E", "S", "C", "L", "T" };
        String[] tempMonth = { "0", "2", "R", "9", "I", "S", "h", "H", "5", "L", "3", "D", "A" };
        String[] tempString = { "0", "9", "G", "6", "1", "i", "Z", "c", "H", "j", "B", "4", "X", "s", "Y", "g", "K", "f", "L", "3", "Q", "a", "O", "o", "W", "h", "H", "1", "S", "9", "M", "s" };

        
		SimpleDateFormat formatyyyy = new SimpleDateFormat("yyyy");
		SimpleDateFormat formatMM = new SimpleDateFormat("MM");
		SimpleDateFormat formatdd = new SimpleDateFormat("dd");
		Date temp = new Date();
        String dateTempyyyy = formatyyyy.format(temp).toString();
		String dateTempMM = formatMM.format(temp).toString();
		String dateTempdd = formatdd.format(temp).toString();

        int NUMDay = Integer.parseInt(dateTempdd);
        int NUMMonth = Integer.parseInt(dateTempMM);

        String key = id;
        if (key.length() >= 3) {
            char id1 = key.charAt(0);
            char id2 = key.charAt(1);
            char id3 = key.charAt(2);
            try {
                returnVal = "" + formatyyyy + id1 + tempDay[NUMDay] + id2 + dateTempdd + tempMonth[NUMMonth] + dateTempMM + tempString[NUMDay] + id3 + tempMonth[NUMMonth] + "d";
            } catch (Exception e) {
            }
        } else {
            try {
                returnVal = "" + formatyyyy + "0" + tempDay[NUMDay] + "0" + dateTempdd + tempMonth[NUMMonth] + dateTempMM + tempString[NUMDay] + "Y" + tempMonth[NUMMonth] + "d";
            } catch (Exception e) {
            }
        }
        return returnVal;
    }

    // KEY 생성
    Key getAESKey() throws Exception {
        key = getKey();
        byte[] keyBytes = new byte[16];
        byte[] b = key.getBytes("UTF-8");

        int len = b.length;
        if (len > keyBytes.length) {
            len = keyBytes.length;
        }
        System.arraycopy(b, 0, keyBytes, 0, len);
        Key keySpecTemp = new SecretKeySpec(keyBytes, "AES");

        return keySpecTemp;
    }

    Key getAESKey(String id) throws Exception {
        String key2 = "";
        String iv2 = "";
        key2 = getKey(id);
        iv2 = key2;

        byte[] keyBytes = new byte[16];
        byte[] b = key2.getBytes("UTF-8");

        int len = b.length;
        if (len > keyBytes.length) {
            len = keyBytes.length;
        }
        System.arraycopy(b, 0, keyBytes, 0, len);
        Key keySpecTemp = new SecretKeySpec(keyBytes, "AES");

        return keySpecTemp;
    }

    // 암호화
    public String Enc(String str) throws Exception {
        Key keySpec = getAESKey();
        Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
        c.init(Cipher.ENCRYPT_MODE, keySpec, new IvParameterSpec(iv.getBytes("UTF-8")));
        byte[] encrypted = c.doFinal(str.getBytes("UTF-8"));
        String enStr = new String(Base64.encodeBase64(encrypted));
        return enStr;
    }

    // 복호화
    public String Dec(String enStr) throws Exception {
        String decStr = enStr;

        if (enStr.length() >= 10) {
            Key keySpec = getAESKey();
            Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
            c.init(Cipher.DECRYPT_MODE, keySpec, new IvParameterSpec(iv.getBytes("UTF-8")));
            byte[] byteStr = Base64.decodeBase64(enStr.getBytes("UTF-8"));
            decStr = new String(c.doFinal(byteStr), "UTF-8");
            if (decStr.length() == 0) {
                decStr = enStr;
            }
        } else {
            decStr = enStr;
        }

        return decStr;
    }

    public String Dec(String enStr, String key) throws Exception {
        String key2 = "";
        String iv2 = "";
        key2 = getKey(key);
        iv2 = key2;
        String decStr = enStr;
        if (enStr.length() >= 10) {
            Key keySpec = getAESKey(key);
            Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
            c.init(Cipher.DECRYPT_MODE, keySpec, new IvParameterSpec(iv2.getBytes("UTF-8")));
            byte[] byteStr = Base64.decodeBase64(enStr.getBytes("UTF-8"));
            decStr = new String(c.doFinal(byteStr), "UTF-8");
            if (decStr.length() == 0) {
                decStr = enStr;
            }
        } else {
            decStr = enStr;
        }

        return decStr;
    }

}
