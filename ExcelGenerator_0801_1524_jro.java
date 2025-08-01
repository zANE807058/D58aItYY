// 代码生成时间: 2025-08-01 15:24:09
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

public class ExcelGenerator {

    // 根据指定的模板和数据生成Excel文件
    public static String generateExcel(String templatePath, String outputPath, Map<String, Object> dataMap) {
        try (FileInputStream fis = new FileInputStream(templatePath);
             Workbook workbook = new XSSFWorkbook(fis)) {
            
            // 获取第一个工作表
            Sheet sheet = workbook.getSheetAt(0);

            // 遍历数据映射并填充到工作表中
            for (Map.Entry<String, Object> entry : dataMap.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                // 假设模板中的数据占位符为'{key}'
                int position = sheet.getSheetName().indexOf(