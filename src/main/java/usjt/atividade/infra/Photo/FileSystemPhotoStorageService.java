package usjt.atividade.infra.Photo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

public class FileSystemPhotoStorageService implements PhotoStorageService{

    private final String storageDir;

    public FileSystemPhotoStorageService(String storageDir) {
        Path path = Paths.get(storageDir);
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                throw new RuntimeException("Não foi possível criar o diretório de storage", e);
            }
        }
        this.storageDir = storageDir;
    }

    @Override
    public String save(File photoFile) {
        try {
            String ext = getFileExtension(photoFile.getName());
            String newFileName = UUID.randomUUID().toString() + "." + ext;
            Path targetPath = Paths.get(storageDir, newFileName);
            Files.copy(photoFile.toPath(), targetPath, StandardCopyOption.REPLACE_EXISTING);
            return targetPath.toString();

        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar foto no disco", e);
        }
    }

    private String getFileExtension(String fileName) {
        int i = fileName.lastIndexOf('.');
        if (i > 0 && i < fileName.length() - 1) {
            return fileName.substring(i + 1).toLowerCase();
        }
        return "";
    }

}
