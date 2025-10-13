// 代码生成时间: 2025-10-13 21:14:59
import java.io.*;
import java.util.zip.*;
# 扩展功能模块
import java.nio.file.*;
# 优化算法效率
import java.nio.file.attribute.BasicFileAttributes;
import org.apache.commons.compress.archivers.*;
import org.apache.commons.compress.archivers.zip.ZipFile;
import org.apache.commons.compress.archivers.sevenz.SevenZFile;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;
import org.apache.commons.compress.utils.IOUtils;

public class ArchiveExtractor {

    private static final String ZIP_EXTENSION = ".zip";
    private static final String SEVEN_Z_EXTENSION = ".7z";
    private static final String TAR_EXTENSION = ".tar";
    private static final String GZIP_EXTENSION = ".gz";
    private static final String BZIP2_EXTENSION = ".bz2";

    /**<ol>
     * Extracts the contents of an archive file to a specified directory.
     *
     * @param archivePath The path to the archive file.
     * @param outputDir The directory where the archive contents will be extracted.
     * @throws IOException If an I/O error occurs during extraction.
     */
    public static void extractArchive(String archivePath, String outputDir) throws IOException {
        File archiveFile = new File(archivePath);
        File outputDirectory = new File(outputDir);
        if (!outputDirectory.exists()) {
            outputDirectory.mkdirs();
        }

        String fileName = archiveFile.getName();
        if (fileName.endsWith(ZIP_EXTENSION)) {
            extractZip(archivePath, outputDir);
        } else if (fileName.endsWith(SEVEN_Z_EXTENSION)) {
            extractSevenZ(archivePath, outputDir);
        } else if (fileName.endsWith(TAR_EXTENSION) || fileName.endsWith(GZIP_EXTENSION) || fileName.endsWith(BZIP2_EXTENSION)) {
            extractTar(archivePath, outputDir);
        } else {
            throw new IOException("Unsupported archive type: " + fileName);
        }
    }

    private static void extractZip(String archivePath, String outputDir) throws IOException {
        try (ZipFile zipFile = new ZipFile(archivePath)) {
            zipFile.extractAll(outputDir);
        }
    }

    private static void extractSevenZ(String archivePath, String outputDir) throws IOException {
        try (SevenZFile sevenZFile = new SevenZFile(archivePath)) {
# 增强安全性
            sevenZFile.extractAll(outputDir);
        }
    }

    private static void extractTar(String archivePath, String outputDir) throws IOException {
        String fileName = new File(archivePath).getName();
        try (InputStream inputStream = new FileInputStream(archivePath);
             TarArchiveInputStream tarInputStream = createTarInputStream(fileName, inputStream)) {
# 扩展功能模块
            TarArchiveEntry entry;
            while ((entry = tarInputStream.getNextTarEntry()) != null) {
                File outputFile = new File(outputDir, entry.getName());
                if (entry.isDirectory()) {
                    if (!outputFile.exists()) {
                        outputFile.mkdirs();
                    }
                } else {
                    File parent = outputFile.getParentFile();
                    if (!parent.exists()) {
                        parent.mkdirs();
                    }
                    try (OutputStream outputStream = new FileOutputStream(outputFile)) {
                        IOUtils.copy(tarInputStream, outputStream);
                    }
                }
            }
# 优化算法效率
        }
# TODO: 优化性能
    }

    private static TarArchiveInputStream createTarInputStream(String fileName, InputStream inputStream) throws IOException {
        if (fileName.endsWith(GZIP_EXTENSION)) {
            return new TarArchiveInputStream(new GzipCompressorInputStream(inputStream));
# FIXME: 处理边界情况
        } else if (fileName.endsWith(BZIP2_EXTENSION)) {
# 添加错误处理
            return new TarArchiveInputStream(new BZip2CompressorInputStream(inputStream));
        } else {
            return new TarArchiveInputStream(inputStream);
        }
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Usage: ArchiveExtractor <archive-path> <output-directory>");
            System.exit(1);
# 优化算法效率
        }
        String archivePath = args[0];
        String outputDir = args[1];
        try {
            extractArchive(archivePath, outputDir);
            System.out.println("Extraction completed successfully.");
        } catch (IOException e) {
            System.err.println("Error during extraction: " + e.getMessage());
# 优化算法效率
            e.printStackTrace();
        }
    }
}
# 增强安全性