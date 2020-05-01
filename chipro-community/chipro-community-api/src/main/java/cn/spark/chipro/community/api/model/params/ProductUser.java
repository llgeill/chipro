package cn.spark.chipro.community.api.model.params;

import lombok.Data;

@Data
public class ProductUser {
    private String productId;
    private String userId;
    private String comment;
}
