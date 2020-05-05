package com.monggo.service.impl;

import com.monggo.common.utils.R;
import com.monggo.entity.File;
import com.monggo.mapper.FileMapper;
import com.monggo.service.IFileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>
 * 文件表 服务实现类
 * </p>
 *
 * @author fhs
 * @since 2020-04-27
 */
@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, File> implements IFileService {

    @Value("${system.type}")
    private String systemType;

    @Value("${system.domain}")
    private String systemDomain;

    @Value("${system.file-load}")
    private String systemfLoad;

    @Value("${system.file-cate}")
    private String systemfCate;

    @Value("${system.file-save}")
    private String systemfSave;


    @Async
    @Override
    public R upload(MultipartFile picture) {
        // 获取文件地址  可存储到静态资源服务器
        // 获取项目位置
        String path = "";
        if(systemType.equals("windows")){
            path += System.getProperty("user.dir") + systemfSave;
        }
        if(systemType.equals("linux")){
            path += systemfSave;
        }
        java.io.File filePath = new java.io.File(path);
        System.out.println("文件的保存路径：" + path);
        if (!filePath.exists() && !filePath.isDirectory()) {
            System.out.println("目录不存在，创建目录:" + filePath);
            filePath.mkdir();
        }
        //获取原始文件名称(包含格式)
        String originalFileName = picture.getOriginalFilename();
        System.out.println("原始文件名称：" + originalFileName);

        //获取文件类型，以最后一个`.`为标识
        String type = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
        System.out.println("文件类型：" + type);
        //获取文件名称（不包含格式）
        String name = originalFileName.substring(0, originalFileName.lastIndexOf("."));
        //设置文件新名称: 当前时间+文件名称（不包含格式）
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String date = sdf.format(d);
        String fileName = date + name + "." + type;
        System.out.println("新文件名称：" + fileName);
        //在指定路径下创建一个文件
        java.io.File targetFile = new java.io.File(path, fileName);
        //将文件保存到服务器指定位置
        try {
            picture.transferTo(targetFile);
            System.out.println("上传成功");
            File file = new File();
            file.setName(fileName);
            file.setPath(path);
            file.setImg(systemDomain + "/" + systemfLoad + "/" + systemfCate + "/" +  fileName);
            this.save(file);
            //将文件在服务器的存储路径返回
            return R.ok().put("url", systemDomain + "/" + systemfLoad + "/" + systemfCate + "/" +  fileName);
        } catch (IOException e) {
            System.out.println("上传失败");
            e.printStackTrace();
            return R.error("上传失败");
        }
    }
}
