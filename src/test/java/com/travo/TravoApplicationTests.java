package com.travo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.Normalizer;
import java.util.regex.Pattern;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TravoApplicationTests {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    public void contextLoads() {
        System.out.println(bCryptPasswordEncoder.matches("123456", "$2a$10$81JLWibccKsxvmftADjUlOPb81fgLZ9C2w19Ti1hm12eAi35p5IMe"));
    }

    private static String deAccent(String str) {
        String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(nfdNormalizedString).replaceAll("")
                .replaceAll("Đ", "D")
                .replaceAll("đ", "d");
    }
}
