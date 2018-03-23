package com.travo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.Normalizer;
import java.util.regex.Pattern;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TravoApplicationTests {

    @Test
    public void contextLoads() {
        System.out.println(deAccent("Đó"));
    }

    private static String deAccent(String str) {
        String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(nfdNormalizedString).replaceAll("")
                .replaceAll("Đ", "D")
                .replaceAll("đ", "d");
    }
}
