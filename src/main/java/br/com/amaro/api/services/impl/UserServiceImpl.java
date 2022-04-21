package br.com.amaro.api.services.impl;

import br.com.amaro.api.domain.User;
import br.com.amaro.api.domain.dto.UserDTO;
import br.com.amaro.api.repositories.UserRepository;
import br.com.amaro.api.services.UserService;
import br.com.amaro.api.services.exceptions.DataIntegratyViolationException;
import br.com.amaro.api.services.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private ModelMapper mapper;
    @Autowired
    private UserRepository repository;
    @Override
    public User findById(Integer id) {
        Optional<User> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found"));
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public User create(UserDTO userDTO) {
        findByEmail(userDTO);
        return repository.save(mapper.map(userDTO, User.class));
    }

    @Override
    public User update(UserDTO userDTO) {
        findByEmail(userDTO);
        return repository.save(mapper.map(userDTO, User.class));
    }

    @Override
    public void delete(Integer id) {
        findById(id);
        repository.deleteById(id);
    }

    private void findByEmail(UserDTO userDTO) {
        Optional<User> user = repository.findByEmail(userDTO.getEmail());
        if(user.isPresent() && !user.get().getId().equals(userDTO.getId())) {
            throw new DataIntegratyViolationException("Email já cadastrado no sistema");
        }
    }
}
