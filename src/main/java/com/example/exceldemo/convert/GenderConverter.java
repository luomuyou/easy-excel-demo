package com.example.exceldemo.convert;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.property.ExcelContentProperty;

/**
 * @Author：lmy
 * @Date：2023/06/01 18:12
 * @Version：1.0
 * @Description：性别转换器,
 */
public class GenderConverter implements Converter<String> {
	@Override
	public Class supportJavaTypeKey() {
		return Integer.class;
	}

	@Override
	public CellDataTypeEnum supportExcelTypeKey() {
		return CellDataTypeEnum.STRING;
	}

	/*
	 * excel表格中的性别值转换为Java类型值
	 * */
	@Override
	public String convertToJavaData(CellData cellData, ExcelContentProperty contentProperty,
									GlobalConfiguration globalConfiguration) throws Exception {
		String result = "-1";
		String ganderValue = cellData.getStringValue();
		if ("男".equals(ganderValue)) {
			result = "1";
		} else if ("女".equals(ganderValue)) {
			result = "0";
		} else {

		}
		return result;
	}

	/*
	 * Java类型值转换为excel表中的性别值
	 * */
	@Override
	public CellData convertToExcelData(String value, ExcelContentProperty contentProperty,
									   GlobalConfiguration globalConfiguration) throws Exception {
		CellData cellData = null;
		if ("0".equals(value)) {
			cellData = new CellData("女");
		} else if ("1".equals(value)) {
			cellData = new CellData("男");
		} else {
			cellData = new CellData("未知");
		}

		return cellData;
	}
}

