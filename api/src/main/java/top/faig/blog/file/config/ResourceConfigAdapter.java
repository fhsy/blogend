package top.faig.blog.file.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author fhs
 * 2020/4/29 13:06
 * 文件说明: 配置虚拟文件路径的映射
 * 上传后需要重启服务器才能访问图片  这个配置可以不用重启直接访问
 *
 */
@Configuration
public class ResourceConfigAdapter extends WebMvcConfigurerAdapter {

    @Value("${system.type}")
    private String systemType;

    @Value("${system.file-save}")
    private String systemfSave;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //获取文件的真实路径 work_project代表项目工程名 需要更改
        String path = System.getProperty("user.dir");
        if(systemType.equals("windows")){
            path += "\\api\\src\\main\\resources\\static\\img\\";
        }
        registry.addResourceHandler("/img/**").
                addResourceLocations("file:"+path);
    }
}
