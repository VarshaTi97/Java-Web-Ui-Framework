package utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExcelFileReader {
    public static Object[][] getData(String filePath, String sheetName){
        Object data[][] = null;
        XSSFWorkbook workbook = null;

        File file = new File(filePath);
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            workbook = new XSSFWorkbook(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Sheet sheet = workbook.getSheet(sheetName);
        int rowNum = sheet.getLastRowNum();
        int columnNum = sheet.getRow(0).getLastCellNum();
        data = new String[rowNum][columnNum];
        DataFormatter dataFormatter = new DataFormatter();
        for(int i=1;i<rowNum+1;i++){
            for(int j=0;j<columnNum;j++){
               Cell cell = sheet.getRow(i).getCell(j);
                data[i-1][j] = dataFormatter.formatCellValue(cell);
            }
        }
        return data;
    }
}
