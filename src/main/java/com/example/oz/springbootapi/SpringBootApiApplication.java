package com.example.oz.springbootapi;

import com.example.oz.springbootapi.domain.Difficulty;
import com.example.oz.springbootapi.domain.Region;
import com.example.oz.springbootapi.domain.Tour;
import com.example.oz.springbootapi.domain.TourPackage;
import com.example.oz.springbootapi.service.TourPackageService;
import com.example.oz.springbootapi.service.TourService;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@SpringBootApplication
public class SpringBootApiApplication implements CommandLineRunner {

	@Autowired
	private TourService tourService;

	@Autowired
	private TourPackageService tourPackageService;

	@Value("${oz.importFile}")
	String importFile;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		createTourPackages();
		long tourPackageCount = tourPackageService.total();

		createTours(importFile);
		long tourCount = tourService.count();
	}

	/**
	 * Initialize known tour packages.
	 */
	private void createTourPackages() {
		tourPackageService.createTourPackage("NH", "Nature Hike");
		tourPackageService.createTourPackage("DT", "Desert Trip");
		tourPackageService.createTourPackage("SS", "Sand and Snow");
		tourPackageService.createTourPackage("RS", "Rivers and Sea");
		tourPackageService.createTourPackage("CS", "City and Suburbs");
	}

	/**
	 * Create tour objects from an external file.
	 * @param fileToImport the JSON file representing an array of tours
	 * @throws IOException failed to read the file
	 */
	private void createTours(String fileToImport) throws IOException {
		TourFromFile.readFromFile(fileToImport).forEach(importedTour -> {
			tourService.createTour(
					importedTour.getTitle(),
					importedTour.getDescription(),
					Integer.valueOf(importedTour.getPrice()),
					importedTour.getDuration(),
					Difficulty.valueOf(importedTour.getDifficulty()),
					Region.valueOf(importedTour.getRegion()),
					importedTour.getPackageType());
		});
	}

	/**
	 * Helper class to import tours from a JSON file
	 */
	private static class TourFromFile {
		private String packageType;
		private String title;
		private String description;
		private String duration;
		private String price;
		private String region;
		private String difficulty;

		/**
		 * Read in the tours from the supplied JSON file.
		 * @param fileToImport the JSON array of tour objects
		 * @return a list of all the read-in objects
		 * @throws IOException the file was not found
		 */
		static List<TourFromFile> readFromFile(String fileToImport) throws IOException {
			return new ObjectMapper()
					.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
					.readValue(new FileInputStream(fileToImport), new TypeReference<List<TourFromFile>>() {});
		}

		public String getPackageType() {
			return packageType;
		}

		public String getTitle() {
			return title;
		}

		public String getDescription() {
			return description;
		}

		public String getDuration() {
			return duration;
		}

		public String getPrice() {
			return price;
		}

		public String getRegion() {
			return region;
		}

		public String getDifficulty() {
			return difficulty;
		}
	}
}
