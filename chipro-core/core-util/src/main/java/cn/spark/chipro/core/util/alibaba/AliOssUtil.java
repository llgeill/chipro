package cn.spark.chipro.core.util.alibaba;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.OSSObject;

import java.io.*;

public class AliOssUtil {

    private static String endpoint = "oss-cn-shenzhen.aliyuncs.com";
    private static String accessKeyId = "LTAIX0zvxsSd2bcz";
    private static String accessKeySecret = "AEZU0zMt95wHMKgLyjD6VvFZWRJdHs";
    private static String bucketName = "chipro";
    private static String objectName = "template/批量创建校园帐号模板.xls";

    public static void main(String[] args) throws IOException {
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        // 上传文件流。
        InputStream inputStream = new FileInputStream("/Users/liliguang/Desktop/MANAGE_SCHOOL_USER.sql");
        ossClient.putObject(bucketName, "sql/test.sql", inputStream);
        // 关闭OSSClient。
        ossClient.shutdown();
    }


    /**
     * 通过流下载文件
     * @param objectName 对象名字 路径+名称
     */
    private static Boolean downFileByStream(String objectName){
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        try {
            // ossObject包含文件所在的存储空间名称、文件名称、文件元信息以及一个输入流。
            OSSObject ossObject = ossClient.getObject(bucketName, objectName);
            // 读取文件内容。
            System.out.println("Object content:");
            BufferedReader reader = new BufferedReader(new InputStreamReader(ossObject.getObjectContent()));
            while (true) {
                String line = reader.readLine();
                if (line == null) break;
                System.out.println("\n" + line);
            }
            // 数据读取完成后，获取的流必须关闭，否则会造成连接泄漏，导致请求无连接可用，程序无法正常工作。
            reader.close();
            return true;
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message: " + oe.getErrorMessage());
            System.out.println("Error Code:       " + oe.getErrorCode());
            System.out.println("Request ID:      " + oe.getRequestId());
            System.out.println("Host ID:           " + oe.getHostId());
            return false;
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message: " + ce.getMessage());
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            /*
             * Do not forget to shut down the client finally to release all allocated resources.
             */
            ossClient.shutdown();
        }
    }
}
