package training.java.sharing_file.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.xml.ws.spi.http.HttpHandler;

import org.apache.commons.io.FilenameUtils;
import org.apache.tomcat.util.file.ConfigurationSource.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import training.java.sharing_file.dto.DocumentDTO;
import training.java.sharing_file.model.User;
import training.java.sharing_file.repository.UserRepository;
import training.java.sharing_file.service.DocumentService;
import training.java.sharing_file.service.request.UploadDocumentRequest;
import training.java.sharing_file.service.response.MessageResponse;
import training.java.sharing_file.utils.Constants;
import training.java.sharing_file.utils.Util;

@RestController
@RequestMapping(value = "/document")
public class DocumentController {

	@Autowired
	private DocumentService documentService;
	
	@Autowired
	private UserRepository userRepository;

	public static final Logger logger = LoggerFactory.getLogger(DocumentController.class);

	@PostMapping(value = "/upload")
	public ResponseEntity<?> uploadDocument(@Validated @RequestBody UploadDocumentRequest uploadInfo) {
		try {
			User user = userRepository
					.findByUserName(Util.getCurrentUserName()).get();
			
			DocumentDTO dto = new DocumentDTO();
			File fileOrign = new File(uploadInfo.getPathFileLocal());
			if (!fileOrign.exists()) {
				return ResponseEntity.badRequest().body(new MessageResponse(Constants.FILE_NOT_FOUND));
			}
			dto.setFileName(FilenameUtils.getBaseName(fileOrign.getName()));
			dto.setExtension(FilenameUtils.getExtension(fileOrign.getName()));
			dto.setSize((int) Files.size(fileOrign.toPath()));
			dto.setTitle(uploadInfo.getTitle());
			dto.setDescription(uploadInfo.getDescription());
			dto.setIs_Active(Constants.IS_ACTIVE);
			
			
			File fileDoc = new File(Paths.get(Constants.PATH_FOLDER,
					user.getEmployee().getGroup().getCode(),
					dto.getFileName() + "." + dto.getExtension()).toString());
			
			if(fileDoc.exists()) {
				logger.error(Constants.FILE_ALREADY_EXISTS + ": " + dto.getFileName());
				return ResponseEntity.badRequest().body(new MessageResponse(Constants.FILE_ALREADY_EXISTS));
			}
			
			documentService.insert(uploadInfo.getPathFileLocal(), user, dto);
			
			return ResponseEntity.ok(new MessageResponse(Constants.UPLOAD_SUCCESS));
		} catch (Exception e) {
			logger.error(Constants.UPLOAD_FAIL + e.getMessage());
			e.printStackTrace();
			return ResponseEntity.badRequest().body(new MessageResponse(Constants.UPLOAD_FAIL));
		}
	}
	
	@GetMapping(value = "/download/{groupCode}/{fileName}")
	public ResponseEntity<?> downloadDocument(@PathVariable String groupCode, @PathVariable String fileName){
		try {
			Path path = Paths.get(new File(".").getCanonicalPath(),Constants.PATH_FOLDER, groupCode, fileName);
			UrlResource resource = new UrlResource(path.toUri());
			
			return ResponseEntity.ok().contentType(MediaType.parseMediaType("application/octet-stream"))
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
					.body(resource);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return ResponseEntity.badRequest().body(new MessageResponse(Constants.DOWNLOAD_FILE_FAIL));
		}
	}
}
