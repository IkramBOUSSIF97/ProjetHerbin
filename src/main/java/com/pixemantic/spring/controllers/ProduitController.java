package com.pixemantic.spring.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pixemantic.spring.implementations.ProduitServiceImpl;
import com.pixemantic.spring.models.Produit;
import com.pixemantic.spring.repository.ProduitRepository;




//http://localhost:8087
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/prod")
public class ProduitController {
	
	@Autowired
	ProduitRepository produitRepo ;
	
	@Autowired
	ProduitServiceImpl produitService;
	

	 @PostMapping("/produit")
	  public ResponseEntity<Produit> createProduit(@RequestBody Produit produit) {
	    try {
	Produit p = produitRepo.save(new Produit(produit.getNom(),produit.getPrix(),produit.getQuantite(),produit.getPhoto(),produit.getDescription()));
	      return new ResponseEntity<>(p, HttpStatus.CREATED);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

}
	  }
	 
	 @PutMapping("/updatep/{id}")
	  public ResponseEntity<Produit> updateProduit(@PathVariable("id") int id, @RequestBody Produit produit) {
	    Optional<Produit> prodData = produitRepo.findById(id);

	    if (prodData.isPresent()) {
	      Produit p = prodData.get();
	      p.setDescription(produit.getDescription());
	      p.setNom(produit.getNom());
	      p.setPhoto(produit.getPhoto());
	      p.setPrix(produit.getPrix());
	      p.setQuantite(produit.getQuantite());
	      return new ResponseEntity<>(produitRepo.save(p), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }

		@GetMapping("/retrieve-all-product")
		@ResponseBody
		public List<Produit> getAll()
		{
			List<Produit> list = produitService.getAll();
			return list;
		}
		

         /* @DeleteMapping("/delete/{id}")
	      @ResponseBody
		  public void deleteproduitById(@PathVariable("id") int id)
		  {
			  produitService.deleteProdById(id);
		  }*/
          

          @DeleteMapping("/delete/{id}")
          public ResponseEntity<HttpStatus> deleteProduit(@PathVariable("id") int id) {
            try {
              produitRepo.deleteById(id);
              return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } catch (Exception e) {
              return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
          }
		
          @DeleteMapping("/deleteAllprod")
          public ResponseEntity<HttpStatus> deleteAllProd() {
            try {
              produitRepo.deleteAll();
              return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } catch (Exception e) {
              return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

          }
          


          @GetMapping("/getProdById/{id}")
          public ResponseEntity<Produit> getProdById(@PathVariable("id") int id) {
            Optional<Produit> prodData = produitRepo.findById(id);

            if (prodData.isPresent()) {
              return new ResponseEntity<>(prodData.get(), HttpStatus.OK);
            } else {
              return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
          }
          
          //http://localhost:8087/prod/retrieve-productBy/{name}
          @GetMapping("/retrieveProduitBy/{name}")			
          public List<Produit> getProductByName(@PathVariable String name){
      	  List<Produit> p = produitService.getProductByName(name);
      	  return p;
        }
          
//          @GetMapping("/retrieveProductBy/{name}")
//          public ResponseEntity<List<Produit>> findByPublished(String name) {
//            try {
//              List<Produit> pr = produitRepo.findByNom(name);
//
//              if (pr.isEmpty()) {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//              }
//              return new ResponseEntity<>(pr, HttpStatus.OK);
//            } catch (Exception e) {
//              return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//            }
//          }


		  
		

		/*@PutMapping("/update/{id}")
		public Produit updateproduit(@PathVariable int id, @RequestBody Produit k) {
			produitService.updateProdById(id, k);
			return k;
		}*/
		
/*
		@PutMapping("/update-produit")
		@ResponseBody
		public Produit updateKindergarten(@RequestBody Produit p)
		{
		Produit pr =produitService.addOrUpdateProduct(p);
		return  pr;
	    }
	 */
}
