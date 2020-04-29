package com.monggo.service.impl;

import com.monggo.entity.File;
import com.monggo.mapper.FileMapper;
import com.monggo.service.IFileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
