package core.util;

import core.constant.FileType;
import core.exception.D2pNotFoundException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class FileHelper {
    public static final String JAVA_EXTENSION = ".java";

    public static String getAbsolutePath(String path) throws D2pNotFoundException {
        String absolutePath;
        try {
            absolutePath = new File(path).getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
            throw new D2pNotFoundException("project path not found");
        }
        return absolutePath;
    }

    /**
     * @param parentPath absolute path
     * @return list absolute path of file or folder child
     */
    public static ArrayList<String> getAbsolutePathChilds(String parentPath) {
        ArrayList<String> childAbsolutePaths = new ArrayList<String>();
        File file = new File(parentPath);
        String[] names = file.list();
        if (names == null)
            return childAbsolutePaths;

        for (String name : names) {
            childAbsolutePaths.add(parentPath + File.separator + name);
        }
        return childAbsolutePaths;
    }


    public static FileType getFileType(String filePath) {
        if (filePath.endsWith(JAVA_EXTENSION)) {
            return FileType.JAVA_FILE;
        }
        return FileType.UNKNOWN;
    }

    public static String getAbsolutePath(String parentAbsolutePath, String childName) {
        return parentAbsolutePath + File.separator + childName;
    }
}
