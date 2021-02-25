package codingTask.controller;

import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import codingTask.DTO.AbstractDTO;
import codingTask.model.AbstractModel;
import codingTask.service.CommonService;

public abstract class AbstractController<C extends AbstractModel, D extends AbstractDTO, S extends CommonService<C>> {

	@Autowired
	public S service;
	
	private Class<D> dtoType;
	ModelMapper mm = new ModelMapper();
	
	public AbstractController(S service, Class<D> dtoType) {
		this.service = service;
		this.dtoType = dtoType;
	}

	@GetMapping(path = "")
	public ResponseEntity<ArrayList<D>> findAll() {
		ArrayList<D> lista = new ArrayList<D>();

		for (C x : service.findAll()) {
			lista.add(mm.map(x, dtoType));
		}
		return new ResponseEntity<ArrayList<D>>(lista, HttpStatus.OK);
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<D> findById(@PathVariable("id") Long id) {
		C single = service.findById(id);

		if (single != null) {
			D dtoSingle = mm.map(single, dtoType);
			return new ResponseEntity<D>(dtoSingle, HttpStatus.OK);
		}
		return new ResponseEntity<D>(HttpStatus.NOT_FOUND);

	}

	@PostMapping(path = "")
	public ResponseEntity<String> add(@RequestBody C body) {

		service.save(body);
		return new ResponseEntity<String>(HttpStatus.CREATED);
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<String> deleteById(@PathVariable("id") Long id) {
		service.deleteById(id);
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@PutMapping(path = "")
	public ResponseEntity<C> update(@RequestBody C body) {
		System.out.println(body.getId());
		C existing = service.findById(body.getId());
		if (existing != null) {
			existing = body;
			service.save(existing);
			return new ResponseEntity<C>(HttpStatus.OK);
		}
		return new ResponseEntity<C>(HttpStatus.NOT_FOUND);
	}

}
