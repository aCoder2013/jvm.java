package org.song.entry;

import org.junit.Test;
import org.song.core.entry.Entry;
import org.song.core.entry.ZipEntry;

import java.nio.ByteBuffer;

/**
 * Created by song on 16/9/4.
 */
public class ZipEntryTest {

    @Test
    public void readZip() throws Exception {
        Entry entry = new ZipEntry("/Users/song/.m2/repository/redis/clients/jedis/2.8.1/jedis-2.8.1.jar");
        byte[] bytes = entry.readClass("redis.clients.jedis.Builder");
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
        System.out.println(Integer.toHexString(byteBuffer.getInt()).toUpperCase());
    }
}
