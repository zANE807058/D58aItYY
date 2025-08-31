// 代码生成时间: 2025-09-01 01:07:41
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelGenerator {

    private Workbook workbook;
    private Sheet sheet;
    private String sheetName;
    private List<?> data;
    private int headerRowNum;
    private int dataRowNum;
    private CellStyle headerStyle;
    private ExcelGenerator() {
        this.workbook = new XSSFWorkbook();
        this.sheet = workbook.createSheet(sheetName);
        this.headerStyle = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        headerStyle.setFont(font);
    }

    /**
     * Creates a new ExcelGenerator instance with the specified sheet name.
     *
     * @param sheetName The name of the sheet to create in the Excel file.
     * @return A new ExcelGenerator instance.
     */
    public static ExcelGenerator create(String sheetName) {
        return new ExcelGenerator(sheetName);
    }

    private ExcelGenerator(String sheetName) {
        this.sheetName = sheetName;
    }

    /**
     * Sets the data that will be written to the Excel sheet.
     *
     * @param data The list of data to be written.
     * @return The current ExcelGenerator instance for method chaining.
     */
    public ExcelGenerator setData(List<?> data) {
        this.data = data;
        return this;
    }

    /**
     * Generates the Excel file with the provided data.
     *
     * @param fileName The name of the Excel file to generate.
     * @throws IOException If an I/O error occurs while writing the file.
     */
    public void generate(String fileName) throws IOException {
        writeDataToSheet();
        try (FileOutputStream outputStream = new FileOutputStream(fileName)) {
            workbook.write(outputStream);
        }
        workbook.close();
    }

    private void writeDataToSheet() {
        headerRowNum = 0;
        dataRowNum = 1;
        Row headerRow = sheet.createRow(headerRowNum);

        for (int i = 0; i < data.get(0).getClass().getDeclaredFields().length; i++) {
            Cell headerCell = headerRow.createCell(i);
            headerCell.setCellStyle(headerStyle);
            headerCell.setCellValue(data.get(0).getClass().getDeclaredFields()[i].getName());
        }

        for (Object rowData : data) {
            Row dataRow = sheet.createRow(dataRowNum++);
            Object[] values = rowData.getClass().getDeclaredFields();
            for (int i = 0; i < values.length; i++) {
                values[i].setAccessible(true); // Necessary to access private fields
                Cell cell = dataRow.createCell(i);
                cell.setCellValue(values[i].get(rowData).toString());
            }
        }
    }
}
