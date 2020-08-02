package training.java.sharing_file.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import training.java.sharing_file.model.Action;

@Repository
public interface ActionRepository extends JpaRepository<Action, Integer>{

	Optional<Action> findByCode(String code);
}
