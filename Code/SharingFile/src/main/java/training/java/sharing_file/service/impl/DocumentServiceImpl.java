package training.java.sharing_file.service.impl;

import java.io.File;
import java.nio.file.Paths;
import java.sql.Timestamp;

import org.apache.commons.io.FileUtils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import training.java.sharing_file.dto.ActionDTO.EAction;
import training.java.sharing_file.dto.DocumentDTO;
import training.java.sharing_file.model.Action;
import training.java.sharing_file.model.ActionDocument;
import training.java.sharing_file.model.Document;
import training.java.sharing_file.model.SharingDocumentGroup;
import training.java.sharing_file.model.User;
import training.java.sharing_file.repository.ActionDocumnetRepository;
import training.java.sharing_file.repository.ActionRepository;
import training.java.sharing_file.repository.DocumentRepository;
import training.java.sharing_file.repository.SharingDocumentRepository;
import training.java.sharing_file.service.DocumentService;
import training.java.sharing_file.utils.Constants;

@Service
@Transactional(propagation = Propagation.NESTED)
public class DocumentServiceImpl implements DocumentService {

	@Autowired
	private DocumentRepository documentRepository;

	@Autowired
	private ActionRepository actionRepository;

	@Autowired
	private ActionDocumnetRepository actionDocumentRepository;
	
	@Autowired
	private SharingDocumentRepository sharingDocumentRepository;

	@Autowired
	private ModelMapper modelMapper;
	
	public static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	@Override
	public DocumentDTO insert(String localPath,User user, DocumentDTO dto) {

		try {
			File currentDirFile = new File(".");
			File folderGroup =  new File(Paths.get(currentDirFile.getCanonicalPath(), Constants.PATH_FOLDER,
					user.getEmployee().getGroup().getCode()).toString());
			if(!folderGroup.exists()) {
				folderGroup.mkdir();
			}
			
			//copy file document from localPath to path of application
			File fileOrign = new File(localPath);
			
			FileUtils.copyFileToDirectory(fileOrign, folderGroup);
			// create a action
			Action action = actionRepository.findByCode(EAction.INSERT.toString()).get();

			Document document = modelMapper.map(dto, Document.class);
			
			ActionDocument  actionDocument = new ActionDocument(user, action, document, Constants.ACTION_INSERT, new Timestamp(System.currentTimeMillis()));
		
			//create a document and create a action of document
			actionDocument = actionDocumentRepository.save(actionDocument);

			//access permission file document follow group department
			SharingDocumentGroup sharingDocumentGroup = new SharingDocumentGroup();
			sharingDocumentGroup.setDocument(actionDocument.getDocument());
			sharingDocumentGroup.setGroup(user.getEmployee().getGroup());
			
			sharingDocumentRepository.save(sharingDocumentGroup);
			
			return modelMapper.map(actionDocument.getDocument(), DocumentDTO.class);
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

}
