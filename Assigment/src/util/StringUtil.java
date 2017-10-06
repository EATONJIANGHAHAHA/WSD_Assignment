package util;

/**
 * Serves as a tool for some string operations.
 */
public class StringUtil {

    /**
     * Read the error message from the exception which comes from the validation of xml.
     * @param msg
     * @return
     */
    public static String readExceptionCause(String msg){
       String[]s1 = msg.split(" type ");
       String[] s2 = s1[s1.length-1].split("\'");
       return s2[1];
    }

    public static String readQueryString(String url){
        String[] s = url.split("=");
        return s[s.length-1];
    }
}
