import java.io.File;

/**
 * 文件重命名工具类
 * 将指定路径下所有包含"ruoyi"的文件或文件夹名称替换为"szzx"
 */
public class FileRenameUtil {

    public static void main(String[] args) {
        // 替换为实际需要处理的目录路径
        String rootPath = "D:\\Users\\Peng.ZHA\\IdeaProjects\\infra-sb3";
        File rootDir = new File(rootPath);

        if (!rootDir.exists() || !rootDir.isDirectory()) {
            System.out.println("指定的路径不存在或不是一个目录: " + rootPath);
            return;
        }

        // 开始递归重命名
        renameFiles(rootDir);
        System.out.println("重命名操作完成");
    }

    /**
     * 递归重命名目录下的所有文件和子目录
     * @param file 要处理的文件或目录
     */
    private static void renameFiles(File file) {
        if (file == null || !file.exists()) {
            return;
        }

        // 如果是目录，先处理子文件和子目录
        if (file.isDirectory()) {
            File[] children = file.listFiles();
            if (children != null) {
                for (File child : children) {
                    renameFiles(child);
                }
            }
        }

        // 重命名当前文件或目录
        renameSingleFile(file);
    }

    /**
     * 重命名单个文件或目录
     * @param file 要重命名的文件或目录
     */
    private static void renameSingleFile(File file) {
        String originalName = file.getName();
        // 检查名称中是否包含"ruoyi"（不区分大小写）
        if (originalName.contains("ruoyi")||originalName.contains("RuoYi")) {
            String newName = originalName.replace("ruoyi", "szzx");
            newName = newName.replace("RuoYi", "Szzx");
            File parentDir = file.getParentFile();
            File newFile = new File(parentDir, newName);

            if (file.renameTo(newFile)) {
                System.out.println("已重命名: " + file.getAbsolutePath() + " -> " + newFile.getAbsolutePath());
            } else {
                System.err.println("重命名失败: " + file.getAbsolutePath());
            }
        }
    }
}

