package utils;

import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	private Workbook workbook;

	public ExcelUtils(String filePath) {
		try (FileInputStream file = new FileInputStream(filePath)) {
			workbook = new XSSFWorkbook(file);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Excel dosyası açılırken hata oluştu: " + e.getMessage());
		}
	}

	public String getCellValue(int rowNum, int colNum) {
		Sheet sheet = workbook.getSheetAt(0);
		Row row = sheet.getRow(rowNum);
		if (row == null)
			return "";

		Cell cell = row.getCell(colNum);
		if (cell == null)
			return "";

		switch (cell.getCellType()) {
		case STRING:
			return cell.getStringCellValue();
		case NUMERIC:
			return String.valueOf(cell.getNumericCellValue());
		case BOOLEAN:
			return String.valueOf(cell.getBooleanCellValue());
		default:
			return "";
		}
	}

	public void closeWorkbook() {
		try {
			if (workbook != null)
				workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
