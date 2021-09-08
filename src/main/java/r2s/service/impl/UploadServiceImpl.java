package r2s.service.impl;

import java.io.File;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import r2s.service.UploadService;

@Service
public class UploadServiceImpl implements UploadService {

	@Autowired
	ServletContext context;

	@Override
	public File save(MultipartFile file, String folder) {
		File dirFile = new File(context.getRealPath("/assets/" + folder));
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}
		String s = System.currentTimeMillis() + file.getOriginalFilename();
		String nameString = Integer.toHexString(s.hashCode()) + s.substring(s.lastIndexOf("."));
		try {
			File savedFile = new File(dirFile, nameString);
			file.transferTo(savedFile);
			System.out.println(savedFile.getAbsolutePath());
			return savedFile;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
