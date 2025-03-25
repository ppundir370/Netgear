package com.netgear;
import java.util.Base64;

public class Decode {

    public static void main(String args[])
{
    String encodedText = "VGhpcyB3YXMgZWFzeS4gTGV0J3MgdHJ5IHNvbWV0aGluZyBoYXJkZXIuIE1yIENyeX\r\n" + //
                " B0byBuZWVkcyB5b3VyIGhlbHAgdG8gdW5kZXJzdGFuZCB0aGlzIHRleHQ6IAoKZ0FBQU\r\n" + //
                " FBQm5ibjhvTzBPN09tcXRxdWZjcDZOazVsNDQ4NEtwZ0xzNmFpaThLejJmX24yWFA2W\r\n" + //
                " mIzSUpmbXhPTzdpVHVfQXFZZWRPeTl3cEFLVk9ZNWttN3NxREpoVGR6dTJaQmxkbDg\r\n" + //
                " tdnd1bnJ2SGFMNjAyX1pPc09OLWtvRmJvOVNVZW13NHNjQm1JTkJFU1p0akJCUHljW\r\n" + //
                " UliNnV1WjZhV1E3MHl3bnNxWXJuOFp5cjVGYzJ1bVJrYUVnaFU1SlM4ZUt4VTlGQThLU\r\n" + //
                " 1ptTWVxd2VDbFlNNG1tNEN5TzNuems3UEh3aHQ4dXNZU3BLbU5CclFjY1dDenZHQ3h\r\n" + //
                " GbDRUX1EwdFRKTWsxSklRX1dXaEpDY3hRTWVLTWxCSlYwb0UwQW9NZDRBd19vN0Iz\r\n" + //
                " UWpURVFvckk9CgpIZSB0ZWxscyB5b3UgJ1NvbWV0aW1lcywgbWFjaGluZXMgaGF2ZS\r\n" + //
                " BuYW1lcyB0aGF0IGh1bWFucyBjYW4ndCByZWFkIHNvIHlvdSBuZWVkIGEgYm9vayB0by\r\n" + //
                " Bsb29rIHVwIHRoZWlyIG5hbWVzLiBDaGVjayB0aGUgcHViJ3MgcmVjb3JkcyBpbiB0aGU\r\n" + //
                " gYm9vayBhbmQgeW91J2xsIGZpbmQgc29tZXRoaW5nIHRoYXQgbWlnaHQgYmUgdXNl\r\n" + //
                " ZnVsLic=";
                encodedText = encodedText.replaceAll("[^A-Za-z0-9+/=]", "");
             try
             {
                byte[] decodedText =  Base64.getDecoder().decode(encodedText);
                String decoded = new String(decodedText);
                System.out.println(decoded); 
             }
             catch(Exception e)
             { 
                 System.out.println("Exception in decoding :" + e);
             }
              




}
    
                          
    
}
