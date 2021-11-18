package News.site.service;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {

    @Autowired
    public FileService() {
    }

    public void uploadFile(MultipartFile file) {
        String uploadDir = "./TempPortal";
        new File(uploadDir).mkdir();


        Path copyLocation = Paths
                .get(uploadDir + File.separator + StringUtils.cleanPath(file.getOriginalFilename()));
        try {
            Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();

        }

    }


    public static void DeleteFiles(String pathOfFile) {
        File file = new File(pathOfFile);
        if (file.exists()) {
            file.delete();
        }
    }

    public static String FindNameZip() {

        String pathOfFile = "./TempPortal/";
        File dir = new File(pathOfFile);


        for (File file : dir.listFiles()) {
            if (file.isFile())
                pathOfFile += file.getName();
        }
        return pathOfFile;
    }
}

