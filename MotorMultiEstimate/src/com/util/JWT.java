package com.util;

import java.security.Key;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.json.simple.JSONObject;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWT 
{

	private JwtBuilder builder = null;
    
    private String issuer;
    private String subject;
    private static int exTimeMin = 10;
    
    private static final String[] KEY_WORLD = {"jti","iat","sub","iss","exp"};
    private static final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS512;
    private static final String SECRET_KEY = "oeRaYY7Wo24sDqKSX3IM9ASGmdGamkTd9jo1QTy4b7P9Ze5_9hKolVX3xNrQDcNRfVEdTZlOuOyqEGhXEbdJI-ZQ59k_o9MI0y3eZN2lp9jgw55FfXMiINEdt1Xq85VipRLSOkT6kSpzs2x-jbLDiz9ifVzkd81YKxMgPA7VfZeQUm4a-mOmnWMaVX30zGFU4L3oPBctYKkl4dYfhYWqRNfrgPJVi5DGFjywgxx0ASEiJHtV72paI3fdR2XwlSkyhhmY-ICjCRmsJN4fX1pdoL8a12-aQrvyu4j0Os6dVPYIoPvvY0SAZtWYKHfM1517A3HD4cVREf9cUsprCRK93w";
    
    public JWT(String _issuer, String _subject)
    {
    	this(_issuer,_subject, 10);
    }
    
    public JWT(String _issuer, String _subject, int _sessionTime)
    {
    	builder = Jwts.builder();
    			
    	this.issuer = _issuer;
    	this.subject = _subject;
    	this.exTimeMin = _sessionTime;
    }
    
    /**
     * 사용자 정의 값 넣기
     * @param _key
     * @param _value
     * @return
     */
    public boolean addParam(String _key, String _value)
    {
    	boolean result = false;
    	
    	try 
    	{
    		boolean check = true;
    		for(String temp : KEY_WORLD)
    		{
    			if(temp.equals(_key.trim().toLowerCase()))
    			{
    				check = false;
    				break;
    			}
    		}
    		
    		if(builder != null && check)
        	{
        		builder.claim(_key, _value);
        		result = true;
        	}
		} 
    	catch (Exception e)
    	{
    		result = false;
		}
    	
    	return result;
    }
    
    /**
     * 토큰 생성
     * @param _id
     * @param _ttlMillis (분)
     * @return
     * @throws Exception
     */
    public String encrypt(String _id, int _ttlmin) throws Exception
    {
    
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        builder.setId(_id);
        builder.setIssuedAt(now); // 시작 시간
        builder.setSubject(this.subject);
        builder.setIssuer(this.issuer);
        builder.signWith(signatureAlgorithm, signingKey);
   	
        if (_ttlmin >= 0) {
        	long sum = (_ttlmin * 60) * 1000;
        	
            long expMillis = nowMillis +  sum;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            builder.claim("exp_datetime", df.format(exp));
        }
        
        return builder.compact();
    }
   
    /**
     * 사용자 session 체크
     * @param _jwt
     * @return
     */
    public static boolean getSession(String _jwt)
    {
    	boolean result = false;
    	
    	try
    	{
    		Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                    .parseClaimsJws(_jwt).getBody();
    		result = true;
    	}
    	catch(Exception ex001)
    	{
    		result = false;
    	}
    	
    	return result;
    }
    
    /**
     * 시간안에 들어온 토큰의 시간 정보를 수정후 리턴
     * @param _jwt
     * @return 토큰
     */
    public static String updateExpiration(String _jwt)
    {
    	String result = null;
    	
    	try
    	{
            byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
            Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
            
            Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                    .parseClaimsJws(_jwt).getBody();
            
            long expMillis = System.currentTimeMillis() +  (exTimeMin * 60 * 1000);
            Date exp = new Date(expMillis);
            claims.setExpiration(exp);
            
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            claims.put("exp_datetime", df.format(exp));
            
            JwtBuilder builderUpdate = Jwts.builder();
            builderUpdate.signWith(signatureAlgorithm, signingKey);
            builderUpdate.setClaims(claims);
            result =  builderUpdate.compact();
    	}
    	catch(Exception ex001)
    	{
    		result = null;
    	}
    	
    	return result;
    }
    
    /**
     * 본문 해독
     * @param _jwt
     * @return
     * @throws Exception
     */
    public static Claims decrypt(String _jwt) 
    {
    	Claims claims = null;
    	
    	try 
    	{
    		claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                    .parseClaimsJws(_jwt).getBody();
		} 
    	catch (Exception e) 
    	{
    		claims = null;
		}    	
        return claims;
    }
    
    /**
     * 사용자 아이디
     * @param _jwt key
     * @return user_id
     */
    public static String getId(String _jwt)
    {
    	String result = null;
    	
    	try
    	{
    		result = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                    .parseClaimsJws(_jwt).getBody().getId();
    	}
    	catch(Exception ex001)
    	{
    		result = null;
    	}
    	
    	return result;
    	
    	
    }
    
    public static JSONObject getJson(String _jwt)
    {
    	JSONObject resultJson = new JSONObject();
    	
    	try
    	{
    		Claims claims = decrypt(_jwt);
    		if(claims != null)
    		{
    			
    			String temp = claims.toString().trim();
            	String[] jsonTemp = temp.substring(1, temp.length()-1).split(",");
            	
            	for(String tempKV : jsonTemp)
            	{
            		String[] kv = tempKV.split("=");
            		
            		boolean checkKey = true;
            		for(String keyCheck : KEY_WORLD)
            		{
            			if(kv[0].trim().equals(keyCheck))
            			{
            				checkKey = false;
            				break;
            			}	
            		}
            		if(checkKey)
            		{
            			resultJson.put(kv[0].trim(), kv[1].trim());
            		}
            	}
    		}
    		else
    		{
    			resultJson = null;
    		}
    	}
    	catch(Exception ex001)
    	{
    		resultJson = null;
    	}
    	
    	return resultJson;
    }
}
