package one.digitalinnovation.personapi.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import one.digitalinnovation.personapi.dto.MessageResponseDTO;
import one.digitalinnovation.personapi.dto.PersonDTO;
import one.digitalinnovation.personapi.entity.Person;
import one.digitalinnovation.personapi.repository.PersonRepository;

import static one.digitalinnovation.personapi.utils.PersonUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {
	
	    @Mock
	    private PersonRepository personRepository;

	    @InjectMocks
	    private PersonService personService;

	    @Test
	    void testGivenPersonDTOThenReturnSavedMessage() {
	        PersonDTO personDTO = createFakeDTO();
	        Person expectedSavedPerson = createFakeEntity();

	        when(personRepository.save(any(Person.class))).thenReturn(expectedSavedPerson);

	        MessageResponseDTO expectedSuccessMessage = createExpectedMessageResponse(expectedSavedPerson.getId());
	        MessageResponseDTO succesMessage = personService.createPerson(personDTO);

	        assertEquals(expectedSuccessMessage, succesMessage);
	    }

	    private MessageResponseDTO createExpectedMessageResponse(Long id) {
	        return MessageResponseDTO
	                .builder()
	                .message("Created person with ID " + id)
	                .build();
	    }

}
