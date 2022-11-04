package PageObjectModule.PageObjectModule;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.FileInputStream;

public class GetDataProvider {

    @DataProvider
    public Object[][] testData(){
        return new Object[][]{
                {"9790499064","subbulakshmi"}
        };
    }

    /*
     *
     * Accessing data from excel for data provider
     *
     *
     */
    @DataProvider(name="credential")
    public Object[][] faceBookCredential() throws Exception {
        File inputFile = new File("./src/test/resources/Book1.xlsx");
        FileInputStream fileInputStream = new FileInputStream(inputFile);
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        XSSFSheet xssfSheet = workbook.getSheet("Sheet1");
        int numberOfRows = xssfSheet.getPhysicalNumberOfRows();
        int numberOfColumns = xssfSheet.getRow(0).getLastCellNum();
        Object[][] cellValues = new Object[numberOfRows-1][numberOfColumns];
        for (int i=0;i< numberOfRows-1 ;i++){
            for (int j=0;j< numberOfColumns;j++){
                DataFormatter dataFormatter = new DataFormatter();
                cellValues[i][j] = dataFormatter.formatCellValue(xssfSheet.getRow(i+1).getCell(j));
            }
        }
        workbook.close();
        fileInputStream.close();
        return cellValues;
    }

}
