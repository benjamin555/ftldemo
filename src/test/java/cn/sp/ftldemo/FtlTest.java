package cn.sp.ftldemo;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.sp.net.domain.rule.redmine.Contract;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;

/**
* @author 陈嘉镇
* @version 创建时间：2014-9-5 下午5:36:24
* @email benjaminchen555@gmail.com
*/
@SuppressWarnings({ "rawtypes", "unchecked" })
public class FtlTest {

	@Test
	public void testPrint() throws Exception {

		// Create the root hash
		Map root = new HashMap();
		// Put string ``user'' into the root
		root.put("user", "Big Joe");
		List animals = new ArrayList();
		root.put("animals", animals);
		// Create the hash for ``latestProduct''
		Map animal = new HashMap();
		// and put it into the root

		animal.put("price", "products/greenmouse.html");
		animal.put("name", "green mouse");
		animals.add(animal);

		String tempFile = "test.ftl";
		processTemp(tempFile, root);

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
		cfg.setDirectoryForTemplateLoading(new File("G:\\myproject\\ftldemo\\src\\main\\resources\\templates"));

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

	@Test
	public void testMap() throws Exception {
		Map root = new HashMap();
		List ms = new ArrayList();
		root.put("ms", ms);
		Field[] fs = Contract.class.getDeclaredFields();
		root.put("clazz", Contract.class.getName());

		for (int i = 0; i < fs.length; i++) {
			Field field = fs[i];
			Map m = new HashMap();
			m.put("col", i);
			m.put("field", field.getName());
			ms.add(m);
		}

		processTemp("map.ftl", root);

	}

	@Test
	public void testTempCustomerMap() throws Exception {
		Map root = new HashMap();
		List ms = new ArrayList();
		root.put("ms", ms);

		Field[] fs = com.sp.net.domain.rule.redmine.TempCustomer.class.getDeclaredFields();
		root.put("clazz", com.sp.net.domain.rule.redmine.TempCustomer.class.getName());
		for (int i = 0; i < fs.length; i++) {
			Field field = fs[i];
			Map m = new HashMap();
			m.put("col", i);
			m.put("field", field.getName());
			ms.add(m);
		}

		processTemp("map.ftl", root);

	}

	@Test
	public void testUnion() throws Exception {
		Map root = new HashMap();
		root.put("count", 7);
		processTemp("union.ftl", root);
	}

	@Test
	public void testAndroid() throws Exception {
		
		
//		@Column(position=0)
//		private String llNo;
//		@Column(position=1)
//		private String reqDate;
//		@Column(position=2)
//		private String submitBuName;
//		@Column(position=3)
//		private String createDate;
//		@Column(position=4)
//		private String linkman;
//		@Column(position=5)
//		private String linkTel;
//		
//		@Column(position=6)
//		private String deliveryLoc;
//		@Column(position=7)
//		private String remark;
		Map root = new HashMap();
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		String[] ids = new String[] { "llNo","reqDate","submitBuName","createDate","linkman","linkTel","deliveryLoc","remark" };
		String[] txts = new String[] { "领料单号","请求交货日期","提交单位名称","创建日期","联系人","联系电话","配送地点","备注" };
		for (int i = 0; i < txts.length; i++) {
			String txt = txts[i];
			String id = ids[i];

			Map<String, String> m = new HashMap<String, String>();
			m.put("id", id);
			m.put("text", txt);

			list.add(m);

		}
		root.put("list", list);
		processTemp("android.ftl", root);

	}
	
	
	
	
	@Test
	public void testPeisong() throws Exception {
		
		
		Map root = new HashMap();
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		String[] ids = new String[] { "llNo","reqDate","submitBuName","createDate","linkman","linkTel","deliveryLoc","remark" };
		for (int i = 0; i < ids.length; i++) {
			String id = ids[i];
			Map<String, String> m = new HashMap<String, String>();
			m.put("id", id);

			list.add(m);

		}
		root.put("list", list);
		processTemp("androidItemRow.ftl", root);

	}


}
