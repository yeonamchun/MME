package com.util;

import java.security.Key;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWT {

    private static String SECRET_KEY = "oeRaYY7Wo24sDqKSX3IM9ASGmdGamkTd9jo1QTy4b7P9Ze5_9hKolVX3xNrQDcNRfVEdTZlOuOyqEGhXEbdJI-ZQ59k_o9MI0y3eZN2lp9jgw55FfXMiINEdt1Xq85VipRLSOkT6kSpzs2x-jbLDiz9ifVzkd81YKxMgPA7VfZeQUm4a-mOmnWMaVX30zGFU4L3oPBctYKkl4dYfhYWqRNfrgPJVi5DGFjywgxx0ASEiJHtV72paI3fdR2XwlSkyhhmY-ICjCRmsJN4fX1pdoL8a12-aQrvyu4j0Os6dVPYIoPvvY0SAZtWYKHfM1517A3HD4cVREf9cUsprCRK93w";
    
    private JwtBuilder builder = null;
    
    private String issuer;
    private String subject;
    
    public JWT(String _issuer, String _subject)
    {
    	builder = Jwts.builder();
    			
    	this.issuer = _issuer;
    	this.subject = _subject;
    }
    
    private static final String[] KEY_WORLD = {"jti","iat","sub","iss","exp"};
  
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
     * @param _ttlMillis
     * @return
     * @throws Exception
     */
    public String encrypt(String _id,long _ttlMillis) throws Exception
    {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        builder.setId(_id);
        builder.setIssuedAt(now); // 시작 시간
        builder.setSubject(this.subject);
        builder.setIssuer(this.issuer);
        builder.signWith(signatureAlgorithm, signingKey);
   	
        if (_ttlMillis >= 0) {
            long expMillis = nowMillis + _ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
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
    	Date now = new Date();
    	
    	try
    	{
    		Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                    .parseClaimsJws(_jwt).getBody();
    		
    		Date end = claims.getExpiration();
    	
    		claims.setExpiration(new Date());
    	
    		result = true;
    		
    		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    		System.out.println(">>>> [ "+format.format(now)+" < "+format.format(end)+" ] <<<< ["+result+"]");
    	}
    	catch(Exception ex001)
    	{
    		result = false;
    	}
    	
    	return result;
    }
    
    /**
     * 본문 해독
     * @param _jwt
     * @return
     * @throws Exception
     */
    public static Claims decrypt(String _jwt) throws Exception
    {
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                .parseClaimsJws(_jwt).getBody();
        return claims;
    }
}
