package cn.sp.ftldemo;

import cn.sp.ftldemo.common.FileUtils;
import com.google.common.collect.Lists;
import freemarker.template.*;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: chenjiazhen
 * @Date: 2020/9/11 15:01
 */
public class WmsTest {
    @Test
    public void txtProcess() throws Exception{

        String table = "biz_stock_input";
        //获取列集合和占位符集合

//        占位符集合
        List<String> vals = new ArrayList<String>();

        String path = "/wms/update-1.txt";
        String txt = FileUtils.getTxt(path);
        txt = txt.replaceAll("\\\n","");
        txt = txt.replaceAll(" ","");
        String[] split = txt.split(",#");
        for (int i = 0; i < split.length; i++) {
            String s = split[i];
            if(i>0){
                vals.add("#"+s);
            }else{
                vals.add(s);
            }
        }
        System.out.println(vals);

        // 列集合
        List<String> cols = new ArrayList<String>();
         path = "/wms/update-2.txt";
         txt = FileUtils.getTxt(path);
        txt = txt.replaceAll("\\\n","");
        txt = txt.replaceAll(" ","");
        split = txt.split(",");
        for (int i = 0; i < split.length; i++) {
            String s = split[i];
            cols.add(s);
        }
        System.out.println(cols);

//        拼接模版
        List<String> ms = new ArrayList<String>();
//        唯一约束集合
        List<List<String>> kss = new ArrayList<List<String>>();
        List<String> ks1 = new ArrayList<String>();
        ks1.add("id");
        kss.add(ks1);
        String[] ids = new String[] { "MAT_CODE", "BATCH", "FTY_CODE", "LOCATION_CODE", "WH_CODE", "TYPE_CODE", "BIN_CODE", "CELL_CODE"};
        List<String> ks2 = Lists.newArrayList(ids);
        kss.add(ks2);
        for (int i = 0; i < vals.size(); i++) {
            String v = vals.get(i);
            String c =  cols.get(i);
            ms.add(v +" " +c);
        }

        Map root = new HashMap();
        root.put("vals",vals);
        root.put("cols",cols);
        root.put("table",table);
        root.put("ms",ms);
        root.put("kss",kss);
        processTemp("wms.ftl", root);

    }

    protected void processTemp(String tempFile, Map root) throws IOException, TemplateException {
        Template temp = getTemp(tempFile);

        Writer out = new OutputStreamWriter(System.out);
        temp.process(root, out);
    }

    protected Template getTemp(String tempFile) throws IOException {
        Configuration cfg = getConfig();
        Template temp = cfg.getTemplate(tempFile);
        return temp;
    }

    protected Configuration getConfig() throws IOException {
        Configuration cfg = new Configuration();

        // Specify the data source where the template files come from. Here I set a
        // plain directory for it, but non-file-system are possible too:
        cfg.setDirectoryForTemplateLoading(new File("D:\\fastwork\\projects\\myproject\\ftldemo\\src\\main\\resources\\templates"));

        // Specify how templates will see the data-model. This is an advanced topic...
        // for now just use this:
        cfg.setObjectWrapper(new DefaultObjectWrapper());

        // Set your preferred charset template files are stored in. UTF-8 is
        // a good choice in most applications:
        cfg.setDefaultEncoding("UTF-8");

        // Sets how errors will appear. Here we assume we are developing HTML pages.
        // For production systems TemplateExceptionHandler.RETHROW_HANDLER is better.
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);

        // At least in new projects, specify that you want the fixes that aren't
        // 100% backward compatible too (these are very low-risk changes as far as the
        // 1st and 2nd version number remains):
        cfg.setIncompatibleImprovements(new Version(2, 3, 20)); // FreeMarker 2.3.20\
        return cfg;
    }
}
