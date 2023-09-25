package Util;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

public class ExcelUtils {

    public static Object[][] getTestData(String filePath, String sheetName) throws IOException {
    	
        FileInputStream fis = new FileInputStream("C:\\Users\\THIS PC\\eclipse-workspace\\shivin_tech\\TestProject_OrangeHRM\\src\\test\\java\\Util\\TestDatas.xlsx");
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheet(sheetName);
        int rowCount = sheet.getPhysicalNumberOfRows();
        int colCount = sheet.getRow(0).getPhysicalNumberOfCells();
        
        Object[][] data = new Object[rowCount - 1][colCount];

        Iterator<Row> iterator = sheet.iterator();
        int i = 0;

        while (iterator.hasNext()) {
            Row currentRow = iterator.next();
            if (currentRow.getRowNum() == 0) {
                // Skip header row
                continue;
            }
            Iterator<Cell> cellIterator = currentRow.iterator();
            int j = 0;
            while (cellIterator.hasNext()) {
                Cell currentCell = cellIterator.next();
                switch (currentCell.getCellType()) {
                    case STRING:
                        data[i][j] = currentCell.getStringCellValue();
                        break;
                    case NUMERIC:
                        data[i][j] = String.valueOf(currentCell.getNumericCellValue());
                        break;
                    default:
                        break;
                }
                j++;
            }
            i++;
        }

        workbook.close();
        fis.close();

        return data;
    }
}

