package com.example.libreria.resource;



import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.compress.utils.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.libreria.Expoter.ExcelFileExporter;
import com.example.libreria.services.AutorServices;
import com.example.libreria.services.CategoriaServices;
import com.example.libreria.services.LibroServices;
@RestController
@RequestMapping("/excel")
@Controller
public class DownloadExcel {
	 private final CategoriaServices categoriaServices;
	 private final AutorServices autorServices;
	 private final LibroServices libroServices;
	 
	   public DownloadExcel(CategoriaServices categoriaServices,AutorServices autorServices,LibroServices libroServices) {
	        this.categoriaServices = categoriaServices;
	        this.autorServices = autorServices;
	        this.libroServices = libroServices;
	    }
	 
	   
	   @GetMapping("/download")
	   public void downloadCsv(HttpServletResponse response) throws IOException {
	        response.setContentType("application/octet-stream");
	        response.setHeader("Content-Disposition", "attachment; filename=libreria.xlsx");
	        ByteArrayInputStream stream = ExcelFileExporter.createExcel(autorServices.findAllAutors(),categoriaServices.findAllCategorias(),libroServices.findAllLibros());
	        IOUtils.copy(stream, response.getOutputStream());
	    }
	   
	

	}


