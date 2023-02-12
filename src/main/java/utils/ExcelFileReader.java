package utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;

public class ExcelFileReader {

    public static final Iterator<Object[]> getMultipleData(String filePath, String testCaseName) throws IOException {
        DataFormatter dataFormatter = new DataFormatter();
        File file = new File(filePath);
        FileInputStream fileInputStream = new FileInputStream(file);
        Workbook workbook = new XSSFWorkbook(fileInputStream);
        String sheetName = testCaseName.split("_")[1];
        System.out.println("testcasename:" + testCaseName);
        System.out.println("Sheet name"+ sheetName);
        Sheet sheet = workbook.getSheet(sheetName);
        ArrayList<Object[]> rowData = new ArrayList<Object[]>();
        int rowNum = sheet.getLastRowNum();
        String userName = null;
        String password = null;
        for(int i=0;i<rowNum;i++){
            if(sheet.getRow(i).getRowNum() == 0)
                continue;

            Cell testCaseCell = sheet.getRow(i).getCell(1);
            Cell userNameCell = sheet.getRow(i).getCell(2);
            Cell passwordCell = sheet.getRow(i).getCell(3);

            if(testCaseName.equalsIgnoreCase(testCaseCell.getStringCellValue())){
                userName = dataFormatter.formatCellValue(userNameCell);
                password = dataFormatter.formatCellValue(passwordCell);
            }
            else{
                continue;
            }
            Object object[] = {userName, password};
            rowData.add(object);
            break;
        }
        workbook.close();
        return rowData.iterator();
    }

//    public static Object[][] getData(String filePath, String testCaseName){
//        Object data[][] = null;
//        XSSFWorkbook workbook = null;
//
//        File file = new File(filePath);
//        try {
//            FileInputStream fileInputStream = new FileInputStream(file);
//            workbook = new XSSFWorkbook(fileInputStream);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        String sheetName = testCaseName.split("_")[1];
//        Sheet sheet = workbook.getSheet(sheetName);
//        int rowNum = sheet.getLastRowNum();
//        int columnNum = sheet.getRow(0).getLastCellNum();
//        data = new String[rowNum][columnNum];
//        DataFormatter dataFormatter = new DataFormatter();
//        for(int i=1;i<rowNum+1;i++){
//            if(sheet.getRow(i).getCell(1).equals(testCaseName)) {
//                for (int j = 0; j < columnNum; j++) {
//                    Cell cell = sheet.getRow(i).getCell(j);
//                    data[i - 1][j] = dataFormatter.formatCellValue(cell);
//                }
//                break;
//            }
//        }
//        return data;
//    }
}
