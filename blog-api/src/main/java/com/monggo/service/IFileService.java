package com.monggo.service;

import com.monggo.common.utils.R;
import com.monggo.entity.File;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 文件表 服务类
 * </p>
 *
 * @author fhs
 * @since 2020-04-27
 */
public interface IFileService extends IService<File> {
    R upload(@RequestParam("picture") MultipartFile picture);
}
