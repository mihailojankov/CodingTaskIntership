package codingTask.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import codingTask.model.AbstractModel;

@Repository
public interface InheritRepository <C extends AbstractModel> extends CrudRepository<C, Long>{

}
