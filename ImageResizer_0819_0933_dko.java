// 代码生成时间: 2025-08-19 09:33:17
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class ImageResizer {

    private static final String SOURCE_DIRECTORY = "/path/to/source/directory"; // Update with your source directory path
    private static final String DESTINATION_DIRECTORY = "/path/to/destination/directory"; // Update with your destination directory path
    private static final int NEW_WIDTH = 800; // New width for images
    private static final int NEW_HEIGHT = 600; // New height for images

    /**
     * Main method to execute the image resizing program.
     * 
# FIXME: 处理边界情况
     * @param args Command line arguments (not used in this program)
     * @throws IOException If any I/O error occurs
     */
    public static void main(String[] args) throws IOException {
        // List of image file extensions to process
        List<String> imageExtensions = Arrays.asList("jpg", "jpeg", "png", "gif");

        // Get all files from the source directory
# NOTE: 重要实现细节
        File sourceDir = new File(SOURCE_DIRECTORY);
        File[] files = sourceDir.listFiles((dir, name) -> imageExtensions.contains(name.substring(name.lastIndexOf('.') + 1).toLowerCase()));

        if (files == null) {
            throw new IOException("Cannot read directory: " + SOURCE_DIRECTORY);
        }

        // Process each file and resize the image
# 优化算法效率
        for (File file : files) {
            resizeImage(file, NEW_WIDTH, NEW_HEIGHT);
        }
    }

    /**
     * Resizes the image to the specified width and height.
# 扩展功能模块
     * 
     * @param file The image file to resize
# NOTE: 重要实现细节
     * @param newWidth The new width of the image
# 添加错误处理
     * @param newHeight The new height of the image
     * @throws IOException If any I/O error occurs
     */
# 改进用户体验
    public static void resizeImage(File file, int newWidth, int newHeight) throws IOException {
        BufferedImage originalImage = ImageIO.read(file);
# 改进用户体验
        if (originalImage == null) {
            throw new IOException("Cannot read image: " + file.getAbsolutePath());
        }

        BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, originalImage.getType());
        resizedImage.getGraphics().drawImage(originalImage, 0, 0, newWidth, newHeight, null);

        // Save the resized image to the destination directory
        String fileName = file.getName().substring(0, file.getName().lastIndexOf('.')) + "_resized" + file.getName().substring(file.getName().lastIndexOf('.'));
        Path destinationPath = Paths.get(DESTINATION_DIRECTORY, fileName);
        ImageIO.write(resizedImage, file.getName().substring(file.getName().lastIndexOf('.') + 1), destinationPath.toFile());
    }
}
