package com.iwcn.master.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.iwcn.master.entities.Producto;
import com.iwcn.master.services.DatabaseLoader;


@Controller
public class AppController {
    
	@Autowired
	private DatabaseLoader databaseLoader;
	
	@RequestMapping("/")
	public ModelAndView index() {
		return new ModelAndView("Login");
	}

	@RequestMapping("/login")  
	public ModelAndView login() {
		return new ModelAndView("Login");
	}

    @RequestMapping("/Inicio")
    public ModelAndView Inicio() {
        return new ModelAndView("Inicio");
    }
    
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @RequestMapping("/ListaDeProductos")
    public ModelAndView listaDeProductos() {
        return new ModelAndView("ListadoDeProductos_template").addObject("ListaProductos", this.databaseLoader.getAll());
    }
    
    
    @Secured({"ROLE_ADMIN"})
    @RequestMapping("/AñadirProducto")
    public ModelAndView añadirProducto(@RequestParam String pname,@RequestParam int ref,@RequestParam int price,
    		@RequestParam int stock) {
    	this.databaseLoader.addProduct(pname,ref,price,stock);
        return new ModelAndView("ListadoDeProductos_template").addObject("ListaProductos", this.databaseLoader.getAll());
    }
    
    @Secured({"ROLE_ADMIN"})
    @RequestMapping("/Formulario")
    public ModelAndView formulario() {
        return new ModelAndView("Formulario_template");
    }
    
    @Secured({"ROLE_ADMIN"})
    @RequestMapping("/BorrarProducto/{id}")
    public ModelAndView borrarProducto(@PathVariable String id) {
    	int index = Integer.parseInt(id);
    	this.databaseLoader.removeProduct(index);
        return new ModelAndView("ListadoDeProductos_template").addObject("ListaProductos", this.databaseLoader.getAll());
    }
    
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @RequestMapping("/MostrarProducto/{id}")
    public ModelAndView mostrarProducto(@PathVariable String id) {
    	int index = Integer.parseInt(id);
    	Producto newProd = this.databaseLoader.getProduct(index);
    	return new ModelAndView("Detalles").addObject("product",newProd);
    }
    
    @Secured({"ROLE_ADMIN"})
    @RequestMapping("/EditarProducto/{id}")
    public ModelAndView editProduct(@PathVariable String id,Model model)
    {
    	int index = Integer.parseInt(id);
    	model.addAttribute(this.databaseLoader.getProduct(index));
    	return new ModelAndView("Formulario2_template");
        		
    }
    
    @Secured({"ROLE_ADMIN"})
    @RequestMapping("/EditarExProducto")
    public ModelAndView editProductEx(Producto i){
       	this.databaseLoader.editProduct(i.name,i.reference,i.price,i.stock,i.getId());
    	return new ModelAndView("ListadoDeProductos_template").addObject("ListaProductos", this.databaseLoader.getAll());
    }
    
   
}
