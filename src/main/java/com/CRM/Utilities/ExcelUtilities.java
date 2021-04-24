package com.CRM.Utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtilities {
	
	XSSFWorkbook wb;
	XSSFSheet sh;
	public ExcelUtilities(String path,String sheetName) throws IOException{
		try {
			FileInputStream fis=new FileInputStream(path);
			 wb=new XSSFWorkbook(fis);
			 sh=wb.getSheet(sheetName);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int GetRowCount() {
		int rows=sh.getLastRowNum();
		return rows;
	}
	
	public int GetColumnCount() {
		int cols=sh.getRow(0).getLastCellNum();
		return cols;
	}
	
	public String getCelldata(String column,int rowno) {
		int col_index=0;
        String col="";
		int cols=sh.getRow(0).getLastCellNum();
		for(int i=0;i<cols;i++) {
			String colval=sh.getRow(0).getCell(i).getStringCellValue();
			if(colval.equalsIgnoreCase(column)) {
				col_index=i;
				break;
			}
		}
		
		//col=sh.getRow(rowno).getCell(col_index).getStringCellValue();
		XSSFCell cell=sh.getRow(rowno).getCell(col_index);
		if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC) {
			int cellval=(int)(sh.getRow(rowno).getCell(col_index).getNumericCellValue());
			col=String.valueOf(cellval);
		}else if(cell.getCellType()==Cell.CELL_TYPE_STRING) {
			col=sh.getRow(rowno).getCell(col_index).getStringCellValue();
		}else if(cell.getCellType()==Cell.CELL_TYPE_BOOLEAN) {
			col=String.valueOf(sh.getRow(rowno).getCell(col_index).getStringCellValue());
		}
		return col;
	}
	
	public String getCelldataByIndex(int rowno,int colmunno) {
		String col="";
		XSSFCell cell=sh.getRow(rowno).getCell(colmunno);
		if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC) {
			int cellval=(int)(sh.getRow(rowno).getCell(colmunno).getNumericCellValue());
			col=String.valueOf(cellval);
		}else if(cell.getCellType()==Cell.CELL_TYPE_STRING) {
			col=sh.getRow(rowno).getCell(colmunno).getStringCellValue();
		}else if(cell.getCellType()==Cell.CELL_TYPE_BOOLEAN) {
			col=String.valueOf(sh.getRow(rowno).getCell(colmunno).getStringCellValue());
		}
		return col;
	}


}
