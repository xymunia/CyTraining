package com.example.api.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.model.Admin;
import com.example.service.AdminService;

@RestController    
public class AdminController {

	private final AdminService adminService;
	
	@Autowired
    public AdminController(AdminService adminService){
        this.adminService = adminService;
    }
	
	@GetMapping("/admins")//pour la liste des admins a afficher pour le root
	public Admin getAdmin(@RequestParam int id)		/////////////////////////////////////////////////que cela doit-il retourner puisque c'est une liste?
	{
		Optional<Admin> admin = adminService.getAdmin(id);
		return (Admin) admin.orElse(null);
	}
	
	@PatchMapping("/enleverCertif")
    public Admin removeAdmin(@RequestParam int id, @RequestParam int idMatiere)
    {
    	Optional<Admin> admin = adminService.removeAdmin(id, idMatiere);
        return (Admin) admin.orElse(null);
    }
}
