import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class RepoBuild {

    public static void main(String[] args) throws IOException, InterruptedException {
        /*
        boolean buildResult =
                build("D:\\projects\\parsec_repo2\\aopalliance\\aopalliance\\1.0");
        System.out.println("Build result " + buildResult);
        */

        List<FileInfo> files = new ArrayList<>();
        try (Stream<Path> paths = Files.walk(Paths.get("D:\\projects\\parsec_repo2\\"))) {
            paths
                .filter(Files::isRegularFile)
                .map(Objects::toString)
                .filter(s -> s.contains("-sources"))
                .forEach(s -> files.add(new FileInfo(s)));
        }
        try (PrintWriter out = new PrintWriter("report.csv")) {
            for (FileInfo fileInfo : files) {
                boolean buildResult =
                        build(fileInfo.getDirectory());
                out.println(fileInfo.getFullName() + ";" + buildResult);
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
