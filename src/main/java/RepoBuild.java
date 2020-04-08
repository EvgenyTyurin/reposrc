import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RepoBuild {

    static String PATH = "D:\\projects\\parsec_repo\\";
    static String PATH_AUTO = "D:\\projects\\repo_auto\\";
    static List<FileInfo> source_files = new ArrayList<>();

    public static void main(String[] args) throws IOException, InterruptedException {


        /*
        FileUtils.copyDirectoryToDirectory(new File("D:\\temp\\repo\\bibl1"),
                new File("D:\\temp\\repo_auto"));

        */
        /* Java 8 shit
        try (Stream<Path> paths = Files.walk(Paths.get("D:\\projects\\parsec_repo\\"))) {
            paths
                .filter(Files::isRegularFile)
                .map(Objects::toString)
                .filter(s -> s.contains("-sources.jar"))
                .filter(s -> !s.contains(".jar."))
                .filter(s -> !s.contains("groovy-all-2.1.3-sources.jar"))
                    .filter(s -> !s.contains("groovy-all-2.1.7-sources.jar"))
                    .filter(s -> !s.contains("groovy-all-2.1.7-indy-sources.jar"))
                .forEach(s -> source_files.add(new FileInfo(s)));
        }
        }
        */

        File[] fileList = new File(PATH).listFiles();
        if (fileList != null)
            getFiles(fileList);

        boolean stop = true;
        BufferedWriter writer = new BufferedWriter(
                new FileWriter("report.csv", true));
        try {
            PrintWriter out = new PrintWriter(writer);
            for (FileInfo fileInfo : source_files) {
                if (stop) {
                    if (fileInfo.getFullName().equals("testng-6.8.8-sources.jar")) {
                        stop = false;
                    }
                    continue;
                }
                boolean buildResult =
                        build(fileInfo.getDirectory());
                out.println(fileInfo.getFullName() + ";" + buildResult);
                out.flush();
                if (buildResult) {
                    FileUtils.copyFileToDirectory(new File("build.cmd"), new File(fileInfo.getDirectory()));
                    FileUtils.copyDirectory(new File(fileInfo.getDirectory()),
                            new File("C:\\" + fileInfo.getDirNonDrive()));
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

    }

    public static void getFiles(File[] fileList) {
        for (File file : fileList) {
            if (file.isDirectory()) {
                getFiles(file.listFiles());
            } else {
                String fileName = file.toString();
                if (fileName.contains("-sources.jar") && !fileName.contains(".jar.")) {
                    System.out.println("File: " + fileName);
                    source_files.add(new FileInfo(fileName));
                }
            }
        }
    }

    private static boolean build(String dir) throws IOException, InterruptedException {
        List<String> buildResult =
                CmdExec.exec("compile1.bat " + dir);
        for (String str : buildResult) {
            if (str.contains("BUILD FAILURE")) {
                return false;
            }
            if (str.contains("BUILD SUCCESS")) {
                return true;
            }
        }
        return false;
    }

}
