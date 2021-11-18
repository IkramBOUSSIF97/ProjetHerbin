package com.pixemantic.spring.interfaces;

import java.util.List;

import com.pixemantic.spring.models.Produit;



public interface IProduitService {
	


	public  List<Produit> getAll();
	

	public Produit addOrUpdateProduct(Produit p) ;
	
	public Produit updateProdById(int id,Produit p);
	
	public void deleteProdById(int id);
	
	public List<Produit> getProductByName(String p);
	
}
