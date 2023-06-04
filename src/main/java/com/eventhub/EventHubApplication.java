package com.eventhub;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.eventhub.config.AppConstants;
import com.eventhub.entities.Category;
import com.eventhub.entities.SubCategory;
import com.eventhub.repositories.CategoryRepository;
import com.eventhub.repositories.SubCategoryRepository;

@SpringBootApplication
public class EventHubApplication implements CommandLineRunner {

	@Autowired
	CategoryRepository catRepo;
	
	@Autowired
	SubCategoryRepository subCategoryRepo;

	public static void main(String[] args) {
		SpringApplication.run(EventHubApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return modelMapper;
	}

	private void createCategories() {
		 
		 if(catRepo.count() > 0) {
			 
			 
			 System.out.println("Categories exist : skipping creation ");
			 
			 return;
			 
		 }
		 
		 try {
			 
			 Category category = new Category();
			 
			 category.setId(AppConstants.ArtsAndEntertainment);
			 category.setCategory("ArtsAndEntertainment");
			 
             Category category1 = new Category();
			 
			 category1.setId(AppConstants.Business);
			 category1.setCategory("Business");
			 
             Category category2 = new Category();
			 
			 category2.setId(AppConstants.Education);
			 category2.setCategory("Education");
			 
             Category category3 = new Category();
			 
			 category3.setId(AppConstants.Fundraising);
			 category3.setCategory("Fundraising");
			 
              Category category4 = new Category();
			 
			 category4.setId(AppConstants.Health);
			 category4.setCategory("Health");
			 
             Category category5 = new Category();
			 
			 category5.setId(AppConstants.SportsAndFitness);
			 category5.setCategory("SportsAndFitness");
			 
             Category category6 = new Category();
			 
			 category6.setId(AppConstants.Technology);
			 category6.setCategory("SportsAndFitness");
			 
			 List<Category> allCategories = List.of(category,category1,category2 ,category3,category4,category5,category6);
			 
			 this.catRepo.saveAll(allCategories);
			 
			 
		 }catch(Exception e){
				
				System.err.println("Error occurred during roles data creation: " + e.getMessage());
			}
	 }
	
	private void createSubCategories() {
		
		if(subCategoryRepo.count() > 0) {
			
			System.out.println("Sub Category exists : Skipping creation ");
			return ;
		}
		
		SubCategory sCategory = new SubCategory();
		
		sCategory.setId(AppConstants.Conferences);
		sCategory.setSubCategory("Conferences");
		
		SubCategory sCategory1 = new SubCategory();
		
		sCategory1.setId(AppConstants.Hackathons);
		sCategory1.setSubCategory("Hackathons");
		
		SubCategory sCategory2 = new SubCategory();
		
		sCategory2.setId(AppConstants.MusicConcerts);
		sCategory2.setSubCategory("MusicConcerts");
		
		SubCategory sCategory3 = new SubCategory();
		
		sCategory3.setId(AppConstants.MarathonsAndRaces);
		sCategory3.setSubCategory("MarathonsAndRaces");
		
		SubCategory sCategory4 = new SubCategory();
		
		sCategory4.setId(AppConstants.EducationWebinars);
		sCategory4.setSubCategory("EducationWebinars");
		
		SubCategory sCategory5 = new SubCategory();
		
		sCategory5.setId(AppConstants.StudentCompetitions);
		sCategory5.setSubCategory("StudentCompetitions");
		
		SubCategory sCategory6 = new SubCategory();
		
		sCategory6.setId(AppConstants.MentalHealthAwarenessEvents);
		sCategory6.setSubCategory("MentalHealthAwarenessEvents");
		
		List<SubCategory> allSubCategories = List.of(sCategory,sCategory1,sCategory2,sCategory3,sCategory4,sCategory5,sCategory6);
		
		subCategoryRepo.saveAll(allSubCategories);
		
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		createCategories();
		
		createSubCategories();

	}
}
