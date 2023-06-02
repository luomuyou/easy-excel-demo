package com.example.exceldemo;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.enums.WriteDirectionEnum;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.alibaba.excel.read.builder.ExcelReaderSheetBuilder;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.builder.ExcelWriterSheetBuilder;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.fill.FillConfig;
import com.example.exceldemo.domain.FillData;
import com.example.exceldemo.domain.Student;
import com.example.exceldemo.listener.StudentListener;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
class ExceldemoApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void test01() throws FileNotFoundException {
//        获取工作簿对象,文件流的方式获也可以通过文件路径
		FileInputStream fileInputStream = new FileInputStream("src/ExcelPattern/easyExcel.xlsx");
		ExcelReaderBuilder readWorkBook = EasyExcel.read(fileInputStream, Student.class, new StudentListener());
		// 也可以通过文件路径资源方式获取
		ExcelReaderBuilder readWorkBook2 = EasyExcel.read("src/ExcelPattern/easyExcel.xlsx", Student.class,
				new StudentListener());
//        获取工作表对象
		ExcelReaderSheetBuilder sheet = readWorkBook.sheet();
//        读取表中的内容
		sheet.doRead();
	}

	@Test
	public void test02() {
		ExcelWriterBuilder writeWorkBook = EasyExcel.write("src/ExcelPattern/easyExcel1.xlsx", Student.class);
		ExcelWriterSheetBuilder sheet = writeWorkBook.sheet();
		sheet.doWrite(initData());

	}

	//    数据生成
	private static List<Student> initData() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<Student> students = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			Student data = new Student();
			data.setName("测试" + i + 2);
			data.setGender("男");
			data.setBirthday(new Date());  //
			// data.setStringBirthday(format.format(new Date()));
			students.add(data);
		}
		System.out.println(students);
		return students;
	}

	/*
	 * 单行模版变量填充写入
	 * */
	@Test
	public void test03() {
		/*
		 * 模版中有{variable}多个变量,填充的时候可以指定值,
		 * 变量为实体类的成员属性，或者Map键值对
		 * */
		FillData fillData = new FillData();
		// fillData.setName("张三");
		// fillData.setAge(10);  //没有设置变量,默认值为0 ?
		String template = "src/ExcelPattern/fill_data_template1.xlsx";

		EasyExcel.write("Excel填充单组数据01.xlsx", FillData.class)
				.withTemplate(template).sheet().doFill(fillData);

	}

	//    test04数据准备
	private static List<FillData> initFillData() {
		ArrayList<FillData> fillDatas = new ArrayList<FillData>();
		for (int i = 0; i < 10; i++) {
			FillData fillData = new FillData();
			fillData.setName("杭州黑马0" + i);
			fillData.setAge(10 + i);
			fillDatas.add(fillData);
		}
		return fillDatas;
	}

	/*
	 * 多行
	 * */
	@Test
	public void test04() {
		List<FillData> fillDatas = initFillData();

		String template = "src/ExcelPattern/fill_data_template2.xlsx";
		ExcelWriterBuilder excelWriterBuilder =
				EasyExcel.write("Excel填充多组数据02.xlsx", FillData.class).withTemplate(template);
		ExcelWriterSheetBuilder sheet = excelWriterBuilder.sheet();
		sheet.doFill(fillDatas);

	}

	// 组合填充
	@Test
	public void test05() {
		List<FillData> fillDatas = initFillData();

		String template = "src/ExcelPattern/fill_data_template3.xlsx";
		ExcelWriter workBook = EasyExcel.write("Excel填充组合数据03.xlsx", FillData.class).withTemplate(template).build();
		WriteSheet sheet = EasyExcel.writerSheet().build();
//        换行-防止覆盖
		FillConfig fillConfig = FillConfig.builder().forceNewRow(true).build();

		HashMap<String, String> otherData = new HashMap<>();
		otherData.put("date", "2020-03-14");
		otherData.put("total", "100");

		workBook.fill(fillDatas, fillConfig, sheet);
		workBook.fill(otherData, sheet);

		workBook.finish(); //完成 关闭


	}

	@Test
	public void test06() {
		List<FillData> fillDatas = initFillData();

		String template = "src/ExcelPattern/fill_data_template4.xlsx";
		ExcelWriter workBook = EasyExcel.write("Excel填充水平数据04.xlsx", FillData.class).withTemplate(template).build();
		WriteSheet sheet = EasyExcel.writerSheet().build();
		// 水平填充，默认垂直填充
		FillConfig fillConfig = FillConfig.builder().direction(WriteDirectionEnum.HORIZONTAL).build();
		workBook.fill(fillDatas, fillConfig, sheet);
		workBook.finish();  //完成 关闭
	}

	@Test
	public void test07() {

		String template = "src/ExcelPattern/report_template.xlsx";
		ExcelWriter workBook = EasyExcel.write("Excel报表数据填充.xlsx", FillData.class).withTemplate(template).build();
		WriteSheet sheet = EasyExcel.writerSheet().build();
		// ****** 准备数据 *******
		// 日期
		HashMap<String, String> dateMap = new HashMap<String, String>();
		dateMap.put("date", "2020-03-16");


		// 新增员数
		HashMap<String, String> increaseCountMap = new HashMap<String, String>();
		dateMap.put("increaseCount", "100");

		// 总会员数
		HashMap<String, String> totalCountMap = new HashMap<String, String>();
		dateMap.put("totalCount", "1000");

		// 本周新增会员数
		HashMap<String, String> increaseCountWeekMap = new HashMap<String, String>();
		dateMap.put("increaseCountWeek", "50");

		// 本月新增会员数
		HashMap<String, String> increaseCountMonthMap = new HashMap<String, String>();
		dateMap.put("increaseCountMonth", "100");
		// 新增会员数据 10人
		List<Student> students = initData();
		// **** 准备数据结束****

		// 写入统计数据
		workBook.fill(dateMap, sheet);
		workBook.fill(totalCountMap, sheet);
		workBook.fill(increaseCountMap, sheet);
		workBook.fill(increaseCountWeekMap, sheet);
		workBook.fill(increaseCountMonthMap, sheet);
		// 写入新增会员

		workBook.fill(students, sheet);
		workBook.finish();
	}


}
