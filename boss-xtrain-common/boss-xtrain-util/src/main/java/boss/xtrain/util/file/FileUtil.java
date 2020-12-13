package boss.xtrain.util.file;


import java.io.File;
import java.io.IOException;

import org.apache.commons.io.*;

/**
 * @author Gyq
 * @program neu-g02-huluwa-boss-exam-platform
 * @description 文件操作工具类
 * @create 2020-07-03
 * @since 1.0
 */
public class FileUtil extends FileUtils {

    /**
     * 文件内容的复制，如果拷贝后的文件已存在，将替换已存在文件的内容
     *
     * @param src  需要拷贝的文件
     * @param dest 拷贝后的文件
     * @throws FileException IO异常
     */
    public static void copyFile(File src, File dest) throws FileException{
        try {
            org.apache.commons.io.FileUtils.copyFile(src, dest);
        }catch (IOException e){
            throw new FileException("FILE","200901","FILE_COPY_FAIL");
        }

    }

    /**
     * 删除文件
     *
     * @param filePath 文件
     * @return 是否删除成功
     */
    public static boolean deleteFile(String filePath) {
        boolean flag = false;
        File file = new File(filePath);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            flag = file.delete();
        }
        return flag;
    }

    /**
     * 统计文件大小
     *
     * @param file 文件
     * @return 文件大小
     */
    public static long sizeOf(final File file) {
        return FileUtils.sizeOf(file);
    }
}
