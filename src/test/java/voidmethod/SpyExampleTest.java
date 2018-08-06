package voidmethod;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class SpyExampleTest {

    @Test
    public void testSpy() {
        Map<String, String> hashMap = new HashMap<String, String>();
        Map<String, String> hashMapSpy = spy(hashMap);

        System.out.println(hashMapSpy.get("key"));

        hashMapSpy.put("key", "A value");
        System.out.println(hashMapSpy.get("key"));

        when(hashMapSpy.get("key"))
            .thenReturn("Another value");
        System.out.println(hashMapSpy.get("key"));
    }
}
