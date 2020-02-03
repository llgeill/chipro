package cn.spark.chipro.oss.biz;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestApplication {

    @Test
    public void date(){
        getDateAfterYear();
    }


    private Date  getDateAfterYear(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR,1);
        Date time = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");

        System.out.println(sdf.format(time));
        return  time;
    }
}
