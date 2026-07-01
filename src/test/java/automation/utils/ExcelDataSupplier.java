package automation.utils;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.FileInputStream;

public class ExcelDataSupplier {

    @DataProvider(name="bloodRequestData")
    public String[][] getRequestBloodData() throws Exception {
        File file = new File("src/test/resources/Test.xlsx");
        System.out.println(file.exists());
        FileInputStream fis = new FileInputStream(file);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet("Sheet1");
        int row = sheet.getLastRowNum();
        int col = sheet.getRow(0).getLastCellNum();
        String[][] data = new String[row][col];
        for (int i = 1; i <= row; i++) {
            for (int j = 0; j < col; j++) {
                DataFormatter df = new DataFormatter();
                data[i-1][j]=df.formatCellValue(sheet.getRow(i).getCell(j));
            }
        }
        workbook.close();
        fis.close();
        return data;
    }
}