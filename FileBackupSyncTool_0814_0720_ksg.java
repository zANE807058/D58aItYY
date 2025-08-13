// 代码生成时间: 2025-08-14 07:20:05
import java.io.*;
import java.nio.file.*;
import java.util.*;

// 定义一个文件备份和同步工具类
public class FileBackupSyncTool {

    // 源文件夹路径
    private Path sourcePath;
    // 目标文件夹路径
    private Path targetPath;
    // 备份文件的后缀名
    private Set<String> backupSuffixes;

    // 构造函数，初始化源路径、目标路径和备份文件后缀名
    public FileBackupSyncTool(Path sourcePath, Path targetPath, Set<String> backupSuffixes) {
        this.sourcePath = sourcePath;
        this.targetPath = targetPath;
        this.backupSuffixes = backupSuffixes;
    }

    // 执行文件备份和同步
    public void backupAndSync() throws IOException {
        // 检查源路径和目标路径是否存在
        if (!Files.exists(sourcePath) || !Files.exists(targetPath)) {
            throw new IOException(