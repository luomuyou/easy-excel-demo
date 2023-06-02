package com.example.exceldemo.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.example.exceldemo.domain.Student;
import com.example.exceldemo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("prototype")
public class WebStudentListener extends AnalysisEventListener<Student> {
    @Autowired
    private StudentService studentService;
   List<Student> students= new ArrayList<Student>();


    @Override
    public void invoke(Student student, AnalysisContext analysisContext) {
        students.add(student);
       // 五条数据记录为一组
        if (students.size()%5==0) {
            //给到studentService操作读到的一组数据记录
            studentService.readExcel(students);
            // students.clear();  //清空List集合
        }
        // System.out.println(student);
    }

    /*
     * 读取完整个文档后会调用的内容
     * */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        System.out.println(analysisContext);
        // studentService.insertBatch(students);
        System.out.println("doAfterAllAnalysed读取完整个文档后会调用的内容");
    }
}
