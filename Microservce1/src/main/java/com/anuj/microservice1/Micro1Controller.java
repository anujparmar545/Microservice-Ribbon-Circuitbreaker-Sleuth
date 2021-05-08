package com.anuj.microservice1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RibbonClient(name = "micro1")
public class Micro1Controller {

	
	  @Autowired
	  RestTemplate restTemplate;

	  @Autowired
	  private StudentRepository studentRepository;
	 
	  private final Logger LOG = LoggerFactory.getLogger(getClass());
	  
	  @HystrixCommand(fallbackMethod = "getMicro2InstanceFallback")
	  @GetMapping("/getmicro2")
	  public String getMicro2Instance()
	  {
	        String url = "http://micro1/microservice2/port";
	        String port = "Currently hitting instance running on port: " + 
	                       restTemplate.getForObject(url, String.class);
	        return port;
	  }
	  
	  public String getMicro2InstanceFallback()
	     {
	          return "Microservice2 is down!!";
	     }
	  
	  
	  @RequestMapping("exception/arithmetic")
	     public String controllerForArithmeticException()
	     {
	           throw new ArithmeticException("Divide by zero error");
	     }
	    
	     @RequestMapping("exception/null-pointer")
	     public String controllerForException() throws Exception
	     {
	           throw new NullPointerException("Null reference");
	     }


	     @GetMapping("/student")
	     public String saveStudent() 
	     {
	    	 LOG.info("Saving users. Current user count is {}.", studentRepository.count());
	    	 Student shubham = new Student("101","Shubham", 2000);
	    	 Student pankaj = new Student("102","Pankaj", 29000);
	    	 Student lewis = new Student("103","Lewis", 550);

	    	 studentRepository.save(shubham);
	    	 studentRepository.save(pankaj);
	    	 studentRepository.save(lewis);
	    	    LOG.info("Done saving users. Data: {}.", studentRepository.findAll());
	    	 return "Saved";
	     }
	     
	     @Cacheable(value = "users", key = "#userId", unless = "#result.followers < 12000")
	     @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	     public Student getStudent(@PathVariable String userId) {
	       LOG.info("Getting user with ID {}.", userId);
	       return studentRepository.findById(userId.toString()).get();
	     }

	     @CachePut(value = "users", key = "#student")
	     @PutMapping("/update")
	     public Student updateStudentByID(@RequestBody Student student) {
	    	 studentRepository.save(student);
	       return student;
	     }
	     
	     @CacheEvict(value = "users", allEntries=true)
	     @DeleteMapping("/{id}")
	     public void deleteStudentByID(@PathVariable String id) {
	       LOG.info("deleting Student with id {}", id);
	       studentRepository.deleteById(id);
	     }


}
