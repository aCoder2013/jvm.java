package org.song.helper;

/**
 * Created by song on 16/9/4.
 */
public class AssertUtils {

    private AssertUtils() {
    }

    public static void notEmpty(String string){
        notEmpty(string,"string can't be null");
    }

    public static void notEmpty(String string,String msg){
        if(string == null || string.length() == 0){
            throw new RuntimeException(msg);
        }
    }


    public static void notNull(Object object){
        notNull(object,"object can't be null");
    }

    public static void notNull(Object object,String message){
        if(object == null){
            throw new RuntimeException(message);
        }
    }
}
