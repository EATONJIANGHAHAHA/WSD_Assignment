package util;

public class StringUtil {

    public static String readExceptionCause(String msg){
       String[]s1 = msg.split(" type ");
       String[] s2 = s1[s1.length-1].split("\'");
       return s2[1];
    }
}
