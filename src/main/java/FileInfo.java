import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import java.util.Objects;

public class FileInfo {

    private final String fullStr;
    private String name;
    private String fullName;
    private String extension;
    private String directory;

    public FileInfo(String fullStr) {
        this.fullStr = fullStr;
        name = FilenameUtils.getBaseName(fullStr);
        extension = FilenameUtils.getExtension(fullStr);
        directory = FilenameUtils.getFullPath(fullStr);
        fullName = name + "." + extension;
    }

    @Override
    public String toString() {
        return fullName;
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

    public String getName() {
        return name;
    }

    public String getExtension() {
        return extension;
    }

    public String getDirectory() {
        return directory;
    }

    public String getFullName() {
        return fullName;
    }
}
