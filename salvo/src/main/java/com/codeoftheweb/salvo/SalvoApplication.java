package com.codeoftheweb.salvo;

import org.aspectj.apache.bcel.util.Play;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.LinkedHashSet;

@SpringBootApplication
public class SalvoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalvoApplication.class, args);
	}

	@Bean
	public CommandLineRunner init(PlayerRepository playerRepository, GameRepository gameRepository, GamePlayerRepository gamePlayerRepository, ShipRepository shipRepository) {
		return (args) -> {

			Player p1 = new Player("Jack", "Bauer", "j.bauer@ctu.gov", "passJack");
			Player p2 = new Player("Chloe", "O'Brian", "c.obrian@ctu.gov", "passChloe");
			Player p3 = new Player("Kim", "Bauer", "kim_bauer@gmail.com", "passKim");
			Player p4 = new Player("Tony", "Almeida", "t.almeida@ctu.gov", "PassTony");
			Game g1 = new Game(LocalDateTime.now());
			Game g2 = new Game(LocalDateTime.of(2019, Month.FEBRUARY, 04, 14, 30));
			Game g3 = new Game(LocalDateTime.parse("2019-02-07T17:45:00"));
			Game g4 = new Game(LocalDateTime.parse("2019-01-02T17:45:00"));
			Game g5 = new Game(LocalDateTime.parse("2019-01-01T17:45:00"));
			Game g6 = new Game(LocalDateTime.parse("2019-02-02T17:45:00"));
			Game g7 = new Game(LocalDateTime.parse("2019-01-14T17:45:00"));
			Game g8 = new Game(LocalDateTime.parse("2019-01-30T17:45:00"));
			Ship s1 = new Ship("destroyer", Arrays.asList("H2", "H3", "H4"));
			Ship s2 = new Ship("submarine", Arrays.asList("E1", "F1", "G1"));
			Ship s3 = new Ship("patrol_boat", Arrays.asList("B4", "B5"));
			Ship s4 = new Ship("destroyer", Arrays.asList("B5", "C5", "D5"));
			Ship s5 = new Ship("patrol_boat", Arrays.asList("F1", "F2"));
			Ship s6 = new Ship("destroyer", Arrays.asList("B5", "C5", "D5"));
			Ship s7 = new Ship("patrol_boat", Arrays.asList("C6", "C7"));
			Ship s8 = new Ship("submarine", Arrays.asList("A2", "A3", "A4"));
			Ship s9 = new Ship("patrol_boat", Arrays.asList("G6", "H6"));
			Ship s10 = new Ship("destroyer", Arrays.asList("B5", "C5", "D5"));
			Ship s11 = new Ship("patrol_boat", Arrays.asList("C6", "C7"));
			Ship s12 = new Ship("submarine", Arrays.asList("A2", "A3", "A4"));
			Ship s13 = new Ship("patrol_boat", Arrays.asList("G6", "H6"));
			Ship s14 = new Ship("destroyer", Arrays.asList("B5", "C5", "D5"));
			Ship s15 = new Ship("patrol_boat", Arrays.asList("C6", "C7"));
			Ship s16 = new Ship("submarine", Arrays.asList("A2", "A3", "A4"));
			Ship s17 = new Ship("patrol_boat", Arrays.asList("G6", "H6"));
			Ship s18 = new Ship("destroyer", Arrays.asList("B5", "C5", "D5"));
			Ship s19 = new Ship("patrol_boat", Arrays.asList("C6", "C7"));
			Ship s20 = new Ship("submarine", Arrays.asList("A2", "A3", "A4"));
			Ship s21 = new Ship("patrol_boat", Arrays.asList("G6", "H6"));
			Ship s22 = new Ship("destroyer", Arrays.asList("B5", "C5", "D5"));
			Ship s23 = new Ship("patrol_boat", Arrays.asList("C6", "C7"));
			Ship s24 = new Ship("destroyer", Arrays.asList("B5", "C5", "D5"));
			Ship s25 = new Ship("patrol_boat", Arrays.asList("C6", "C7"));
			Ship s26 = new Ship("submarine", Arrays.asList("A2", "A3", "A4"));
			Ship s27 = new Ship("patrol_boat", Arrays.asList("G6", "H6"));


			GamePlayer gp1 = new GamePlayer(g1, p1);
			GamePlayer gp2 = new GamePlayer(g1, p2);
			GamePlayer gp3 = new GamePlayer(g2, p1);
			GamePlayer gp4 = new GamePlayer(g2, p2);
			GamePlayer gp5 = new GamePlayer(g3, p2);
			GamePlayer gp6 = new GamePlayer(g3, p4);
			GamePlayer gp7 = new GamePlayer(g4, p2);
			GamePlayer gp8 = new GamePlayer(g4, p1);
			GamePlayer gp9 = new GamePlayer(g5, p4);
			GamePlayer gp10 = new GamePlayer(g5, p1);
			GamePlayer gp11 = new GamePlayer(g6, p3);
			GamePlayer gp12 = new GamePlayer(g7, p4);
			GamePlayer gp13 = new GamePlayer(g8, p3);
			GamePlayer gp14 = new GamePlayer(g8, p4);
			gp1.addShip(s1);
			gp1.addShip(s2);
			gp1.addShip(s3);
			gp2.addShip(s4);
			gp2.addShip(s5);
			gp3.addShip(s6);
			gp3.addShip(s7);
			gp4.addShip(s8);
			gp4.addShip(s9);
			gp5.addShip(s10);
			gp5.addShip(s11);
			gp6.addShip(s12);
			gp6.addShip(s13);
			gp7.addShip(s14);
			gp7.addShip(s15);
			gp8.addShip(s16);
			gp8.addShip(s17);
			gp9.addShip(s18);
			gp9.addShip(s19);
			gp10.addShip(s20);
			gp10.addShip(s21);
			gp11.addShip(s22);
			gp11.addShip(s23);
			gp13.addShip(s24);
			gp13.addShip(s25);
			gp14.addShip(s26);
			gp14.addShip(s27);


			playerRepository.save(p1);
			playerRepository.save(p2);
			playerRepository.save(p3);
			playerRepository.save(p4);
			gameRepository.save(g1);
			gameRepository.save(g2);
			gameRepository.save(g3);
			gameRepository.save(g4);
			gameRepository.save(g5);
			gameRepository.save(g6);
			gameRepository.save(g7);
			gameRepository.save(g8);
			gamePlayerRepository.save(gp1);
			gamePlayerRepository.save(gp2);
			gamePlayerRepository.save(gp3);
			gamePlayerRepository.save(gp4);
			gamePlayerRepository.save(gp5);
			gamePlayerRepository.save(gp6);
			gamePlayerRepository.save(gp7);
			gamePlayerRepository.save(gp8);
			gamePlayerRepository.save(gp9);
			gamePlayerRepository.save(gp10);
			gamePlayerRepository.save(gp11);
			gamePlayerRepository.save(gp12);
			gamePlayerRepository.save(gp13);
			gamePlayerRepository.save(gp14);
			shipRepository.save(s1);
			shipRepository.save(s2);
			shipRepository.save(s3);
			shipRepository.save(s4);
			shipRepository.save(s5);
			shipRepository.save(s6);
			shipRepository.save(s7);
			shipRepository.save(s8);
			shipRepository.save(s9);
			shipRepository.save(s10);
			shipRepository.save(s11);
			shipRepository.save(s12);
			shipRepository.save(s13);
			shipRepository.save(s14);
			shipRepository.save(s15);
			shipRepository.save(s16);
			shipRepository.save(s17);
			shipRepository.save(s18);
			shipRepository.save(s19);
			shipRepository.save(s20);
			shipRepository.save(s21);
			shipRepository.save(s22);
			shipRepository.save(s23);
			shipRepository.save(s24);
			shipRepository.save(s25);
			shipRepository.save(s26);
			shipRepository.save(s27);
		};
	}

}