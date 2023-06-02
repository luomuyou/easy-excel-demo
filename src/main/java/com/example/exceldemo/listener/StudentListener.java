package com.example.exceldemo.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.example.exceldemo.domain.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentListener extends AnalysisEventListener<Student> {

    private List<Student> list = new ArrayList<>();

    /*
    * 每读取一行数据会调用的内容
    *
    * */
    @Override
    public void invoke(Student student, AnalysisContext analysisContext) {
        System.out.println(student);
        list.add(student);
    }

    /*
    * 读取完整个文档后会调用的内容
    * */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        // System.out.println(analysisContext);
        System.out.println("doAfterAllAnalysed读取完整个文档后会调用的内容");
        System.out.println(list);
        // 可以对list最后做一个批量的操作,比如批量插入数据库
    }
}
