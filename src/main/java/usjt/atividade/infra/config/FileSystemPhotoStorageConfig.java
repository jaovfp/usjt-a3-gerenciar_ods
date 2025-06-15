package usjt.atividade.infra.config;

import java.io.File;

public class FileSystemPhotoStorageConfig {

    private static final String IMAGE_FOLDER_NAME = "userImages";

    public static String getUserImagesFolderPath() {
        String userHome = System.getProperty("user.home");
        return userHome + File.separator + IMAGE_FOLDER_NAME;
    }

}
