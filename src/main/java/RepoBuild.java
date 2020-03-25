import java.io.IOException;
import java.util.List;

public class RepoBuild {

    public static void main(String[] args) throws IOException, InterruptedException {
        /*
        boolean buildResult =
                build("D:\\projects\\parsec_repo2\\aopalliance\\aopalliance\\1.0");
        System.out.println("Build result " + buildResult);
        */

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
