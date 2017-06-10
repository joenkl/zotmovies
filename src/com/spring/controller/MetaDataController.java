package com.spring.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;

import com.spring.dao.MetadataDao;


@Controller
public class MetaDataController {
	
	@Autowired
	MetadataDao metadata;
	
	@Autowired
	ServletContext context; 
	
	@RequestMapping(value="/_metadata")
	public ModelAndView metadata() throws SQLException, IOException{
		ModelAndView model = new ModelAndView();
		model.setViewName("_metadata");
		
		String metaData = metadata.getMeta();
		
		String path = context.getRealPath("/WEB-INF");
		File logs = new File(path, "Logs");
		
		System.out.println(path);
		
		logs = new File (path, "log.txt");
		
		if (!logs.exists())
			logs.createNewFile();
		
		FileWriter fw = new FileWriter(logs.getAbsoluteFile());
		
		System.out.println(logs.getAbsoluteFile().toString());
        BufferedWriter bw = new BufferedWriter(fw);
		bw.write(metaData);
		bw.close();
		
		model.addObject("log", logs.getAbsoluteFile().toString());
		model.addObject("metaData", metaData);
		return model;
	}
	
	@RequestMapping(value="/showAllReports")
	public ModelAndView showAllReports(){
		return new ModelAndView("all-reports");
	}


	
}
