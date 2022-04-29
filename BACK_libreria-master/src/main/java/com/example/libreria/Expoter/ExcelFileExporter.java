package com.example.libreria.Expoter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.example.libreria.model.Autor;
import com.example.libreria.model.Categoria;
import com.example.libreria.model.Libro;

public class ExcelFileExporter {
	public static ByteArrayInputStream createExcel(List<Autor> autor, List<Categoria> categoria, List<Libro> libro) {
		try (XSSFWorkbook workbook = new XSSFWorkbook()) {
			Sheet sheetAutor = workbook.createSheet("Autores");
			Sheet sheetCategoria = workbook.createSheet("Categorias");
			Sheet sheetLibros = workbook.createSheet("Libros");


			Row rowAutor = sheetAutor.createRow(0);
			Row rowCategoria = sheetCategoria.createRow(0);
			Row rowLibros = sheetLibros.createRow(0);

			CellStyle headercellstyle = workbook.createCellStyle();
			headercellstyle.setFillForegroundColor(IndexedColors.BRIGHT_GREEN.getIndex());
			headercellstyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

			Cell cellAutor = rowAutor.createCell(0);
			cellAutor.setCellStyle(headercellstyle);
			cellAutor.setCellValue("DNI");

			cellAutor = rowAutor.createCell(1);
			cellAutor.setCellStyle(headercellstyle);
			cellAutor.setCellValue("Nombre");

			cellAutor = rowAutor.createCell(2);
			cellAutor.setCellStyle(headercellstyle);
			cellAutor.setCellValue("Apellido1");

			cellAutor = rowAutor.createCell(3);
			cellAutor.setCellStyle(headercellstyle);
			cellAutor.setCellValue("Apellido2");

			cellAutor = rowAutor.createCell(4);
			cellAutor.setCellStyle(headercellstyle);
			cellAutor.setCellValue("Telefono");

			cellAutor = rowAutor.createCell(5);
			cellAutor.setCellStyle(headercellstyle);
			cellAutor.setCellValue("Email");

			for (int i = 0; i < autor.size(); i++) {
				Row dataRowAutor = sheetAutor.createRow(i + 1);
				dataRowAutor.createCell(0).setCellValue(autor.get(i).getDni());
				dataRowAutor.createCell(1).setCellValue(autor.get(i).getNombre());
				dataRowAutor.createCell(2).setCellValue(autor.get(i).getApellido1());
				dataRowAutor.createCell(3).setCellValue(autor.get(i).getApellido2());
				dataRowAutor.createCell(4).setCellValue(autor.get(i).getTelefono());
				dataRowAutor.createCell(5).setCellValue(autor.get(i).getEmail());
			}

			sheetAutor.autoSizeColumn(0);
			sheetAutor.autoSizeColumn(1);
			sheetAutor.autoSizeColumn(2);
			sheetAutor.autoSizeColumn(3);
			sheetAutor.autoSizeColumn(4);
			sheetAutor.autoSizeColumn(5);

			Cell cellCategoria = rowCategoria.createCell(0);
			cellCategoria.setCellStyle(headercellstyle);
			cellCategoria.setCellValue("ID_Categoria");

			cellCategoria = rowCategoria.createCell(1);
			cellCategoria.setCellStyle(headercellstyle);
			cellCategoria.setCellValue("Descripcion");

			for (int i = 0; i < categoria.size(); i++) {
				Row dataRowCategoria = sheetCategoria.createRow(i + 1);
				dataRowCategoria.createCell(0).setCellValue(categoria.get(i).getId());
				dataRowCategoria.createCell(1).setCellValue(categoria.get(i).getDescripcion());
			}
			sheetCategoria.autoSizeColumn(0);
			sheetCategoria.autoSizeColumn(1);

			Cell cellLibro = rowLibros.createCell(0);
			cellLibro.setCellStyle(headercellstyle);
			cellLibro.setCellValue("ID_Libro");

			cellLibro = rowLibros.createCell(1);
			cellLibro.setCellStyle(headercellstyle);
			cellLibro.setCellValue("Titulo");

			cellLibro = rowLibros.createCell(2);
			cellLibro.setCellStyle(headercellstyle);
			cellLibro.setCellValue("Edicion");

			cellLibro = rowLibros.createCell(3);
			cellLibro.setCellStyle(headercellstyle);
			cellLibro.setCellValue("ID_Autor");

			cellLibro = rowLibros.createCell(4);
			cellLibro.setCellStyle(headercellstyle);
			cellLibro.setCellValue("ID_Categoria");

			for (int i = 0; i < libro.size(); i++) {
				Row dataRowLibros = sheetLibros.createRow(i + 1);
				dataRowLibros.createCell(0).setCellValue(libro.get(i).getId());
				dataRowLibros.createCell(1).setCellValue(libro.get(i).getTitulo());
				dataRowLibros.createCell(2).setCellValue(libro.get(i).getEdicion());
				dataRowLibros.createCell(3).setCellValue(libro.get(i).getAutor().getNombre()+" "+libro.get(i).getAutor().getApellido1());
				dataRowLibros.createCell(4).setCellValue(libro.get(i).getCategoria().getDescripcion());
			}

			sheetLibros.autoSizeColumn(0);
			sheetLibros.autoSizeColumn(1);
			sheetLibros.autoSizeColumn(2);
			sheetLibros.autoSizeColumn(3);
			sheetLibros.autoSizeColumn(4);

			ByteArrayOutputStream output = new ByteArrayOutputStream();
			workbook.write(output);

			return new ByteArrayInputStream(output.toByteArray());
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		}

	}
}
