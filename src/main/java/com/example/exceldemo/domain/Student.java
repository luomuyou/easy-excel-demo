package com.example.exceldemo.domain;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.example.exceldemo.convert.GenderConverter;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
//@HeadRowHeight(20)//表头高度
//@ContentRowHeight(20)//内容的行高
public class Student {
	//和excel表中数据一一对应
//    @ExcelIgnore//该字段不参与读写
	private String id;

	//    @ExcelProperty("姓名")//列名
	@ColumnWidth(20)//列宽
	private String name;

	//GenderConverter性别转换器,excel中的男和女,读取到java值变为1和0,写入则相反
	// @ExcelProperty(value = "性别",converter = GenderConverter.class)
//    @ColumnWidth(20)
	private String gender;

	//    @ExcelProperty("出生日期")
//    @ColumnWidth(20)
	private Date birthday;

	/*
	 * 写入时传入Date日期格式，读取时候用String日期格式化好一点
	 * @DateTimeFormat指定日期格式,针对String字符串区接收时间类型的格式转换
	 * */
	// @ExcelProperty("stringBirthday")
	// @DateTimeFormat("yyyy-MM-dd")
	// private String birthday;
}
