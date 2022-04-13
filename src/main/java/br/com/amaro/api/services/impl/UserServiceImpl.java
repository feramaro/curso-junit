package br.com.amaro.api.services.impl;

import br.com.amaro.api.domain.User;
import br.com.amaro.api.repositories.UserRepository;
import br.com.amaro.api.services.UserService;
import br.com.amaro.api.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public User findById(Integer id) {
        Optional<User> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found"));
    }
}