package cn.sp.ftldemo;

import cn.sp.ftldemo.common.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.List;

/**
 * @Author: chenjiazhen
 * @Date: 2021/5/16 10:58
 */
public class TxtTest {
    @Test
    public void regex() throws Exception{

        String path = "/txt/temp.txt";
        List<String> ls = FileUtils.txt2List(path);
        ls.forEach((v)->{
            System.out.println(v);
        });

    }
}
