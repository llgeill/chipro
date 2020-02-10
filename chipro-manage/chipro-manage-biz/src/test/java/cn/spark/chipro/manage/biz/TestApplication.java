package cn.spark.chipro.manage.biz;

import ch.qos.logback.core.util.FileUtil;
import cn.spark.chipro.core.result.Result;
import cn.spark.chipro.core.util.ExcelUtil;
import cn.spark.chipro.oss.api.feign.UserFeignService;
import cn.spark.chipro.oss.api.model.params.UserParam;
import org.apache.commons.lang.time.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestApplication {

//    @Autowired
//    UserFeignService userFeignService;
//
//    @Test
//    public void test(){
//        UserParam userParam = new UserParam();
//        userParam.setEmail("427002055@qq.com");
//        userParam.setEmailCode("164110");
//        Result<UserParam> result =userFeignService.register(userParam);
//        System.out.println(result);
//    }

    @Test
    public void importExcel(){
        String filePath = "/Users/liliguang/Desktop/批量创建校园帐号模板.xls";
        //解析excel，
        List<Person> personList = ExcelUtil.importExcel(filePath,7,1,Person.class);
        //也可以使用MultipartFile,使用 FileUtil.importExcel(MultipartFile file, Integer titleRows, Integer headerRows, Class<T> pojoClass)导入
        System.out.println("导入数据一共【"+personList.size()+"】行");

        //TODO 保存数据库
    }

//    @Test
//    public void export(HttpServletResponse response){
//
//        //模拟从数据库获取需要导出的数据
//        List<Person> personList = new ArrayList<>();
//        Person person1 = new Person("路飞","1",new Date());
//        Person person2 = new Person("娜美","2", new Date());
//        Person person3 = new Person("索隆","1", new Date());
//        Person person4 = new Person("小狸猫","1", new Date());
//        personList.add(person1);
//        personList.add(person2);
//        personList.add(person3);
//        personList.add(person4);
//
//        //导出操作
//        ExcelUtil.exportExcel(personList,"花名册","草帽一伙",Person.class,"海贼王.xls",response);
//    }


}
