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

	
	@Test
	public void testOtherTitle() throws Exception {

		
		Map root = new HashMap();
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		String[] ids = new String[] { "pz","purNo","checkDate","checkman","checkinBu","checkType","checkBu","checkStandard","checkReportNo","manDateOrBatchNo","remark" };
		String[] txts = new String[] { "103凭证","采购订单","检验日期","检验人","收货单位","检验类型","检验单位","质检标准","检验报告编号","生产日期或批次","备注"};
		for (int i = 0; i < txts.length; i++) {
			String txt = txts[i];
			String id = ids[i];

			Map<String, String> m = new HashMap<String, String>();
			m.put("id", id);
			m.put("text", txt);
			String style = getStyle(txt.length());
			m.put("style",style);
			list.add(m);

		}
		root.put("list", list);
		processTemp("android.ftl", root);

	}
	
	
	@Test
	public void testPeisongDetailTitle() throws Exception {
		
		
		Map root = new HashMap();
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		String[] txts = new String[] { "项目","物料编码","描述","需求数量","单位","库存数量","库存地点","是否发货","特殊库存","批次","批次数量","寄售供应商","供应商描述","四号定位","发货数量","最后发货","自动确定批","扫描条码","预留号","项目","移动类型","预留已发货","预留未清"};

		for (int i = 0; i < txts.length; i++) {
			String txt = txts[i];
			Map<String, String> m = new HashMap<String, String>();
			m.put("text", txt);
			String style = getStyle(txt.length());
			m.put("style",style);
			list.add(m);

		}
		root.put("list", list);
		processTemp("android.ftl", root);

	}

	private String getStyle(int len) {
		String width = "@style/GridCell%sWord";
		width = String.format(width, len);
		return width;
	}
	
	
	@Test
	public void testPeisongDetailItem() throws Exception {
		
		
		Map root = new HashMap();
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		String[] ids = new String[] { "itemNo","mCode","descr","qty","unit","storeQty","storeLoc","hasSend","speStore","batch","batchQty","supplier","supplierDesc","fourPos","sendQty","lastSend","keepNo","keepItemNo","moveType","keepSentQty","keepUnclearQty"};
		String[] txts = new String[] { "项目","物料编码","描述","需求数量","单位","库存数量","库存地点","是否发货","特殊库存","批次","批次数量","寄售供应商","供应商描述","四号定位","发货数量","最后发货","自动确定批","扫描条码","预留号","项目","移动类型","预留已发货","预留未清"};

		for (int i = 0; i < ids.length; i++) {
			String id = ids[i];
			Map<String, String> m = new HashMap<String, String>();
			m.put("id", id);
			String style = getStyle(txts[i].length());
			m.put("style",style);
			list.add(m);

		}
		root.put("list", list);
		processTemp("androidItemRow.ftl", root);

	}
	
	@Test
	public void testHeadForm() throws Exception {
		Map root = new HashMap();
		List ms = new ArrayList();
		root.put("list", ms);

		Field[] fs = cn.sp.ftldemo.Delivery.class.getDeclaredFields();
		for (int i = 0; i < fs.length; i++) {
			Field field = fs[i];
			Map m = new HashMap();
			m.put("fieldName", field.getName());
			ms.add(m);
		}

		processTemp("androidHeadForm.ftl", root);
	}
	
	
	
	@Test
	public void testQueryJava() throws Exception {
		
		
		Map root = new HashMap();
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		String[] ids = new String[] { "submitDate","mPz","itemNo","chk","mCode","desc","kno","moveType","dc","specStore","flush","batch","qty","unit","supplier","supplierDesc","amount","other"};
		String[] txts = new String[] { "过账日期","物料凭证","项目","选择","物料编码","描述","库位","移动类型","借贷","特殊库存","冲销","批次","数量","单位","供应商","供应商描述","金额","其他"};

		for (int i = 0; i < ids.length; i++) {
			String id = ids[i];
			Map<String, String> m = new HashMap<String, String>();
			m.put("id", id);
			m.put("text",txts[i]);
			list.add(m);
		}
		root.put("list", list);
		processTemp("androidJava.ftl", root);

	}
	
	@Test
	public void testQueryTitle() throws Exception {
		
		
		Map root = new HashMap();
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		String[] txts = new String[] { "过账日期","物料凭证","项目","物料编码","描述","库位","移动类型","借贷","特殊库存","冲销","批次","数量","单位","供应商","供应商描述","金额","其他信息"};

		for (int i = 0; i < txts.length; i++) {
			Map<String, String> m = new HashMap<String, String>();
			m.put("text",txts[i]);
			String style = getStyle(txts[i].length());
			m.put("style",style);
			list.add(m);
		}
		root.put("list", list);
		processTemp("android.ftl", root);

	}
	
	
	@Test
	public void testQueryItem() throws Exception {
		
		
		Map root = new HashMap();
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		String[] ids = new String[] { "submitDate","mPz","itemNo","mCode","desc","kno","moveType","dc","specStore","flush","batch","qty","unit","supplier","supplierDesc","amount"};
		String[] txts = new String[] { "过账日期","物料凭证","项目","物料编码","描述","库位","移动类型","借贷","特殊库存","冲销","批次","数量","单位","供应商","供应商描述","金额"};

		for (int i = 0; i < ids.length; i++) {
			String id = ids[i];
			Map<String, String> m = new HashMap<String, String>();
			m.put("id", id);
			String style = getStyle(txts[i].length());
			m.put("style",style);
			list.add(m);

		}
		root.put("list", list);
		processTemp("androidItemRow.ftl", root);

	}
	
	
	@Test
	public void testNodejsModel() throws Exception {
		
		
		Map root = new HashMap();
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		String[] txts = new String[] { "count","created","time","device","version","system","battery","rooted","ram","disk","ver","caught","network","exception","osType"};

		for (int i = 0; i < txts.length; i++) {
			Map<String, String> m = new HashMap<String, String>();
			m.put("text",txts[i]);
			list.add(m);
		}
		root.put("list", list);
		processTemp("nodejsModel.ftl", root);

	}
	
	@Test
	public void testNgjsEditForm() throws Exception {
		
		
		Map root = new HashMap();
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		String model="good";
		String[] txts = new String[] { "count","created","time","device","version","system","battery","rooted","ram","disk","ver","caught","network","exception","osType"};
		String[] fieldDesc = new String[] { "count","created","time","device","version","system","battery","rooted","ram","disk","ver","caught","network","exception","osType"};
		for (int i = 0; i < txts.length; i++) {
			Map<String, String> m = new HashMap<String, String>();
			m.put("field",txts[i]);
			m.put("fieldDesc",fieldDesc[i]);
			m.put("model",model);
			list.add(m);
		}
		root.put("list", list);
		processTemp("ngjsEditForm.ftl", root);

	}



}
