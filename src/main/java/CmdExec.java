import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class CmdExec {

    static public List<String> exec(String cmd) throws IOException, InterruptedException {
        System.out.println("Executing:" + cmd);
        Runtime rt = Runtime.getRuntime();
        Process pr = rt.exec(cmd);
        BufferedReader inputReader = new BufferedReader(new InputStreamReader(pr.getInputStream()));
        String inputResult;
        String result;
        while (!inputReader.ready()) {}
        List<String> outputList = new ArrayList<>();
        if (inputReader.ready()) {
            while ((inputResult = inputReader.readLine()) != null) {
                result = Charset.forName("IBM866").decode(ByteBuffer.wrap(inputResult.getBytes())).toString();
                System.out.println(result);
                outputList.add(result);
            }
        }
        pr.waitFor();
        return outputList;
    }

}
