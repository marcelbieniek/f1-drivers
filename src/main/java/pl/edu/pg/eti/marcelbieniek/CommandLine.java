package pl.edu.pg.eti.marcelbieniek;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.edu.pg.eti.marcelbieniek.driver.entity.Driver;
import pl.edu.pg.eti.marcelbieniek.team.entity.Team;
import pl.edu.pg.eti.marcelbieniek.driver.service.DriverService;
import pl.edu.pg.eti.marcelbieniek.team.service.TeamService;

import java.util.Scanner;

@Component
public class CommandLine implements CommandLineRunner {

    private DriverService driverService;
    private TeamService teamService;

    @Autowired
    public CommandLine(DriverService driverService, TeamService teamService) {
        this.driverService = driverService;
        this.teamService = teamService;
    }

    @Override
    public void run(String... args) throws Exception {

        boolean quit = false;
        Scanner scanner = new Scanner(System.in);

        while(!quit) {
            String input = scanner.nextLine();

            switch (input) {
                case "quit" -> quit = true;
                case "help" -> System.out.println("Available commands: help | teams | drivers | add | delete | quit");
                case "teams" -> teamService.findAll().forEach(System.out::println);
                case "drivers" -> driverService.findAll().forEach(System.out::println);
                case "add" -> addDriver();
                case "delete" -> deleteDriver();
                default -> {
                    System.out.println("Could not recognise command!");
                    System.out.println("Available commands: help | teams | drivers | add | delete | quit");
                }
            }
        }
    }

    private void add() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Would you like to add a driver or a team?");

        boolean correct = false;
        while(!correct) {
            String select = scanner.nextLine();
            switch (select) {
                case "driver" -> {
                    addDriver();
                    correct = true;
                }
                case "team" -> {
                    addTeam();
                    correct = true;
                }
                default -> System.out.println("Incorrect command, choose whether to add a driver or a team");
            }
        }

    }

    private void addTeam() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("name: ");
        String name = scanner.nextLine();

        System.out.print("wins: ");
        int wins = scanner.nextInt();

        System.out.print("championships: ");
        int championships = scanner.nextInt();

        Team team = Team.builder()
                .name(name)
                .wins(wins)
                .championships(championships)
                .build();

        teamService.create(team);
        System.out.println("Team added!");
    }

    private void addDriver(){
        Scanner scanner = new Scanner(System.in);

        System.out.print("name: ");
        String name = scanner.nextLine();

        boolean available = false;
        String team;

        while(!available) {
            System.out.print("team: ");
            team = scanner.nextLine();

            if(teamService.find(team).isPresent()) {
                System.out.print("wins: ");
                int wins = scanner.nextInt();

                System.out.print("championships: ");
                int championships = scanner.nextInt();

                Driver driver = Driver.builder()
                        .name(name)
                        .team(teamService.find(team).orElseThrow())
                        .wins(wins)
                        .championships(championships)
                        .build();

                driverService.create(driver);
                System.out.println("Driver added!");

                available = true;
            } else {
                System.out.println("This team does not exist, choose one of these teams:");
                teamService.findAll().forEach(System.out::println);
            }
        }
    }

    private void deleteDriver() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("id: ");
        Long id = scanner.nextLong();

        boolean available = false;

        while(!available) {
            if(driverService.find(id).isPresent()) {
                driverService.delete(id);

                System.out.println("Driver deleted!");
                available = true;
            } else {
                System.out.println("This driver does not exist, choose one of these drivers:");
                driverService.findAll().forEach(System.out::println);
            }
        }
    }
}
