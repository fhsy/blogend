package com.monggo.controller;


import com.monggo.common.utils.R;
import com.monggo.service.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>
 * 文件表 前端控制器
 * </p>
 *
 * @author fhs
 * @since 2020-04-27
 */
@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private IFileService fileService;

    /**
     * @api {POST}  /file/img  图片上传
     * @apiGroup file
     * @apiSuccess {file} picture 图片流
     * @apiSuccessExample {json} 成功
     * {
     * "msg":"success","code":0,"url":"http://localhost:9091/blog-api/img/20200429132327img_34f9f763a6e67595c34b0ce0c0fcace7.jpg"}
     */
    @PostMapping("/img")
    public R upload(@RequestParam("picture") MultipartFile picture) {

        return fileService.upload(picture);
    }

}
