package com.spring.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LogAnalysisController {
	@Autowired
	ServletContext context;

	@RequestMapping(value="/tstj")
	public ModelAndView showReport() {
		// get the log location:
		String path = context.getRealPath("/WEB-INF");

		BufferedReader br = null;
		FileReader fr = null;
		ModelAndView model = new ModelAndView("logAnalysisReport");

		try {

			File file = new File(path, "TS_TJ_logs.txt");
			System.out.println(file.getAbsolutePath());

			if (!file.exists()) {
				System.out.println("not exist!");
				return model; 
			}

			fr = new FileReader(file);
			br = new BufferedReader(fr);

			String sCurrentLine;

			br = new BufferedReader(new FileReader(file));
			
			long TS = 0;
			long no_TS = 0;
			long TJ = 0; 
			long no_TJ = 0; 

			while ((sCurrentLine = br.readLine()) != null) {
				
				if(sCurrentLine.contains("TJ")){
					
					
					String[] tokens = sCurrentLine.split(" ");
					
					String token = tokens[2];
					if(tryParseLong(token)){
//						System.out.println(Long.parseLong(token));
						no_TJ++;
						TJ += Long.parseLong(token);
					}
					
				}
				else if(sCurrentLine.contains("TS")){
					
					String[] tokens = sCurrentLine.split(" ");
					
					String token = tokens[2];
					if(tryParseLong(token)){
//						System.out.println(Long.parseLong(token));
						no_TS++;
						TS += Long.parseLong(token);
					}
				
					
				}
			}
			
			System.out.println(TS);
			System.out.println(TJ);
			double avg_TS = (double) TS / (double) no_TS;
			double avg_TJ = (double) TJ / (double) no_TJ; 
			System.out.println("average TS = " + avg_TS + "ns");
			System.out.println("average TJ = " + avg_TJ + "ns");
			
			
			model.addObject("avg_ts", avg_TS);
			model.addObject("avg_tj", avg_TJ);
			
			return model; 
			

		} catch (IOException e) {

			e.printStackTrace();
			
			return model; 
			

		} 
		finally {

			try {

				if (br != null)
					br.close();

				if (fr != null)
					fr.close();
				
				return model; 
				
				

			} catch (IOException ex) {
				
				ex.printStackTrace();
				

			}

		}
		

	}
	
	public Boolean tryParseLong(String no){
		try{
			long n = Long.parseLong(no);
			
			return true; 
			
		}catch(Exception e){
			return false;
		}
	}

}
