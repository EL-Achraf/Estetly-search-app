package com.example.estetly_backend;

import com.example.estetly_backend.entities.Client;
import com.example.estetly_backend.reposirory.ClientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
public class EstetlyBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(EstetlyBackendApplication.class, args);
	}

	@Bean
	@Transactional
	CommandLineRunner initDatabase(ClientRepository clientRepository) {
		return args -> {
			if (clientRepository.count() == 0) {
				LocalDateTime now = LocalDateTime.now();
				List<Client> clients = new ArrayList<>();

				String[] firstNames = {"Jean", "Marie", "Pierre", "Sophie", "Thomas", "Julie", "Nicolas", "Isabelle",
						"François", "Valérie", "David", "Céline", "Alexandre", "Emilie", "Christophe",
						"Caroline", "Antoine", "Aurélie", "Sébastien", "Elodie"};

				String[] lastNames = {"Dupont", "Martin", "Durand", "Leroy", "Moreau", "Simon", "Laurent", "Michel",
						"Bernard", "Petit", "Robert", "Richard", "Roux", "Fournier", "Girard", "Lefebvre",
						"Lambert", "Bonnet", "Martinez", "Dumont"};

				String[] domains = {"gmail.com", "yahoo.com", "hotmail.com", "outlook.com", "protonmail.com"};

				Random random = new Random();

				for (int i = 0; i < 20; i++) {
					String firstName = firstNames[i];
					String lastName = lastNames[i];
					String email = firstName.toLowerCase() + "." + lastName.toLowerCase() + "@" + domains[random.nextInt(domains.length)];

					// Génère une date aléatoire dans les 30 derniers jours
					LocalDateTime randomDate = now.minusDays(random.nextInt(30))
							.minusHours(random.nextInt(24))
							.minusMinutes(random.nextInt(60));

					Client client = new Client();
					client.setFirstName(firstName);
					client.setLastName(lastName);
					client.setEmail(email);
					clients.add(client);
				}

				clientRepository.saveAll(clients);
				System.out.println("Database initialized with 20 sample clients at " + now);
			}
		};
	}
}