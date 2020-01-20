package cn.spark.chipro.core.cloud.config;



import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;



import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@RestController
@Slf4j
public class AutoRefreshEndPoint {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/auto/refresh")
    public void refresh(@RequestBody HashMap map, HttpServletRequest request) {
        String[] strings = request.getRequestURL().toString().split("/auto/refresh");
        String url = strings[0]+"/actuator/bus-refresh";
        log.info("---------------bus-refresh url: {} ------------------- ",url);
        restTemplate.postForLocation(url,new HashMap<>());
    }
}
