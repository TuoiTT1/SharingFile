package training.java.sharing_file.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import training.java.sharing_file.model.SharingDocumentGroup;

@Repository
public interface SharingDocumentRepository extends JpaRepository<SharingDocumentGroup, Integer>{

}
