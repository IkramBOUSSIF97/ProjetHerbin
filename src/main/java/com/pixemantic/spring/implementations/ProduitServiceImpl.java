package com.pixemantic.spring.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pixemantic.spring.interfaces.IProduitService;
import com.pixemantic.spring.models.Produit;
import com.pixemantic.spring.repository.ProduitRepository;

@Service
public class ProduitServiceImpl implements IProduitService{
	
	@Autowired
	ProduitRepository prodRepo;

	@Override
	public List<Produit> getAll() {
		// TODO Auto-generated method stub
		return (List<Produit>) prodRepo.findAll();
	}

	@Override
	public Produit addOrUpdateProduct(Produit p) {
		Produit pr = prodRepo.save(p);
		return pr;
	}

	@Override
	public Produit updateProdById(int id, Produit p) {
		Produit pr = prodRepo.findById(id).get();
		pr.setNom(p.getNom());
		pr.setDescription(p.getDescription());
		pr.setPhoto(p.getPhoto());
		pr.setPrix(p.getPrix());
		pr.setQuantite(p.getQuantite());
		prodRepo.save(p);
		return pr;
		
	}

	@Override
	public void deleteProdById(int id) {
		prodRepo.deleteById(id);
		
	}

	@Override
	public List<Produit> getProductByName(String p) {
		// TODO Auto-generated method stub
		return prodRepo.findByNom(p);
	}

	

}
