package com.tr.query.bind.querybind.mapping.util;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;

public class ExcelUtil {
	
	public static String getStringFrom(Cell cell) {
		if (cell == null)
			return "";
		cell.setCellType(CellType.STRING);
		
		return cell.getStringCellValue().replaceAll("\\n", "").replaceAll("\\r", "");
	}
}
