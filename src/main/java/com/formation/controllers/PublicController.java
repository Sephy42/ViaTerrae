package com.formation.controllers;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.formation.config.JwtTokenUtil;
import com.formation.dto.basketType.BasketTypeFull;
import com.formation.dto.clients.ClientToSave;
import com.formation.dto.jwt.JwtRequest;
import com.formation.dto.jwt.JwtResponse;
import com.formation.dto.picture.PictureFull;
import com.formation.dto.place.PlaceFull;
import com.formation.dto.place.PlaceLight;
import com.formation.exceptions.NotAuthorizedException;
import com.formation.persistence.entities.Client;
import com.formation.services.IBasketTypeService;
import com.formation.services.IClientService;
import com.formation.services.IPlaceService;



/**
 * @author Aelion
 * Regroupe l'ensemble des méthodes exposées via webservice ne nécessitant PAS de token d'identification
 */
/**
 * @author Aelion
 *
 */
@RestController
@RequestMapping(path = "/api/public")
@CrossOrigin
public class PublicController {

	
	/************/
	/**** atributs ***/
	/************/

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private IClientService clientService;
	
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	private IPlaceService servicePlace; 
	
	@Autowired
	private IBasketTypeService basketTypeService;
	
	/************/
	/**** methodes ***/
	/************/
	
	
	@PostMapping(path = "/authenticate")
	@CrossOrigin
	public ResponseEntity<?> authenticate(@RequestBody JwtRequest authenticationRequest) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
			final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
			final String token = jwtTokenUtil.generateToken(userDetails);
			return ResponseEntity.ok(new JwtResponse(token));
		} catch (DisabledException | BadCredentialsException e) {
			throw new NotAuthorizedException(e.getMessage());
		}		
	}
	
	
	
	/**
	 * @param cl
	 * @return the client effectively saved
	 */
	@PostMapping(path = "/client") 
	public ClientToSave save(@RequestBody ClientToSave cl) {
		
		cl.setPassword(encoder.encode(cl.getPassword()));
		if (cl.getFirstName() == null) {
			cl.setFirstName("Anonyme");
		}
		return mapper.map(clientService.save(mapper.map(cl, Client.class)), ClientToSave.class);	
	}
	
	
	/**
	 * @return list of place
	 */
	@GetMapping (path = "/places")
	public List<PlaceLight> getAll(){

		return servicePlace.findAll()
				.stream()
				.map(c -> mapper.map(c, PlaceLight.class))
				.collect(Collectors.toList()) ; 
			}
	
	
	/**
	 * @param id
	 * @return name and address of a place
	 */
	@RequestMapping(path="/places/{identifiant}", method = RequestMethod.GET )
	public PlaceFull findOne(@PathVariable(name = "identifiant") Long id) {
           return  mapper.map(servicePlace.findOne(id), PlaceFull.class);
	}

	/**
	 * @return list of basketTypes available for the current week
	 */
	@GetMapping (path = "/baskets")
	public Set<BasketTypeFull> BasketsForWeek(){
		Set<BasketTypeFull> basketSet= basketTypeService.BasketsForToday().stream()
		  .map(b -> mapper.map(b,BasketTypeFull.class))
		  .collect(Collectors.toSet());
		basketSet.stream().forEach(b -> b.setProductCount(b.getListProduct().size()));
		return basketSet;
	}
	
	@GetMapping (path = "/basket/{identifiant}/picture", produces = "image/jpeg")
	@ResponseBody
	public byte[] pictureForBasket(@PathVariable(name = "identifiant") Long id){	
		return basketTypeService.findOne(id).getPicture().getPicture();
		
	}
	
	
}
