package com.junit;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class APPTest {

    @Test
    public void test01(){
        try {
            Workbook wb = WorkbookFactory.create(new File("C:\\Users\\Administrator\\Desktop\\customers.xlsx"));
            Sheet sheet = wb.getSheetAt(0);
            for (int i = 0; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i+1);
                for (int j = 0; j < row.getLastCellNum(); j++) {
                    Cell cell = row.getCell(j);
                    String value = parseExcelValueToString(cell);
                    if(i >0 && j == 0){
                        value = value.substring(0,value.indexOf("."));
                    }
                    System.out.print(value + "      ");
                }
                System.out.println();
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }

    private String parseExcelValueToString(Cell cell) {
        String result = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        CellType cellTypeEnum = cell.getCellTypeEnum();
        switch (cellTypeEnum){
            case STRING:
                result = cell.getStringCellValue();
                break;
            case NUMERIC:
                if(HSSFDateUtil.isCellDateFormatted(cell)) {
                    Date date = cell.getDateCellValue();
                    result = sdf.format(date);
                }else{
                    BigDecimal decimal = new BigDecimal(String.valueOf(cell.getNumericCellValue()));
                    result = decimal.toPlainString();
                }
                break;
            case BLANK:
                result = "";
                break;
            case FORMULA:
                result = String.valueOf(cell.getCellFormula());
                break;
            case BOOLEAN:
                result = String.valueOf(cell.getBooleanCellValue());
                break;
            default:
                result = null;
        }
        return result;
    }
}
