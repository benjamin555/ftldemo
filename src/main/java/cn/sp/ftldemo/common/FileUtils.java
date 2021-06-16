package cn.sp.ftldemo.common;

import com.google.common.collect.Lists;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.List;

/**
 * @Author: chenjiazhen
 * @Date: 2020/9/11 15:02
 */
public class FileUtils {
    public static String getTxt(String path) {
        URL resource = FileUtils.class.getResource(path);
        File file = new File(resource.getFile());

        return txt2String (file);
    }


    public static String txt2String(File file){
        StringBuilder result = new StringBuilder();
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
            String s = null;
            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
                result.append("\n"+s);
            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return result.toString();
    }

    public static List<String> txt2List(File file){
        List<String> ls = Lists.newArrayList();
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
            String s = null;
            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
                ls.add(s);
            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return ls;
    }
    public static List<String> txt2List(String filepath){
        URL resource = FileUtils.class.getResource(filepath);
        File file = new File(resource.getFile());
        return txt2List(file);
    }
}
