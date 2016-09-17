package org.song.core.entry;

/**
 * Created by song on 16/9/4.
 */
public interface Entry {

    byte[] readClass(String className) throws Exception;
}
