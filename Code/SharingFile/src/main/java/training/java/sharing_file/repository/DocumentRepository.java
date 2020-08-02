package training.java.sharing_file.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import training.java.sharing_file.model.Document;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Integer>{

}
