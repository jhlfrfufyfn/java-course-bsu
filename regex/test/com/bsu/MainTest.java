package com.bsu;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void checkEmail() {
        List<String> list = Arrays.asList("jhlfrfufyfn@gmail.com", "a-a@fa.ru",
                                          "-----@owooo.by", "q.-@qwe.by", "a@a.a");

        List<String> result = new ArrayList<>();

        for(String line: list){
            if(Main.checkEmail(line)){
                result.add(line);
            }
        }

        assertEquals(Arrays.asList(list.get(0), list.get(1), list.get(3)), result);
    }
}