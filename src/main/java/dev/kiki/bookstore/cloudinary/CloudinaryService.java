package dev.kiki.bookstore.cloudinary;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class CloudinaryService {

    private final Cloudinary cloudinary;

    public String uploadFile(MultipartFile file, String folder) throws IOException {
        var params = ObjectUtils.asMap(
                "public_id", folder+"/"+file.getOriginalFilename(),
                "folder", folder,
                "use_uniquename", "true"
        );
        var cloudinaryResponse = cloudinary.uploader().upload(file.getBytes(), params);
        return cloudinaryResponse.get("secure_url").toString();
    }

}
