package boss.xtrain.util.cdn;

import boss.xtrain.util.string.StringUtil;
import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;

import com.aliyun.oss.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 戴若汐
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 实现文件上传的工具类
 * @create 2020-07-06 09:50
 * @since 1.0
 */

@Slf4j
@Component
public class CdnUtil {

    private CdnUtil() {
    }

    private static CdnProperties cdnProperties;

    @Autowired
    public void setCdnProperties(CdnProperties cdnProperties) {
        CdnUtil.cdnProperties = cdnProperties;
    }

    /**
     * 上传文件
     *
     * @param file 文件名
     * @param path 上传的路径 /face/xxx, /qrcode/xxx, /profile_picture/xxx
     */
    public static boolean upload(File file, String path) {
        OSSClient ossClient = null;
        try {
            ossClient = new OSSClient(cdnProperties.getEndpoint(), cdnProperties.getAccessKeyId(), cdnProperties.getAccessKeySecret());
            PutObjectRequest putObjectRequest = new PutObjectRequest(cdnProperties.getBucketName(), path, file);
            PutObjectResult result = ossClient.putObject(putObjectRequest);
            return null != result;
        } catch (ClientException e) {
            throw new CdnException("CDN", "130101", "UNKNOWN_CLIENT");
        } finally {
            if (null != ossClient) {
                ossClient.shutdown();
            }
        }
    }

    /**
     * 通过URL访问文件
     *
     * @param filePath 文件在服务器上的完整路径
     * @return 拥有权限的文件访问URL
     * @throws CdnException 方法可能因为路径名为空或生成URL为空而抛出CdnException
     */
    public static String getUrl(String filePath) {
        OSSClient ossClient = new OSSClient(cdnProperties.getEndpoint(), cdnProperties.getAccessKeyId(), cdnProperties.getAccessKeySecret());
        if (StringUtil.isEmpty(filePath)) {
            throw new CdnException("CDN", "130103", "EMPTY_FILE_PATH");
        } else {
            // 设置URL过期时间为15天
            Date expiration = new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24 * 15);
            URL url = ossClient.generatePresignedUrl(cdnProperties.getBucketName(), filePath, expiration);
            if (null != url) {
                return url.toString();
            } else {
                throw new CdnException("CDN", "130104", "GENERATE_NULL_URL");
            }
        }
    }

    /**
     * 批量获取face文件夹下的所有文件
     *
     * @return 所有文件的url
     */
    public static List<String> getBatchUrl() {
        String keyPrefix = "face/";
        OSSClient ossClient = new OSSClient(cdnProperties.getEndpoint(), cdnProperties.getAccessKeyId(), cdnProperties.getAccessKeySecret());

        // 构造ListObjectsRequest请求。
        ListObjectsRequest listObjectsRequest = new ListObjectsRequest(cdnProperties.getBucketName());
        // 设置prefix参数来获取face目录下的所有文件。
        listObjectsRequest.setPrefix(keyPrefix);

        // 递归列出face目录下的所有文件。
        ObjectListing listing = ossClient.listObjects(listObjectsRequest);

        // 遍历所有文件。
        List<String> urls = new ArrayList<>();
        for (OSSObjectSummary objectSummary : listing.getObjectSummaries()) {
            if (!keyPrefix.equals(objectSummary.getKey())) {
                urls.add(getUrl(objectSummary.getKey()));
            }
        }
        return urls;
    }

    /**
     * 删除OSS上的文件
     * @param filePath 文件在服务器上的完整路径
     * 批量获取face文件夹下的所有文件
     */
    public static void delete(String filePath) {
        OSSClient ossClient = null;
        if (StringUtil.isEmpty(filePath)) {
            throw new CdnException("CDN", "130103", "EMPTY_FILE_PATH");
        }
        try {
            ossClient = new OSSClient(cdnProperties.getEndpoint(), cdnProperties.getAccessKeyId(), cdnProperties.getAccessKeySecret());
            ossClient.deleteObject(cdnProperties.getBucketName(), filePath);
        } catch (ClientException | OSSException e) {
            throw new CdnException("CDN", "130102", "DELETE_FAILURE");
        } finally {
            if (null != ossClient) {
                ossClient.shutdown();
            }
        }
    }
}