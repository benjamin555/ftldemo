package cn.sp.xml;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.Test;

import java.io.FileWriter;
import java.util.List;

public class DOM4jTest {

    @Test
    public void testModify() throws Exception{
        SAXReader sr = new SAXReader();
        Document document = sr.read("src\\test\\resources\\tmp.xml");
        Element root = document.getRootElement();
        List maps = root.elements();
        for (int i = 0; i < maps.size(); i++) {

            Element map = (Element) maps.get(i);
            Attribute col = map.attribute("col");
            String value = col.getValue();
            int newValue = Integer.parseInt(value)-1;
            col.setValue(newValue+"");
        }

        try {
            writeToNewXMLDocument(document);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * 通过document对象将内存中的dom树保存到新的xml文档。
     *
     * @param document
     * @throws Exception
     */
    private static void writeToNewXMLDocument(Document document)
            throws Exception {

        XMLWriter writer = new XMLWriter(new FileWriter(
                "src/test/resources/tmpNew.xml"));
        writer.write(document);
        writer.close();
    }
}
