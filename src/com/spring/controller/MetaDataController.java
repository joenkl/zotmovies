package com.spring.controller;

import java.sql.SQLException;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;


import com.spring.dao.MetadataDao;

@Controller
public class MetaDataController {
	
	@Autowired
	MetadataDao metadata;
	
	@RequestMapping(value="/_metadata")
	public ModelAndView metadata() throws SQLException{
		ModelAndView model = new ModelAndView();
		model.setViewName("_metadata");
		
		String metaData = metadata.getMeta();
		
		System.out.println(metaData);
		model.addObject("metaData", metaData);
		return model;
	}
	
}
