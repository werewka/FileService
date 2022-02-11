import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

class FileService {

    private final String DEFAULT_PATH = "\"Files/myFile.txt\"";
    private StringBuffer inputBuffer = new StringBuffer();
    String requiredString = null;
    private FileInputStream inputStream;
    private BufferedReader br;
    private String line;
    private String toReplace;
    private Path path;


    void connection(String[] args){

        try {
            if (args.length > 0) {
                path = Paths.get(args[0]);
                this.inputStream = new FileInputStream(path.toString());
                this.requiredString = args[1];
                this.toReplace = args[2];
                this.br = new BufferedReader(new InputStreamReader(inputStream));
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

    }

    void connection(String requiredString, String toReplace){

        try {
            {
                path = Paths.get(DEFAULT_PATH);
                this.inputStream = new FileInputStream(path.toString());
                this.requiredString = requiredString;
                this.toReplace = toReplace;
                this.br = new BufferedReader(new InputStreamReader(inputStream));
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

    }

    int countering() {
        int counter = 0;
        int split;
        try {
            while ((line = br.readLine()) != null) {
                split = line.split(requiredString, -1).length - 1;
                counter += split;
            }
            inputStream.getChannel().position(0);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return counter;
    }

    String fileoutput() {
        try {
            line = new String(Files.readAllBytes(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return line;
    }

    @Deprecated
    void replaceLinesOLD() {

        try {
            while ((line = br.readLine()) != null) {
                if (line.equals(requiredString)) {
                    line = toReplace;
                }
                inputBuffer.append(line).append("\r\n");
            }
            inputStream.getChannel().position(0);
            FileOutputStream fileOut = new FileOutputStream(path.toString());
            fileOut.write(inputBuffer.toString().getBytes());
            fileOut.close();
        } catch (IOException e) {
            System.out.println("Something get wrong with reading/writing");
        }
    }


    void replaceLines() {

        String content;
        try {
            content = new String(Files.readAllBytes(path));

            content = content.replaceAll(requiredString, toReplace);
            Files.write(path, content.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
