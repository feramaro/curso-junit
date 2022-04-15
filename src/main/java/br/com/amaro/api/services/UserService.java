package br.com.amaro.api.services;

import br.com.amaro.api.domain.User;
import br.com.amaro.api.domain.dto.UserDTO;

import java.util.List;

public interface UserService {

    User findById(Integer id);

    List<User> findAll();

    User create(UserDTO userDTO);
}
