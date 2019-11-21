package cn.spark.chipro.core.log.service;


import cn.spark.chipro.core.log.entity.LogEntity;

/**
 * @author liliguang
 */
public interface LogService {

    /**
     * 写入日志
     *  该方法，由开发者实现
     * @param logEntity 日志实体
     */
    void writeLog(LogEntity logEntity);
}
