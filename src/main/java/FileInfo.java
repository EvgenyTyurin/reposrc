import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import java.util.Objects;

public class FileInfo {

    private final String fullStr;
    private String name;
    private String extension;
    private String directory;

    public FileInfo(String fullStr) {
        this.fullStr = fullStr;
        name = FilenameUtils.getBaseName(fullStr);
        extension = FilenameUtils.getExtension(fullStr);
        directory = FilenameUtils.getPrefix(fullStr) + FilenameUtils.getFullPath(fullStr);
    }

    @Override
    public String toString() {
        return "FileInfo{" +
                "fullStr='" + fullStr + '\'' +
                ", name='" + name + '\'' +
                ", extension='" + extension + '\'' +
                ", directory='" + directory + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileInfo fileInfo = (FileInfo) o;
        return Objects.equals(name, fileInfo.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
