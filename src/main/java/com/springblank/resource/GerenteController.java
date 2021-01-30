package com.springblank.resource;

import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.springblank.model.Gerente;
import com.springblank.repository.GerenteRepository;


@RestController
@RequestMapping("/gerentes")
public class GerenteController {

	@Autowired
	private GerenteRepository gerenteRepository;
	
	 
	@GetMapping("/")
	@ResponseStatus(HttpStatus.OK)
	public ModelAndView listAll() {
			
			ModelAndView modelAndView = new ModelAndView();    
			modelAndView.setViewName("gerentes");        
			modelAndView.addObject("gerentes", gerenteRepository.findAll());      
			return modelAndView;    
		    
	}
	
	@PostMapping("/add")
	public ModelAndView showAddGerenteForm() {
		ModelAndView mv= new ModelAndView();
		mv.addObject("gerente", new Gerente());
		mv.setViewName("add-gerente-form");
		return mv ;
	}
	
	@PostMapping("/add2")
	public ModelAndView addGerente2(@Validated Gerente gerente, BindingResult result, Model model,HttpServletResponse response ) {
		
		ModelAndView mv = new ModelAndView();
		if (result.hasErrors()) {
			mv= new ModelAndView("add-gerente-form");
			mv.addObject("gerente", gerente);
			return mv;
        }
		
		gerenteRepository.save(gerente);
		
		
		response.setStatus(response.SC_OK);
		mv= new ModelAndView("redirect:/gerentes/");
		//mv.addObject("books", bookRepository.findAll());
		
	    return mv;

	}
	
	
	@GetMapping("/update/{id}")
	public ModelAndView showUpdateGerenteForm(@PathVariable("id") long id, Model model, HttpServletResponse response) {
		Optional<Gerente> gerenteopt = gerenteRepository.findById(id);
		
		if  (gerenteopt.isPresent()) {
			Gerente gerente = gerenteopt.get();
			
			model.addAttribute("gerente", gerente);
			ModelAndView modelAndView = new ModelAndView();  
			modelAndView.setViewName("update-gerente-form");
			modelAndView.addObject("gerente", gerente);
			response.setStatus(response.SC_OK);
			
			return modelAndView;
		
		}else {
			
			response.setStatus(response.SC_NOT_FOUND);
			return null;
			
		}
		
		
	}
	
	
	@PostMapping("/update/{id}") 
	public  ModelAndView updateGerente(@PathVariable("id") long id, @Validated Gerente gerente, 
	  BindingResult result, Model model, HttpServletResponse response) {
	    
		if (result.hasErrors()) {
			gerente.setId(id);
	        return null;
	    }
	        
		gerenteRepository.save(gerente);
	    response.setStatus(response.SC_OK);
	    ModelAndView mv = new ModelAndView();
		mv= new ModelAndView("redirect:/gerentes/" );
		//mv.addObject("books", bookRepository.findAll());
		
	    return mv;
	    
	    
	    
	}

	
	@GetMapping("/delete/{id}")
    public ModelAndView deleteLivro(@PathVariable("id") long id, Model model, HttpServletResponse response) {
        Optional<Gerente> gerenteopt = gerenteRepository.findById(id);
        gerenteRepository.delete(gerenteopt.get());
        response.setStatus(response.SC_NO_CONTENT);
        
        ModelAndView mv = new ModelAndView();
      		mv= new ModelAndView("redirect:/gerentes/" );
      		//mv.addObject("books", bookRepository.findAll());
      		
      	return mv;
    }
	
	@GetMapping(path = "/json/{id}/", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getJSONGerente(@PathVariable("id") long id){
		//Get data from service layer into entityList.
		Gerente gerente= gerenteRepository.findById(id).get();
		
		return new ResponseEntity<Object>(gerente, HttpStatus.OK);
	}
	


}
