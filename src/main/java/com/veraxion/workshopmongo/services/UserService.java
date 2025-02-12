package com.veraxion.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.veraxion.workshopmongo.domain.User;
import com.veraxion.workshopmongo.dto.UserDTO;
import com.veraxion.workshopmongo.repository.UserRepository;
import com.veraxion.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;

	public List<User> findAll() {
		return repo.findAll();
	}

	public User findById(String id) {
		Optional<User> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found"));
	}
	
	public User insert(User obj) {
		return repo.insert(obj);
	}
	
	public User fromDTO(UserDTO objDto) {
		return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
	}
	
	public void delete(String id) {
		if (repo.existsById(id)) {
			repo.deleteById(id);
		}
		else {
			throw new ObjectNotFoundException(id);
		}
	}
}
