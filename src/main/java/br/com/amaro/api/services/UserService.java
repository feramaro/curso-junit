package br.com.amaro.api.services;

import br.com.amaro.api.domain.User;

public interface UserService {

    User findById(Integer id);
}
