// 代码生成时间: 2025-07-31 18:27:04
package com.imageresizer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
# TODO: 优化性能
import java.io.IOException;
# 增强安全性
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 图片尺寸批量调整器
 * 该类提供了批量调整图片尺寸的功能。
 */
public class ImageResizer {

    // 目标尺寸常量
    private static final int WIDTH = 1024;
    private static final int HEIGHT = 768;

    /**
     * 调整一个文件夹内所有图片的尺寸
     * 
     * @param directoryPath 要处理的文件夹路径
     * @param targetDirectoryPath 保存调整后图片的目标文件夹路径
     * @throws IOException 如果发生I/O错误
     */
    public void resizeImagesInDirectory(String directoryPath, String targetDirectoryPath) throws IOException {
        File directory = new File(directoryPath);
        File[] files = directory.listFiles((dir, name) -> name.toLowerCase().endsWith(".jpg") || name.toLowerCase().endsWith(".png"));

        if (files == null) {
            throw new IOException("The specified directory is not valid or cannot be read.");
        }

        Arrays.stream(files)
                .map(file -> resizeImage(file, targetDirectoryPath))
                .collect(Collectors.toList());
    }

    /**
     * 调整单个图片的尺寸，并保存到目标文件夹
     * 
     * @param file 要调整的图片文件
     * @param targetDirectoryPath 目标文件夹路径
# 添加错误处理
     * @return 调整后的图片文件的路径
# 增强安全性
     * @throws IOException 如果发生I/O错误
# 添加错误处理
     */
    private String resizeImage(File file, String targetDirectoryPath) throws IOException {
        BufferedImage originalImage = ImageIO.read(file);
        BufferedImage resizedImage = new BufferedImage(WIDTH, HEIGHT, originalImage.getType());
        resizedImage.getGraphics().drawImage(originalImage.getScaledInstance(WIDTH, HEIGHT, BufferedImage.SCALE_SMOOTH), 0, 0, null);

        Path targetPath = Paths.get(targetDirectoryPath, file.getName());
        ImageIO.write(resizedImage, "jpg", targetPath.toFile());

        return targetPath.toString();
    }

    /**
     * 程序入口
# FIXME: 处理边界情况
     * 
# 添加错误处理
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        ImageResizer imageResizer = new ImageResizer();
# TODO: 优化性能
        try {
            String directoryPath = "path/to/source/images";
# FIXME: 处理边界情况
            String targetDirectoryPath = "path/to/target/images";
# 扩展功能模块
            imageResizer.resizeImagesInDirectory(directoryPath, targetDirectoryPath);
            System.out.println("Images have been resized and saved to the target directory.");
        } catch (IOException e) {
            e.printStackTrace();
# TODO: 优化性能
            System.out.println("An error occurred while resizing images: " + e.getMessage());
        }
    }
}
