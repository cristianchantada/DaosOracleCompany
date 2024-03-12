/*package com.iesVda.classDaos;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
    	
    	RegionDao rgd = new RegionDao();
    	rgd.getAll();

        // Regiones:
        Region regionEuropa = new Region(001, "Europa");
        Region regionAmericaSur = new Region(002, "América del Sur");
        Region regionCaribe = new Region(003, "Caribe");

        // Países:
        Country paisEspanha = new Country(001, "España", regionEuropa);
        Country paisArgentina = new Country(002, "España", regionAmericaSur);
        Country paisCuba = new Country(003, "España", regionCaribe);

        // Localizaciones:
        Location localizacionChantada = new Location(
                001, "Daniel Vázquez Boo", 27500, "Chantada", "Lugo", paisEspanha
        );
        Location localizacionBarcelona = new Location(
                002, "Felip II", 80801, "Barcelona", "Barcelona", paisEspanha
        );
        Location localizacionBuenosAires = new Location(
                003, "11 de mayo", 12298, "Buenos Aires", "Río de la Plata", paisCuba
        );
        Location localizacionLaHabana = new Location(
                004, "de la Revolución", 77700, "La Habana", "Habana", paisArgentina
        );

        // Departamentos
        Department departamentoDesarrolloWeb = new Department(001, "desarrollo web", localizacionChantada, 001);
        Department departamentoAppMultiplataforma = new Department(002, "Aplicaciones multiplataforma", localizacionBarcelona, 002);
        Department departamentoCiberseguridad = new Department(003, "ciberseguridad", localizacionLaHabana, 003);
        Department departamentoBigData = new Department(004, "big data", localizacionBarcelona, 004);
        Department departamentoInteligenciaArtificial = new Department(005, "inteligencia artificial", localizacionChantada, 004);

        // Jobs
        Job trabajoManagerDepWeb = new Job(
                1, "manager del Departamento de Desarrollo web", 39000, 45000
        );
        Job trabajoManagerDepAppMultiplataforma = new Job(
                2, "manager del Departamento de Desarrollo de Aplicaciones Multiplataforma", 39000, 45000
        );
        Job trabajoManagerDepCiberseguridad = new Job(
                3, "manager del Departamento de Ciberseguridad", 42000, 52000
        );
        Job trabajoManagerDepBigdata = new Job(
                4, "manager del Departamento de Big Data", 48000, 70000
        );
        Job trabajoManagerDepInteligenciaArtificial = new Job(
                5, "manager del Departamento de Inteligencia Artificial", 48000, 70000
        );
        Job trabajoDesarrolladorWeb = new Job(
                6, "desarrollador web", 25000, 31000
        );
        Job trabajoDesarrolladorAppsMultiplataforma = new Job(
                7, "desarrollador aplicaciones multiplataforma", 25000, 31000
        );
        Job trabajoExpertoCiberseguridad = new Job(
                8, "experto en ciberseguridad", 28000, 34000
        );
        Job trabajoAnalistaBigData = new Job(
                9, "analista de big data", 28000, 38000
        );
        Job trabajoIngenieroInteligenciaArtificial = new Job(
                10, "ingeniero de inteligencia artificial", 28000, 42000
        );

        // Trabajadores
        Employee trabajadorCristian = new Employee(1, "Cristian", "Varela Casas",
                "cristianchantada@gmail.com", "622222396");
        Employee trabajadorMaksym = new Employee(2, "Maksym", "Kazantsev",
                "slava_ukraine@gmail.com", "678987123");
        Employee trabajadorIvan = new Employee(3, "Iván", "Gómez Matos",
                "listo_lobagueira@gmail.com", "621980222");
        Employee trabajadorMateo = new Employee(4, "Mateo", "del Monte Rosso",
                "guaperas_moterrosso@gmail.com", "669696969");
        Employee trabajadorPablo = new Employee(5, "Pablo", "Espárrago Navarro",
                "friki-pablo@gmail.com", "612345678");
        Employee trabajadorMiguelAngel = new Employee(6, "Miguel Ángel", "Huelva Pronto",
                "miguelangelsoy@gmail.com", "765999012");
        Employee trabajadorMichell = new Employee(7, "Sharod Michell", "Bautista Mayorga",
                "señoritadecali@gmail.com", "677788892");
        Employee trabajadorLaura = new Employee(8, "Laura", "Parlanchina Durmiente",
                "laurita_canjas@gmail.com", "612309834");
        Employee trabajadorIsrael = new Employee(9, "Israel", "Callado",
                "israel@gmail.com", "655778899");
        Employee trabajadorAlfredo = new Employee(10, "Alfredo", "García Varela",
                "bigdater@gmail.com", "699666456");

        trabajadorCristian.setHireDate(LocalDate.of(2023, 3, 14));
        trabajadorCristian.setJob(trabajoManagerDepInteligenciaArtificial);
        trabajadorCristian.setCommissionPct(1200);
        trabajadorCristian.setJobId(trabajoManagerDepInteligenciaArtificial.getJobId());
        trabajadorCristian.setDepartment(departamentoDesarrolloWeb);
        trabajadorCristian.setSalary(70000);

        System.out.println(trabajadorCristian);

        // JobHistory


    }
}*/
