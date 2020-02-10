package cn.spark.chipro.manage.biz;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

@Data
public class Person {

    @Excel(name = "* 学生姓名", orderNum = "0")
    private String name;

    private Integer age;

}