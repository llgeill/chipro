package cn.spark.chipro.community.api.model.params;

import cn.hutool.system.UserInfo;
import lombok.Data;



@Data
public class ProductUser {
    private String productionId;
    private String userId;
    private String userName;
    private String userNickName;
    private String comment;
    private String date;
}
