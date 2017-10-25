package com.iwcn.master.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iwcn.master.entities.Producto;
import com.iwcn.master.repositories.ProductoRepository;


@Component
public class DatabaseLoader {
	
	@Autowired
	private ProductoRepository productoRepository;
	   
        
    public void addProduct(String name,int reference,int price,int stock)
    {
    	Producto newProducto = new Producto(name,reference,price,stock);
    	productoRepository.save(newProducto);
    }
  
    public void removeProduct(int id)
    {
    	Producto newProducto = productoRepository.findById(id);
    	productoRepository.delete(newProducto.getId());
    }
  
    public Producto getProduct(int id)
    {
    	Producto newProducto = productoRepository.findById(id);
    	return newProducto;
    }
   
    public Iterable<Producto> getAll(){
    	return productoRepository.findAll();
    }
    
    public void editProduct(String name,int reference,int price,int stock,int id) {
    	Producto editProd = this.productoRepository.findById(id);
    	editProd.setName(name);
    	editProd.setReference(reference);
    	editProd.setPrice(price);
    	editProd.setStock(stock);
    	editProd.setId(id);
    	productoRepository.save(editProd);
    }
    
    /*public void editProduct(String name,int reference,int price,int stock,int id) {
    	Producto newProducto = new Producto(name,reference,price,stock,id);
    	productoRepository.save(newProducto);
    }*/


}
