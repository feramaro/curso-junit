package br.com.amaro.api.resources.exceptions;

import br.com.amaro.api.services.exceptions.DataIntegratyViolationException;
import br.com.amaro.api.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ResourceExceptionHandlerTest {

    public static final String OBJECT_NOT_FOUND = "Objeto não encontrado";
    public static final String E_MAIL_JA_CADASTRADO = "EMail já cadastrado";
    @InjectMocks
    private ResourceExceptionHandler handler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenObjectNotFoundExceptionThenReturnResponseEntity() {
        ResponseEntity<StandardError> response = handler
                .objectNotFound(new ObjectNotFoundException(OBJECT_NOT_FOUND), new MockHttpServletRequest());
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(StandardError.class, response.getBody().getClass());
        assertEquals(response.getBody().getError(), OBJECT_NOT_FOUND);
    }

    @Test
    void whenDataIntegrityViolationException() {
        ResponseEntity<StandardError> response = handler
                .dateViolateException(new DataIntegratyViolationException(E_MAIL_JA_CADASTRADO),
                        new MockHttpServletRequest());
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(StandardError.class, response.getBody().getClass());
        assertEquals(response.getBody().getError(), E_MAIL_JA_CADASTRADO);
    }
}