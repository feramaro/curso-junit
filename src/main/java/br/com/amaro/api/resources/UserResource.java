package br.com.amaro.api.resources;

import br.com.amaro.api.domain.dto.UserDTO;
import br.com.amaro.api.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserResource {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private UserService service;

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(mapper.map(service.findById(id), UserDTO.class));
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll() {
        return ResponseEntity.ok().
                body(service.findAll().stream().map(user -> mapper.map(user, UserDTO.class)).toList());
    }

    @PostMapping
    public ResponseEntity<UserDTO> create(@RequestBody UserDTO userDTO) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(service.create(userDTO).getId()).toUri() ;
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable Integer id, @RequestBody UserDTO userDTO) {
        userDTO.setId(id);
        return ResponseEntity.ok().body(mapper.map(service.update(userDTO), UserDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserDTO> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
