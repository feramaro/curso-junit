package br.com.amaro.api.resources;

import br.com.amaro.api.domain.User;
import br.com.amaro.api.domain.dto.UserDTO;
import br.com.amaro.api.services.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserResourceTest {


    public static final Integer ID = 1;
    public static final String NAME = "Luis Amaro";
    public static final String EMAIL = "teste@teste.com";
    public static final String PASSWORD = "teste123";


    @InjectMocks
    private UserResource resource;

    @Mock
    private UserServiceImpl userService;
    @Mock
    private ModelMapper mapper;

    private User user;
    private UserDTO userDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void findById() {
    }

    @Test
    void findAll() {
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    private void startUser() {
        user = new User(ID, NAME, EMAIL, PASSWORD);
        userDTO = new UserDTO(ID, NAME, EMAIL, PASSWORD);
    }
}