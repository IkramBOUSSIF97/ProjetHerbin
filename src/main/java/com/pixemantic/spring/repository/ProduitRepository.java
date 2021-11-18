package com.pixemantic.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pixemantic.spring.models.Produit;


@Repository
public interface ProduitRepository extends CrudRepository<Produit, Integer> {
	//public List<Produit> findByNom(String nom);
	
	 List<Produit> findByNom(String nom); 
	 
	/*@Query("SELECT c FROM KinderGarten c WHERE c.name=:name")
	public List<KinderGarten> getKindergartenByName(@Param("name")String n);*/

}
