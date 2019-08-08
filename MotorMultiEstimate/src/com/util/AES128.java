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
	private String key = "";
	private String iv = "";
    
	private String userKey = "";
    private String userIv = "";
    
    public AES128(String _key)
    {
        if (key.length() == 0) 
        {
            key = _key.substring(0, 16);
        }

        if (iv.length() == 0) 
        {
            iv = _key.substring(0, 16);
        }
    }

    public void setKey(String _key) 
    {
        if (key.length() == 0) 
        {
            key = _key.substring(0, 16);
        }

        if (iv.length() == 0) 
        {
            iv = _key.substring(0, 16);
        }
    }
    
    /**
     * 날짜를 조합 하여 키를 생성 한다.
     * @return
     */
    private String getKey() 
    {
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

        try 
        {
            returnVal = "" + dateTempyyyy + "0" + tempDay[NUMDay] + "0" + dateTempdd + tempMonth[NUMMonth] + dateTempMM + tempString[NUMDay] + "Y" + tempMonth[NUMMonth] + "d";
        } 
        catch (Exception e) {}
        
        return returnVal;
    }

    /**
     * 사용자 아이디를 입력 받아 날짜와 조합 하여 키를 생성 한다.
     * @param id 암호용 키
     * @return
     */
    private String getKey(String _id) 
    {
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

        String key = _id;
        if (key.length() >= 3) 
        {
            char id1 = key.charAt(0);
            char id2 = key.charAt(1);
            char id3 = key.charAt(2);
            try 
            {
                returnVal = "" + formatyyyy + id1 + tempDay[NUMDay] + id2 + dateTempdd + tempMonth[NUMMonth] + dateTempMM + tempString[NUMDay] + id3 + tempMonth[NUMMonth] + "d";
            } 
            catch (Exception e) {}
        } 
        else 
        {
            try 
            {
                returnVal = "" + formatyyyy + "0" + tempDay[NUMDay] + "0" + dateTempdd + tempMonth[NUMMonth] + dateTempMM + tempString[NUMDay] + "Y" + tempMonth[NUMMonth] + "d";
            } 
            catch (Exception e) {}
        }
        return returnVal;
    }

    // KEY 생성
    private Key getAESKey() throws Exception
    {
        userKey = getKey();
        userIv = userKey;
        
        byte[] keyBytes = new byte[16];
        byte[] b = userKey.getBytes("UTF-8");

        int len = b.length;
        if (len > keyBytes.length) 
        {
            len = keyBytes.length;
        }
        
        System.arraycopy(b, 0, keyBytes, 0, len);
        Key keySpecTemp = new SecretKeySpec(keyBytes, "AES");

        return keySpecTemp;
    }

    private Key getAESKey(String id) throws Exception 
    {
        String key2 = "";
        String iv2 = "";
        key2 = getKey(id);
        iv2 = key2;

        byte[] keyBytes = new byte[16];
        byte[] b = key2.getBytes("UTF-8");

        int len = b.length;
        if (len > keyBytes.length) 
        {
            len = keyBytes.length;
        }
        
        System.arraycopy(b, 0, keyBytes, 0, len);
        Key keySpecTemp = new SecretKeySpec(keyBytes, "AES");

        return keySpecTemp;
    }

    
    /**
     * 기준 키를 이용한 암호화
     * @param _str 암호화를 하고 싶은 문장
     * @return 암호화된 문장
     * @throws Exception
     */
    public String Enc(String _str) throws Exception 
    {
        Key keySpec = getAESKey(this.key);
        Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
        c.init(Cipher.ENCRYPT_MODE, keySpec, new IvParameterSpec(this.iv.getBytes("UTF-8")));
        byte[] encrypted = c.doFinal(_str.getBytes("UTF-8"));
        String enStr = new String(Base64.encodeBase64(encrypted));
        return enStr;
    }
    
    
    
    /**
     * 기준 키를 이용한 복호화
     * @param enStr 암호화된 문장
     * @return 복호화된 문장
     * @throws Exception
     */
    public String Dec(String _enStr) throws Exception 
    {
        String decStr = _enStr;

        if (_enStr.length() >= 10) 
        {
            Key keySpec = getAESKey(this.key);
            Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
            c.init(Cipher.DECRYPT_MODE, keySpec, new IvParameterSpec(this.iv.getBytes("UTF-8")));
            byte[] byteStr = Base64.decodeBase64(_enStr.getBytes("UTF-8"));
            decStr = new String(c.doFinal(byteStr), "UTF-8");
            if (decStr.length() == 0) 
            {
                decStr = _enStr;
            }
        } 
        else 
        {
            decStr = _enStr;
        }

        return decStr;
    }
    
    /**
     * 한번만 사용 하는 암호 ( 한번만 사용하는 키를 생성 한다. )
     * @param str 암호를 걸고자 하는 문장
     * @return 암호화 된 문장
     * @throws Exception
     */
    public String EncOTP(String _str) throws Exception 
    {
        Key keySpec = getAESKey();
        Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
        c.init(Cipher.ENCRYPT_MODE, keySpec, new IvParameterSpec(userIv.getBytes("UTF-8")));
        byte[] encrypted = c.doFinal(_str.getBytes("UTF-8"));
        String enStr = new String(Base64.encodeBase64(encrypted));
        return enStr;
    }
    
    /**
     * OPT 를 이용하여 암호화 된 문장을 복호화 한다.
     * @param enStr 암호화된 문장
     * @return 복호화 된 문장 
     * @throws Exception
     */
    public String DecOTP(String _enStr) throws Exception 
    {
        String decStr = _enStr;

        if (_enStr.length() >= 10) {
            Key keySpec = getAESKey();
            Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
            c.init(Cipher.DECRYPT_MODE, keySpec, new IvParameterSpec(userIv.getBytes("UTF-8")));
            byte[] byteStr = Base64.decodeBase64(_enStr.getBytes("UTF-8"));
            decStr = new String(c.doFinal(byteStr), "UTF-8");
            if (decStr.length() == 0) 
            {
                decStr = _enStr;
            }
        } 
        else 
        {
            decStr = _enStr;
        }

        return decStr;
    }

    /**
     * 사용자 키를 이용한 암호화 
     * @param _str 암호화 할 문장
     * @param _key 암호 키
     * @return 암호화 된 문장
     * @throws Exception
     */
    public String Enc(String _str, String _key) throws Exception 
    {
    	String key2 = getKey(_key);
        String iv2 = key2;
        
        Key keySpec = getAESKey(key2);
        Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
        c.init(Cipher.ENCRYPT_MODE, keySpec, new IvParameterSpec(iv2.getBytes("UTF-8")));
        byte[] encrypted = c.doFinal(_str.getBytes("UTF-8"));
        String enStr = new String(Base64.encodeBase64(encrypted));
        return enStr;
    }
    
    /**
     * 사용자가 임력 키를 이용해서 복호화 
     * @param enStr 암호화 된 문장 
     * @param key 암호 키
     * @return 복호화된 문장
     * @throws Exception
     */
    public String Dec(String _enStr, String _key) throws Exception 
    {
        String key2 = getKey(_key);
        String iv2 = key2;
        
        String decStr = _enStr;
        
        if (_enStr.length() >= 10) 
        {
            Key keySpec = getAESKey(key2);
            Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
            c.init(Cipher.DECRYPT_MODE, keySpec, new IvParameterSpec(iv2.getBytes("UTF-8")));
            byte[] byteStr = Base64.decodeBase64(_enStr.getBytes("UTF-8"));
            decStr = new String(c.doFinal(byteStr), "UTF-8");
            if (decStr.length() == 0) 
            {
                decStr = _enStr;
            }
        } 
        else 
        {
            decStr = _enStr;
        }
        return decStr;
    }

}
