package com.sfac.javaSpringBoot.modules.test.controller;

import com.sfac.javaSpringBoot.modules.test.entity.City;
import com.sfac.javaSpringBoot.modules.test.entity.Country;
import com.sfac.javaSpringBoot.modules.test.service.CityService;
import com.sfac.javaSpringBoot.modules.test.service.CountryService;
import com.sfac.javaSpringBoot.modules.test.vo.ApplicationTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/test")
public class TestController {
    //导入日志系统
    private final static Logger LOGGER= LoggerFactory.getLogger(TestController.class);
    @Value("${server.port}")
    private int port;
    @Value("${com.name}")
    private String name;
    @Value("${com.age}")
    private int age;
    @Value("${com.desc}")
    private String desc;
    @Value("${com.random}")
    private String random;
    @GetMapping("/logTest")
    @ResponseBody
    public String logTest(){
        LOGGER.trace("This is trace log");
        LOGGER.debug("This is debug log");
        LOGGER.info("This is info log");
        LOGGER.warn("This is warn log");
        LOGGER.error("This is error log");
        return "This is log test";
    }
    @Autowired
    private CityService cityService;
    @Autowired
    private CountryService countryService;
//引入注册的文件进行注册
    @Autowired
    private ApplicationTest applicationTest;
    @GetMapping("/config")
    @ResponseBody
    public String configTest() {
        StringBuffer sb = new StringBuffer();
        sb.append(port).append("----")
                .append(name).append("----")
                .append(age).append("----")
                .append(desc).append("----")
                .append(random).append("----").append("<br>");
        sb.append(applicationTest.getPort()).append("----")
                .append(applicationTest.getName()).append("----")
                .append(applicationTest.getAge()).append("----")
                .append(applicationTest.getDesc()).append("----")
                .append(applicationTest.getRandom()).append("----").append("<br>");
        return sb.toString();
    }


    /**
     * 127.0.0.1/test/index
     */
    //方法直接返回路径，类型为字符串型
    @GetMapping("/index")
    public String testIndexPage(ModelMap modelMap){
        int countryId = 522;
        List<City> cities = cityService.getcitiesByCountryId(countryId);
        cities = cities.stream().limit(10).collect(Collectors.toList());
        Country country = countryService.getCountryByCountryId(countryId);

        modelMap.addAttribute("thymeleafTitle", "scdscsadcsacd");
        modelMap.addAttribute("checked", true);
        modelMap.addAttribute("currentNumber", 99);
        modelMap.addAttribute("changeType", "checkbox");
        modelMap.addAttribute("baiduUrl", "/test/log");
        modelMap.addAttribute("city", cities.get(0));
        modelMap.addAttribute("shopLogo",
                "http://cdn.duitang.com/uploads/item/201308/13/20130813115619_EJCWm.thumb.700_0.jpeg");
        modelMap.addAttribute("shopLogo",
                "/upload/1111.png");
        modelMap.addAttribute("country", country);
        modelMap.addAttribute("cities", cities);
        modelMap.addAttribute("updateCityUri", "/api/city");
//        modelMap.addAttribute("template","test/index");
        return "index";
    }

    /**
     * 127.0.0.1/test/testDesc?paramKey=fuck ---- get
     * @return
     */
    @GetMapping("/index2")
    public String testIndex2Page(ModelMap modelMap){
        modelMap.addAttribute("template","test/index2");
        return "index";
    }

    /**
     * 127.0.0.1/test/testDesc?paramKey=fuck ---- get
     */
    @GetMapping("/testDesc")
    @ResponseBody
    public String testDesc(HttpServletRequest request,
                           @RequestParam("paramKey") String paramValue){
        String paramValue2=request.getParameter("paramKey");
        return "This is test module desc." + paramValue + "==" + paramValue2;
    }

    /**
     *127.0.0.1/test/file ---- post
     */
    @PostMapping(value = "/file",consumes = "multipart/form-data")
    public String uploadFile(@RequestParam MultipartFile file,
                             RedirectAttributes redirectAttributes){
        //考虑文件是否为空
        if (file.isEmpty()){
            redirectAttributes.addFlashAttribute("message","please select file.");
            return "redirect:/test/index";
        }
        try {
            //file.getOriginalFilename得到文件的名字
            String destFilePath="D:\\img\\"+file.getOriginalFilename();
            //创建一个文件，参数为创建的地址
            File destFile=new File(destFilePath);
            //把文件迁移到哪里去
            file.transferTo(destFile);
            //设置传递参数如果成功显示信息并刷新
            redirectAttributes.addFlashAttribute("message","uplaod file success");
        } catch (IOException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message","uplaod file failed.");
        }
        return "redirect:/test/index";
    }

    /**
     *
     * 127.0.0.1/test/files ---- post
     *
     */
    @PostMapping(value = "/files",consumes = "multipart/form-data")
    public String uploadFiles(@RequestParam MultipartFile[] files,
                              RedirectAttributes redirectAttributes){
        boolean empty=true;
        try {
            for (MultipartFile file : files) {
                if (file.isEmpty()){
                    continue;
                }
                //file.getOriginalFilename得到文件的名字
                String destFilePath="D:\\img\\"+file.getOriginalFilename();
                //创建一个文件，参数为创建的地址
                File destFile=new File(destFilePath);
                //把文件迁移到哪里去
                file.transferTo(destFile);
                empty=false;
            }
            if (empty){
                redirectAttributes.addFlashAttribute("message","please select file.");
            }else {
                redirectAttributes.addFlashAttribute("message","uplaod file success");
            }

        } catch (IOException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message","uplaod file failed.");
        }
        return "redirect:/test/index";
    }

    @GetMapping(value = "/downloadFile")
    public ResponseEntity downloadFile(@RequestParam("fileName") String fileName){
        try {
            Resource resource=new UrlResource(Paths.get("D:\\img\\"+fileName).toUri());
            //设置编码格式，否则会乱码
            String downName=new String(resource.getFilename().getBytes("utf-8"),"ISO8859-1");
            return ResponseEntity.ok().header("Content-Type","application/octet-stream")
                    .header(HttpHeaders.CONTENT_DISPOSITION,String.format("attachment; filename=\"%s\"",resource.getFilename()))
                    .body(resource);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

