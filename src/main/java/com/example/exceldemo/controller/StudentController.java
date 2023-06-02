package com.example.exceldemo.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.builder.ExcelWriterSheetBuilder;
import com.example.exceldemo.domain.Student;
import com.example.exceldemo.listener.WebStudentListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("excelTest")
public class StudentController {

	@Autowired
	WebStudentListener webStudentListener;

	@PostMapping("read")
	@ResponseBody
//    文件上传
	public String readExcel(@RequestParam("file")MultipartFile uploadExcel) {
		try {
//            工作薄
			ExcelReaderBuilder readBookWork = EasyExcel.read(uploadExcel.getInputStream(), Student.class,
					webStudentListener);
//            工作表sheet
			readBookWork.sheet().doRead();
			return "成功";
		} catch (IOException e) {
			e.printStackTrace();
			return "失败";
		}
	}

	//    文件导出，访问直接下载文件
	@GetMapping("write")
	@ResponseBody
	public void writeExcel(HttpServletResponse response) throws Exception {
		/*相关响应体设置*/
		response.setContentType("application/vnd.ms-excel");
		response.setCharacterEncoding("utf-8");
		// 防止中文乱码
		String fileName = URLEncoder.encode("测试", "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename*=UTF-8''" + fileName + ".xlsx");

		//获得文件流
		ServletOutputStream outputStream = response.getOutputStream();
		//之前一样
		ExcelWriterBuilder writeWorkBook = EasyExcel.write(outputStream, Student.class);
		ExcelWriterSheetBuilder sheet = writeWorkBook.sheet();
		sheet.doWrite(initData());

	}

	private static List<Student> initData() {
		List<Student> students = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			Student data = new Student();
			data.setName("测试" + i + 2);
			data.setGender("男");
			// data.setBirthday(new Date());
			students.add(data);
		}
		System.out.println(students);
		return students;
	}
}
