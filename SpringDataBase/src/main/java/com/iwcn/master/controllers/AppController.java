package com.iwcn.master.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.iwcn.master.configuration.DatabaseLoader;
import com.iwcn.master.entities.Producto;


@Controller
public class AppController {
    
	@Autowired
	private DatabaseLoader databaseLoader;

    @RequestMapping("/")
    public ModelAndView Inicio() {
        return new ModelAndView("Inicio");
    }
    
    @RequestMapping("/ListaDeProductos")
    public ModelAndView listaDeProductos() {
        return new ModelAndView("ListadoDeProductos_template").addObject("ListaProductos", this.databaseLoader.getAll());
    }
    
    @RequestMapping("/AñadirProducto")
    public ModelAndView añadirProducto(@RequestParam String pname,@RequestParam int ref,@RequestParam int price,@RequestParam int stock) {
    	this.databaseLoader.addProduct(pname,ref,price,stock);
        return new ModelAndView("ListadoDeProductos_template").addObject("ListaProductos", this.databaseLoader.getAll());
    }

    @RequestMapping("/Formulario")
    public ModelAndView formulario() {
        return new ModelAndView("Formulario_template");
    }
    
    @RequestMapping("/BorrarProducto/{id}")
    public ModelAndView borrarProducto(@PathVariable String id) {
    	int index = Integer.parseInt(id);
    	this.databaseLoader.removeProduct(index);
        return new ModelAndView("ListadoDeProductos_template").addObject("ListaProductos", this.databaseLoader.getAll());
    }
    
    @RequestMapping("/MostrarProducto/{id}")
    public ModelAndView mostrarProducto(@PathVariable String id) {
    	int index = Integer.parseInt(id);
    	Producto newProd = this.databaseLoader.getProduct(index);
    	return new ModelAndView("Detalles").addObject("product",newProd);
    }
    
    @RequestMapping("/EditarProducto/{id}")
    public ModelAndView editProduct(@PathVariable String id)
    {
    	int index = Integer.parseInt(id);
    	return new ModelAndView("Formulario2_template")
        		.addObject("producto",this.databaseLoader.getProduct(index))
        		.addObject("productoId", index);
    }
    
    @RequestMapping("/EditarExProducto")
    public ModelAndView editProductEx(@RequestParam String pname,@RequestParam int ref,@RequestParam int price,
    		@RequestParam int stock,@RequestParam int id){
       	this.databaseLoader.editProduct(pname,ref,price,stock,id);
    	return new ModelAndView("ListadoDeProductos_template").addObject("ListaProductos", this.databaseLoader.getAll());
    }
    
}
