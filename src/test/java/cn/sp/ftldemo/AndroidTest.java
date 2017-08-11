package cn.sp.ftldemo;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
public class AndroidTest {

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
	public void testQueryJava() throws Exception {
		Map root = new HashMap();
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		String[] ids = new String[] { "inputTime", "recorder", "workOrder", "costCenter", "purOrder", "net", "flushPz",
				"evaluateType" };
		String[] txts = new String[] { "输入时间", "记账人", "工单", "成本中心", "采购订单", "网络", "冲销对应凭证", "评估类型" };

		for (int i = 0; i < ids.length; i++) {
			String id = ids[i];
			Map<String, String> m = new HashMap<String, String>();
			m.put("id", id);
			m.put("text", txts[i]);
			String style = getStyle(txts[i].length());
			m.put("style", style);
			list.add(m);
		}
		root.put("list", list);
		processTemp("androidJava.ftl", root);
		System.out.println("----------------");
		processTemp("android.ftl", root);
		System.out.println("----------------");
		processTemp("androidItemRow.ftl", root);

	}

	private String getStyle(int len) {
		String width = "@style/GridCell%sWord";
		width = String.format(width, len);
		return width;
	}

	@Test
	public void testQueryStore() throws Exception {
		Map root = new HashMap();
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		String[] ids = new String[] { "mCode", "desc", "storeLoc", "chk", "specStore", "supplier", "supplierDesc",
				"evaluateType", "batch", "fourPos", "qty", "unit", "amount" };
		String[] txts = new String[] { "物料编码", "物料描述", "库存地点", "选择", "特殊库存", "供应商", "供应商描述", "评估类型", "批次", "四号定位",
				"数量", "计量单位", "金额" };

		for (int i = 0; i < ids.length; i++) {
			String id = ids[i];
			Map<String, String> m = new HashMap<String, String>();
			m.put("id", id);
			m.put("text", txts[i]);
			String style = getStyle(txts[i].length());
			m.put("style", style);
			list.add(m);
		}
		root.put("list", list);
		processTemp("androidJava.ftl", root);
		System.out.println("----------------");
		processTemp("android.ftl", root);
		System.out.println("----------------");
		processTemp("androidItemRow.ftl", root);

	}

	@Test
	public void testQueryScan() throws Exception {
		Map root = new HashMap();
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		String[] ids = new String[] { "mCode", "desc", "storeLoc", "fourPos", "batch", "qty", "unit", "amount",
				"evaluateType", "specStore", "purOrderItem", "supplier", "supplierDesc", "mPurRemark", "mLocalRemark" };
		String[] txts = new String[] { "物料编码", "物料描述", "库存地点", "四号定位", "批次", "库存数量", "计量单位", "金额", "评估类型", "特殊库存",
				"采购订单/项目", "供应商", "供应商描述", "物料采购备注", "物料本地备注" };

		for (int i = 0; i < ids.length; i++) {
			String id = ids[i];
			Map<String, String> m = new HashMap<String, String>();
			m.put("id", id);
			m.put("text", txts[i]);
			String style = getStyle(txts[i].length());
			m.put("style", style);
			list.add(m);
		}
		root.put("list", list);
		processTemp("androidJava.ftl", root);
		System.out.println("----------------");
		processTemp("android.ftl", root);
		System.out.println("----------------");
		processTemp("androidItemRow.ftl", root);

	}

	@Test
	public void testSet() throws Exception {
		String[] tFields = { "checkNo", "lineNo", "publishDate", "mCode", "mDesc", "result", "qty", "passQty", "unit",
				"storeLoc" };
		String[] sFields = { "INSPNR", "INSPLINE", "FGDAT", "MATNR", "MAKTX", "ZQSTAT", "ZQSTATD", "ERFMG", "ZHERFMG",
				"MEINSO", "LGORT" };
		Map root = new HashMap();
		root.put("tFields", tFields);
		root.put("sFields", sFields);
		processTemp("modelSetMap.ftl", root);

	}

	@Test
	public void testSetOther() throws Exception {
		String[] tFields = { "pz", "purNo", "checkDate", "checkman", "checkinBu", "checkType", "checkBu",
				"checkStandard", "checkReportNo", "manDateOrBatchNo", "remark" };
		String[] sFields = { "MBLNR", "EBELN", "ZPDAT", "ZPUSER", "ZLGORTNR", "ZINPT", "ZJCDWNR", "ZINPS", "ZZJBGBH",
				"ZRQOP", "REMARK" };
		Map root = new HashMap();
		root.put("tFields", tFields);
		root.put("sFields", sFields);
		processTemp("modelSetMap.ftl", root);

	}

	@Test
	public void testSetRecevie() throws Exception {
		String[] tFields = { "ZMM_GRNOTE", "AEDAT", "ERNAM", "EBELN", "ERDAT", "LIFNR", "NAME1", "EKGRP", "EKORG" };
		String[] sFields = { "no", "notifyDate", "notifyer", "purNo", "formType", "supplierNo", "supplierDesc",
				"purGroup", "purGroupDesc" };
		Map root = new HashMap();
		root.put("tFields", tFields);
		root.put("sFields", sFields);
		processTemp("modelSetMap.ftl", root);
	}

	@Test
	public void testSetRecevieItem() throws Exception {
		String[] tFields = { "POSNR", "MATNR", "MAKTX", "ZMM_GR_NOTE_MENGE", "AEDAT", "LGPBE", "LGPBE", "DISPATCH",
				"DISPATCH", "DISPATCH" };
		String[] sFields = { "itemNo", "mCode", "descr", "qty", "arrivalDa", "kno", "speStore", "mustCheckfourPos",
				"hasStore", "hasDevli" };
		Map root = new HashMap();
		root.put("tFields", tFields);
		root.put("sFields", sFields);
		processTemp("modelSetMap.ftl", root);
	}

	@Test
	public void testWs() throws Exception {
		String[] sFields = { "TYPE", "ID", "NUMBER", "MESSAGE", "LOG_NO", "LOG_MSG_NO", "MESSAGE_V1", "MESSAGE_V2",
				"MESSAGE_V3", "MESSAGE_V4", "PARAMETER", "ROW", "FIELD", "SYSTEM" };
		Map root = new HashMap();
		root.put("sFields", sFields);
		processTemp("ws.ftl", root);
	}

	@Test
	public void testSetCheckItem() throws Exception {
		String[] tFields = { "INSPNR", "INSPLINE", "FGDAT", "MATNR", "MAKTX", "ZQSTATD", "ERFMG", "ZHERFMG", "MEINSO",
				"LGORT", "MBLNR", "ZPDAT", "ZLGORTNR", "ZJCDWNR", "ZZJBGBH", "ZRQOP", "REMARK", "EBELN", "ZPUSER",
				"ZINPT", "ZINPS" };
		String[] sFields = { "checkNo", "lineNo", "publishDate", "mCode", "mDesc", "result", "qty", "passQty", "unit",
				"storeLoc", "pz", "checkDate", "checkinBu", "checkBu", "checkReportNo", "manDateOrBatchNo", "remark",
				"purNo", "checkman", "checkType", "checkStandard" };
		Map root = new HashMap();
		root.put("tFields", tFields);
		root.put("sFields", sFields);
		processTemp("modelSetMap.ftl", root);
	}

	@Test
	public void testSetCheckItemForQuery() throws Exception {
		String[] sFields = { "INSPNR", "INSPLINE", "FGDAT", "MATNR", "MAKTX", "ZQSTATD", "ERFMG", "ZHERFMG", "MEINSO",
				"LGORT", "MBLNR", "ZPDAT", "ZLGORTNR", "ZJCDWNR", "ZZJBGBH", "ZRQOP", "REMARK", "EBELN", "ZPUSER",
				"ZINPT", "ZINPS" };
		String[] tFields = { "checkNo", "lineNo", "publishDate", "mCode", "mDesc", "result", "qty", "passQty", "unit",
				"storeLoc", "pz", "checkDate", "checkinBu", "checkBu", "checkReportNo", "manDateOrBatchNo", "remark",
				"purNo", "checkman", "checkType", "checkStandard" };
		Map root = new HashMap();
		root.put("tFields", tFields);
		root.put("sFields", sFields);
		processTemp("modelSetMap.ftl", root);
	}

	@Test
	public void testSetDeliveryForQuery() throws Exception {
		String[] tFields = { "ZLLSQNUM", "ZJHDATE", "ZZMROTBTXT", "ERDAT", "ZLXNAME", "ZLXTEL", "ZLLADDTXT", "ZMEMO" };
		String[] sFields = { "llNo", "reqDate", "submitBuName", "createDate", "linkman", "linkTel", "deliveryLoc",
				"remark" };
		Map root = new HashMap();
		root.put("tFields", tFields);
		root.put("sFields", sFields);
		processTemp("modelSetMap.ftl", root);
	}

	@Test
	public void testSetDeliveryItemForQuery() throws Exception {
		String[] tFields = { "ZLLSQPOS", "MATNR", "MAKTX", "ZBCSQMNG", "MEINS", "LABST", "LGORT", "WEPOS", "SOBKZ",
				"CHARG", "CLABS", "LIFNR", "NAME1", "LGPBE", "ERFMG", "KZEAR", "RSNUM", "RSPOS", "BWART", "ENMNG",
				"OBMNG" };
		String[] sFields = { "itemNo", "mCode", "descr", "qty", "unit", "storeQty", "storeLoc",
				"privatebooleanhasSend", "speStore", "batch", "batchQty", "supplier", "supplierDesc", "fourPos",
				"sendQty", "lastSend", "keepNo", "keepItemNo", "moveType", "keepSentQty", "keepUnclearQty" };
		Map root = new HashMap();
		root.put("tFields", tFields);
		root.put("sFields", sFields);
		processTemp("modelSetMap.ftl", root);
	}

	@Test
	public void testSetQueryItemForQuery() throws Exception {
		String[] sFields = { "BUDAT", "MBLNR", "ZEILE", "MATNR", "MAKTX", "LGORT", "BWART", "SHKZG", "SOBKZ", "STORN",
				"CHARG", "MENGE", "MEINS", "LIFNR", "NAME1", "DMBTR", "CPUTM", "USNAM", "AUFNR", "KOSTL", "EBELN",
				"NPLNR", "SMBLN", "BWTAR" };
		String[] tFields = { "submitDate", "mPz", "itemNo", "mCode", "desc", "kno", "moveType", "dc", "specStore",
				"flush", "batch", "qty", "unit", "supplier", "supplierDesc", "amount", "inputTime", "recorder",
				"workOrder", "costCenter", "purOrder", "net", "flushPz", "evaluateType" };
		Map root = new HashMap();
		root.put("tFields", tFields);
		root.put("sFields", sFields);
		processTemp("modelSetMap.ftl", root);
	}

	@Test
	public void testSetQueryItemForSubmit() throws Exception {
		String[] sFields = { "submitDate", "mPz", "itemNo", "mCode", "desc", "kno", "moveType", "dc", "specStore",
				"flush", "batch", "qty", "unit", "supplier", "supplierDesc", "amount", "inputTime", "recorder",
				"workOrder", "costCenter", "purOrder", "net", "flushPz", "evaluateType" };
		String[] tFields = { "BUDAT", "MBLNR", "ZEILE", "MATNR", "MAKTX", "LGORT", "BWART", "SHKZG", "SOBKZ", "STORN",
				"CHARG", "MENGE", "MEINS", "LIFNR", "NAME1", "DMBTR", "CPUTM", "USNAM", "AUFNR", "KOSTL", "EBELN",
				"NPLNR", "SMBLN", "BWTAR" };
		Map root = new HashMap();
		root.put("tFields", tFields);
		root.put("sFields", sFields);
		processTemp("modelSetMap.ftl", root);
	}

	@Test
	public void testSetStoreItemForQuery() throws Exception {
		String[] sFields = { "MATNR", "MAKTX", "LGORT", "SOBKZ", "IFNR?", "NAME1", "BWTAR", "CHARG", "LGPBE", "MENGE",
				"MEINS", "VERPR" };
		String[] tFields = { "mCode", "desc", "storeLoc", "specStore", "supplier", "supplierDesc", "evaluateType",
				"batch", "fourPos", "qty", "unit", "amount" };
		Map root = new HashMap();
		root.put("tFields", tFields);
		root.put("sFields", sFields);
		processTemp("modelSetMap.ftl", root);
	}

	@Test
	public void testSetScanItemForQuery() throws Exception {
		String[] sFields = { "LIFNR", "EBELP", "LGORT_D", "MATNR", "MAKTX", "MSEHL", "ERFMG" };
		String[] tFields = { "supplier", "purOrderItem", "storeLoc", "mCode", "desc", "unit", "qty" };
		Map root = new HashMap();
		root.put("tFields", tFields);
		root.put("sFields", sFields);
		processTemp("modelSetMap.ftl", root);
	}

	public static void main(String[] args) {
		System.out.println(UUID.randomUUID().toString().length());
	}

	@Test
	public void testDeliveryKeep() throws Exception {
		Map root = new HashMap();
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		String[] ids = new String[] { "preKeepNo", "itemNo", "moveType", "mCode", "mDesc", "reqQty", "sentQty",
				"remainQty", "unit" };
		String[] txts = new String[] { "预留号", "项目", "移动类型", "物料编码", "描述", "需求数量", "已发货数量", "待发货数量", "单位" };

		for (int i = 0; i < ids.length; i++) {
			String id = ids[i];
			Map<String, String> m = new HashMap<String, String>();
			m.put("id", id);
			m.put("text", txts[i]);
			String style = getStyle(txts[i].length());
			m.put("style", style);
			list.add(m);
		}
		root.put("list", list);
		processTemp("androidJava.ftl", root);
		System.out.println("----------------");
		processTemp("android.ftl", root);
		System.out.println("----------------");
		processTemp("androidItemRow.ftl", root);

	}
	
	
	@Test
	public void testDeliveryKeepStore() throws Exception {
		Map root = new HashMap();
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		String[] ids = new String[] { "typ","storeLoc","qty","supplier","supplierDesc"};
		String[] txts = new String[] { "类型	","库存地点","数量","寄售供应商","供应商描述"};
		
		for (int i = 0; i < ids.length; i++) {
			String id = ids[i];
			Map<String, String> m = new HashMap<String, String>();
			m.put("id", id);
			m.put("text",txts[i]);
			String style = getStyle(txts[i].length());
			m.put("style",style);
			list.add(m);
		}
		root.put("list", list);
		processTemp("androidJava.ftl", root);
		System.out.println("----------------");
		processTemp("android.ftl", root);
		System.out.println("----------------");
		processTemp("androidItemRow.ftl", root);
		
	}
	
	
	
	@Test
	public void testDeliveryKeep2() throws Exception {
		
		String[] ids = new String[] { "chk", "mCode", "mDesc", "unit", "reqQty", "sendQty", "storeLoc",
				"autoBtn", "scanBtn","batch","batchQty","speStore","sentQty","keepUnclearQty","supplier","applyBu","acceptor","keepNo","keepItemNo","orderNetNo","moveType" };
		String[] txts = new String[] { "选择","物料编码","描述","单位","需求数量","本次发货数量","库存地点","自动批次确定","扫描条码","批次","批次数量","特殊库存","已发货数量","未清数量","寄售供应商","领用单位","接收方","预留号","项目","工单/网络号","移动类型"};
		process(ids, txts);

	}

	private void process(String[] ids, String[] txts) throws IOException, TemplateException {
		Map root = new HashMap();
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		for (int i = 0; i < ids.length; i++) {
			String id = ids[i];
			Map<String, String> m = new HashMap<String, String>();
			m.put("id", id);
			m.put("text", txts[i]);
			String style = getStyle(txts[i].length());
			m.put("style", style);
			list.add(m);
		}
		root.put("list", list);
		processTemp("androidJava.ftl", root);
		System.out.println("----------------");
		processTemp("android.ftl", root);
		System.out.println("----------------");
		processTemp("androidItemRow.ftl", root);
		
		processTemp("androidJavaById.ftl", root);
	}
	
	
	@Test
	public void testSetDeliveryKeepForQuery() throws Exception {
		String[] sFields = { "RSNUM","RSPOS","BWART","MATNR","MAKTX","BDMNG","ENMNG","MEINSO","LGORT","CHARG","SOBKZ","MENGE","NAME1" };
		String[] tFields = { "keepNo","keepItemNo","moveType","mCode","mDesc","reqQty","sentQty","unit","storeLoc","batch","speStore","batchQty","supplier" };
		Map root = new HashMap();
		root.put("tFields", tFields);
		root.put("sFields", sFields);
		processTemp("modelSetMap.ftl", root);
	}
	
	@Test
	public void testSetDeliveryKeepForSubmit() throws Exception {
		String[] tFields = { "RSNUM","RSPOS","BWART","MATNR","MAKTX","BDMNG","ENMNG","MEINSO","LGORT","CHARG","SOBKZ","MENGE","NAME1" };
		String[] sFields = { "keepNo","keepItemNo","moveType","mCode","mDesc","reqQty","sentQty","unit","storeLoc","batch","speStore","batchQty","supplier" };
		Map root = new HashMap();
		root.put("tFields", tFields);
		root.put("sFields", sFields);
		processTemp("modelSetMap.ftl", root);
	}
	
	
	@Test
	public void testSetDeliveryItemsForQuery() throws Exception {
		String[] sFields = {"ZPSDNUM","ZPSDPOS","MATNR","MAKTX","MEINS","MENGE","ERFMG","LGORT","ZISBD","CHARG","CLABS","SOBKZ","LIFNR","NAME1","BWART","ZTHFS","ZTBDEPT","DESCRIP","ENGEPS","OBMNG","ERDAT" };
		String[] tFields = { "no","itemNo","mCode","descr","unit","qty","qty","storeLoc","supplement","batch","batchQty","speStore","supplier","supplierDesc","moveType","sendMethod","buCode","buDesc","sentQty","unclearQty","createDate" };
		Map root = new HashMap();
		root.put("tFields", tFields);
		root.put("sFields", sFields);
		processTemp("modelSetMap.ftl", root);
	}
	
	
	@Test
	public void testSetDeliveryItemsForSubmit() throws Exception {
		String[] tFields = {"ZPSDNUM","ZPSDPOS","MATNR","MAKTX","MEINS","MENGE","ERFMG","LGORT","ZISBD","CHARG","CLABS","SOBKZ","LIFNR","NAME1","BWART","ZTHFS","ZTBDEPT","DESCRIP","ENGEPS","OBMNG","ERDAT" };
		String[] sFields = { "no","itemNo","mCode","descr","unit","qty","qty","storeLoc","supplement","batch","batchQty","speStore","supplier","supplierDesc","moveType","sendMethod","buCode","buDesc","sentQty","unclearQty","createDate" };
		Map root = new HashMap();
		root.put("tFields", tFields);
		root.put("sFields", sFields);
		processTemp("modelSetMap.ftl", root);
	}
	
	
	@Test
	public void testBatch() throws Exception {
		
		String[] ids = new String[] { "chk", "batch", "batchQty", "speStore", "supplier", "supplierDesc" };
		String[] txts = new String[] { "选择","批次","批次数量","特殊库存","供应商","供应商描述"};
		process(ids, txts);

	}
	
	
	@Test
	public void testSetValue2View() throws Exception {
		Gson gson = new GsonBuilder().create();
		String json = "{\"materielCode\":\"1000000000\",\"materielDescription\":\"20150607\",\"specialInventoryCode\":\"仓库名称\"}";
		Map map = gson.fromJson(json , Map.class);
		System.out.println(map);
		Map root = new HashMap();
		root.put("map", map);
		processTemp("setVal2View.ftl", root);
	}

}
