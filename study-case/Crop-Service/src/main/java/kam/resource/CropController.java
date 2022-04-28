package kam.resource;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import kam.model.Crop;
import kam.repository.CropRepository;



@RestController
//@RequestMapping("/api")
public class CropController {

	@Autowired
	private CropRepository cropRepo;
	
	@PostMapping("/findcrop")
	public String addcrop(@RequestBody Crop crop) {
		cropRepo.save(crop);
		return "Added crop with id " + crop.getId();
	}

	@GetMapping("/findcrop")
	public List<Crop> getcrop() {
		return cropRepo.findAll();
	}

	@GetMapping("/findcrop/{id}")
	public Optional<Crop> getcrop(@PathVariable String id) {
		return cropRepo.findById(id);
	}
	@PutMapping("/findcrop/{id}")
	public Crop updateCrop(@RequestBody Crop crop, @PathVariable("id") String id) {
		//farmercustom.updateFarmer(id, crop);
         return cropRepo.save(crop);
		
	}

	@DeleteMapping("/findcrop/{id}")
	public String deletecrop(@PathVariable String id) {
		cropRepo.deleteById(id);
		return "crop deleted havin id " + id;
	}

}
