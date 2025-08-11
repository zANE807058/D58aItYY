// 代码生成时间: 2025-08-11 08:19:31
import java.io.*;
import java.nio.file.*;
import java.util.zip.*;

public class FileDecompressor {
    // 函数用于解压文件
    public void decompressFile(String sourceFilePath, String destinationFolderPath) throws IOException {
        // 检查源文件是否存在
        if (!Files.exists(Paths.get(sourceFilePath))) {
            throw new FileNotFoundException("Source file not found: " + sourceFilePath);
        }

        // 检查目标文件夹是否存在，不存在则创建
        Path destinationPath = Paths.get(destinationFolderPath);
        if (!Files.exists(destinationPath)) {
            Files.createDirectories(destinationPath);
        }

        try (ZipInputStream zipIn = new ZipInputStream(new FileInputStream(sourceFilePath))) {
            ZipEntry entry = zipIn.getNextEntry();
            // 遍历zip内的条目
            while (entry != null) {
                String filePath = destinationFolderPath + File.separator + entry.getName();
                if (!entry.isDirectory()) {
                    // 如果是文件，解压
                    extractFile(zipIn, filePath);
                } else {
                    // 如果是目录，创建目录
                    Files.createDirectories(Paths.get(filePath));
                }
                zipIn.closeEntry();
                entry = zipIn.getNextEntry();
            }
        }
    }

    // 提取文件
    private void extractFile(ZipInputStream zipIn, String filePath) throws IOException {
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath))) {
            byte[] bytesIn = new byte[4096];
            int read = 0;
            while ((read = zipIn.read(bytesIn)) != -1) {
                bos.write(bytesIn, 0, read);
            }
        }
    }

    public static void main(String[] args) {
        FileDecompressor decompressor = new FileDecompressor();
        try {
            String sourceFile = "path/to/your/source.zip";
            String destinationFolder = "path/to/extract/destination";
            decompressor.decompressFile(sourceFile, destinationFolder);
            System.out.println("Decompression completed successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred during decompression: " + e.getMessage());
        }
    }
}