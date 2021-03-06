package cn.spark.chipro.generator.modular.restful.controller;

import cn.spark.chipro.generator.core.generator.AbstractCustomGenerator;
import org.beetl.core.Template;

import java.io.File;
import java.util.Map;

/**
 * 带restful接口控制器生成器
 *
 * @author fengshuonan
 * @date 2018-12-13-2:20 PM
 */
public class RestfulControllerGenerator extends AbstractCustomGenerator {

    public RestfulControllerGenerator(Map<String, Object> tableContext) {
        super(tableContext);
    }

    @Override
    public void bindingOthers(Template template) {
        template.binding("controllerPackage", contextParam.getProPackage() + ".controller");
    }

    @Override
    public String getTemplateResourcePath() {
        return "/feignTemplates/RestfulController.btl";
    }

    @Override
    public String getGenerateFilePath() {
        String proPackage = this.contextParam.getProPackage();
        String proPath = proPackage.replaceAll("\\.", "/");
        File file = new File(contextParam.getOutputPath() + "/" + proPath + "/controller/" + tableContext.get("entity") + "controller.java");
        return file.getAbsolutePath();
    }
}
