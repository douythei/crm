package common;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelUtils {
    private static Workbook workbook;
    private static Sheet sheet;

    // Mở file Excel và lấy sheet cụ thể
    public static void setExcelFile(String excelFilePath, String sheetName) throws IOException {
        // Đọc file Excel
        FileInputStream fis = new FileInputStream(new File(excelFilePath));
        workbook = new XSSFWorkbook(fis);
        sheet = workbook.getSheet(sheetName);
        fis.close(); // Đóng workbook đầu vào
    }

    // Lấy dữ liệu từ ô chỉ định và xử lý các kiểu dữ liệu khác nhau
    public static String getCellData(int rowNum, int colNum) {
        Row row = sheet.getRow(rowNum);
        Cell cell = row.getCell(colNum);

        if (cell == null) {
            return "";
        }
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                } else {
                    double numericValue = cell.getNumericCellValue();
                    // Kiểm tra nếu giá trị là số nguyên
                    if (numericValue == (int) numericValue) {
                        return String.valueOf((int) numericValue);
                    } else {
                        return String.valueOf(numericValue);
                    }
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            case BLANK:
                return "";
            default:
                return "UNKNOWN TYPE";
        }
    }

    // Ghi dữ liệu vào ô chỉ định và lưu file
    public static void setCellData(String excelFilePath, String value, int rowNum, int colNum) throws IOException {
        Row row = sheet.getRow(rowNum);
        Cell cell = row.getCell(colNum);

        if (cell == null) {
            cell = row.createCell(colNum);
        }
        cell.setCellValue(value);

        FileOutputStream fos = new FileOutputStream(new File(excelFilePath));
        workbook.write(fos);
        fos.close();
    }

    // Trả về số lượng hàng trong sheet
    public static int getRowCount() {
        return sheet.getPhysicalNumberOfRows();
    }

    // Đóng workbook
    public static void closeWorkbook() throws IOException {
        if (workbook != null) {
            workbook.close();
        }
    }
}
