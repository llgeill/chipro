import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationTests.class)
@Slf4j
public class TestApplicationTests {

    @Value("${llg}")
    private String llg;

    @Test
    public void test(){

        log.info("test -- auto -- print"+llg);
    }


}
