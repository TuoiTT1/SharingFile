package training.java.sharing_file.service;

import org.springframework.stereotype.Service;

import training.java.sharing_file.dto.DocumentDTO;
import training.java.sharing_file.model.User;

@Service
public interface DocumentService {

	DocumentDTO insert (String localPath, User user, DocumentDTO dto);
}
