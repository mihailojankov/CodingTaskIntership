package codingTask.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import codingTask.model.AbstractModel;
import codingTask.repository.InheritRepository;

public abstract class AbstractService <C extends AbstractModel, R extends InheritRepository<C>>{
	
	@Autowired
	protected final R repository;
	
	public AbstractService(R repository) {
		this.repository = repository;
	}
	
	public Iterable<C> findAll(){
		return repository.findAll();
	}
	
	public C findById(Long id) {
		return repository.findById(id).orElse(null);
	}
	
	public void save(C obj) {
		repository.save(obj);
	}
	
	public void deleteById(Long id) {
		repository.deleteById(id);
	}
	
	
}
