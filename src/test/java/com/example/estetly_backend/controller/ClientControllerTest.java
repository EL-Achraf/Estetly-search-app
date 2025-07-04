package com.example.estetly_backend.controller;

import com.example.estetly_backend.entities.Client;
import com.example.estetly_backend.reposirory.ClientRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class ClientControllerTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientController clientController;

    @Test
    void searchClients_shouldReturnMatchingClients() {
        String searchKeyword = "Dupont";
        Client client1 = new Client(1L, "Jean", "Dupont", "jean.dupont@example.com");
        Client client2 = new Client(2L, "Marie", "Martin", "marie.martin@example.com");

        List<Client> expectedClients = Arrays.asList(client1);

        when(clientRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(searchKeyword, searchKeyword))
                .thenReturn(expectedClients);

        List<Client> actualClients = clientController.searchClients(searchKeyword);

        assertEquals(1, actualClients.size());
        assertEquals("Dupont", actualClients.get(0).getLastName());
    }

    @Test
    void getAllClients_shouldReturnAllClients() {
        Client client1 = new Client(1L, "Jean", "Dupont", "jean.dupont@example.com");
        Client client2 = new Client(2L, "Marie", "Martin", "marie.martin@example.com");

        List<Client> expectedClients = Arrays.asList(client1, client2);

        when(clientRepository.findAll()).thenReturn(expectedClients);

        List<Client> actualClients = clientController.getAllClients();

        assertEquals(2, actualClients.size());
    }
}