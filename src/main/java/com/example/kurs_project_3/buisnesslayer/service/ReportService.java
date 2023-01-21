package com.example.kurs_project_3.buisnesslayer.service;

import com.example.kurs_project_3.buisnesslayer.JSONview.PostJSON;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.swing.text.html.HTML;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {

    @Autowired
    private PostService postService;

    public void exportReport() throws FileNotFoundException, JRException {
        String path = "src/main/resources/reports";
        List<PostJSON> postJSONS=postService.getPostsJSONList();
        //Load file and compile it
        File file= ResourceUtils.getFile("classpath:posts.jrxml");
        JasperReport jasperReport= JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource=new JRBeanCollectionDataSource(postJSONS);
        Map <String,Object> parameters=new HashMap<>();
        parameters.put("createdBy","Yedomakha Ilya and Kolot Oleg");
        JasperPrint jasperPrint=JasperFillManager.fillReport(jasperReport,parameters,dataSource);
        JasperExportManager.exportReportToHtmlFile(jasperPrint,path+"\\posts.html");
    }
}
