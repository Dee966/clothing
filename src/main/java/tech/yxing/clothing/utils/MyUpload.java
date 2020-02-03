package tech.yxing.clothing.utils;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

//@ConfigurationProperties("upload.config")
public class MyUpload {

    public static final String FILE_PATH = "/usr/local/src/vhr/dist/static/";
    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    /**
     * @methodDesc: 图片上传
     * @Param: MultipartFile
     * @Author: Joe
     * @Date 2019-12-30-00-32
     */
    public static String pictureUpload(MultipartFile picture) throws IOException {
        //图片新名字
        String newName = UUID.randomUUID().toString();
        //图片原来的名字
        String oldName = picture.getOriginalFilename();
        //后缀
        String sux = oldName.substring(oldName.lastIndexOf("."));
        //新建本地文件流
        File file = new File(FILE_PATH+newName+sux);
        //写入本地磁盘
        picture.transferTo(file);
        String filePath = file.getCanonicalPath();
//        String picPath = filePath.substring(filePath.indexOf("t")+1);
        String finalPath = filePath.substring(23);
        return finalPath;
    }

    public static void main(String[] args){
        String file = "/usr/local/src/vhr/dist/static/1.jpg";
        String picPath = file.substring(file.indexOf("g")+1);
        String finalPath = file.substring(23);
        System.out.println(finalPath);
    }
}
