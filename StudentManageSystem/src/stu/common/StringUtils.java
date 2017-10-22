package stu.common;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.logging.Logger;

public class StringUtils {
	private static final String ALLCHAR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";    
	static Logger log=Logger.getLogger("StringUtils");
	
	/** 
	    * ��ȡ����λ������ַ���(0-9 a-z A-Z) 
	    * @param size λ�� 
	    * @return 
	    */  
	   public static final String getRandomNum(int size){  
	    StringBuffer sb = new StringBuffer();    
	    Random random = new Random();  
	    for (int i = 0; i < size; i++) {    
	        sb.append(ALLCHAR.charAt(random.nextInt(ALLCHAR.length())));    
	    }  
	    return sb.toString();  
	   }  
	   
	   /** 
	    * md5����(ITS) 
	    * @param str 
	    * @param charSet 
	    * @return 
	    */  
	   public synchronized static final String getMD5Str(String str,String charSet) { //md5����  
	    MessageDigest messageDigest = null;    
	    try {    
	        messageDigest = MessageDigest.getInstance("MD5");    
	        messageDigest.reset();   
	        if(charSet==null){  
	            messageDigest.update(str.getBytes());  
	        }else{  
	            messageDigest.update(str.getBytes(charSet));    
	        }             
	    } catch (NoSuchAlgorithmException e) {    
	        log.warning("md5 error:"+e.getMessage());  
	    } catch (UnsupportedEncodingException e) {    
	        log.warning("md5 error:"+e.getMessage());  
	    }    
	      
	    byte[] byteArray = messageDigest.digest();    
	    StringBuffer md5StrBuff = new StringBuffer();    
	    for (int i = 0; i < byteArray.length; i++) {                
	        if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)    
	            md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));    
	        else    
	            md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));    
	    }    
	    return md5StrBuff.toString();    
	}  
}
