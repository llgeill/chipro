package cn.spark.chipro.generator.modular.controller;


import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.ZipUtil;
import cn.spark.chipro.generator.core.generator.param.ContextParam;
import cn.spark.chipro.generator.modular.guns.GunsExecutor;
import cn.spark.chipro.generator.modular.model.DatabaseInfo;
import cn.spark.chipro.generator.modular.repository.DatabaseInfoRepository;
import cn.spark.chipro.generator.modular.restful.mybatisplus.param.MpParam;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.*;
import java.util.List;
import java.util.Optional;

/**
 * 代码生成控制器
 *
 * @author fengshuonan
 * @date 2019-01-30-2:39 PM
 */
@Controller
public class GeneratorController {

    @Autowired
    private DatabaseInfoRepository databaseInfoRepository;

    /**
     * 代码生成主页
     *
     * @author fengshuonan
     * @Date 2019/1/30 2:49 PM
     */
    @RequestMapping("")
    public String index(Model model) {

        List<DatabaseInfo> all = databaseInfoRepository.findAll();
        model.addAttribute("dataSources", all);

        return "/gen.html";
    }

    /**
     * 执行代码生成
     *
     * @author fengshuonan
     * @Date 2019-01-11
     */
    @RequestMapping(value = "/execute")
    @ResponseBody
    public ResponseEntity<InputStreamResource> execute(String author, String proPackage, String removePrefix,
                                                       Long dataSourceId, String tables) {
        tables = tables.substring(3);

        String[] tableArray = tables.split("CAT");

        //获取数据源信息
        Optional<DatabaseInfo> dbInfo = this.databaseInfoRepository.findById(dataSourceId);

        DatabaseInfo databaseInfo = dbInfo.get();

        ContextParam contextParam = new ContextParam();
        contextParam.setAuthor(author);
        contextParam.setProPackage(proPackage);
        contextParam.setJdbcDriver(databaseInfo.getJdbcDriver());
        contextParam.setJdbcUserName(databaseInfo.getUserName());
        contextParam.setJdbcPassword(databaseInfo.getPassword());
        contextParam.setJdbcUrl(databaseInfo.getJdbcUrl());

        //获取临时目录
        long fileName = IdWorker.getId();
        String tempPath = System.getProperty("java.io.tmpdir") + File.separator + "gunsGeneration" + File.separator + fileName;
        contextParam.setOutputPath(tempPath);

        MpParam mpContextParam = new MpParam();
        mpContextParam.setGeneratorInterface(true);
        mpContextParam.setIncludeTables(tableArray);

        if (StrUtil.isNotEmpty(removePrefix)) {
            mpContextParam.setRemoveTablePrefix(new String[]{removePrefix});
        }

        //代码生成contextParam
        GunsExecutor.executor(contextParam, mpContextParam);

        //打包下载代码
        File zip = ZipUtil.zip(tempPath);

        return renderFile(fileName + ".zip", zip.getAbsolutePath());
    }

    /**
     * 返回前台文件流
     *
     * @param fileName    文件名
     * @param inputStream 输入流
     * @return
     * @author 0x0001
     */
    private ResponseEntity<InputStreamResource> renderFile(String fileName, InputStream inputStream) {
        InputStreamResource resource = new InputStreamResource(inputStream);
        String dfileName = null;
        try {
            dfileName = new String(fileName.getBytes("gb2312"), "iso8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", dfileName);
        return new ResponseEntity<>(resource, headers, HttpStatus.CREATED);
    }

    /**
     * 返回前台文件流
     *
     * @author fengshuonan
     * @date 2017年2月28日 下午2:53:19
     */
    protected ResponseEntity<InputStreamResource> renderFile(String fileName, String filePath) {
        try {
            final FileInputStream inputStream = new FileInputStream(filePath);
            return renderFile(fileName, inputStream);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("文件读取错误");
        }
    }

    /**
     * 返回前台文件流
     *
     * @author fengshuonan
     * @date 2017年2月28日 下午2:53:19
     */
    protected ResponseEntity<InputStreamResource> renderFile(String fileName, byte[] fileBytes) {
        return renderFile(fileName, new ByteArrayInputStream(fileBytes));
    }


}
