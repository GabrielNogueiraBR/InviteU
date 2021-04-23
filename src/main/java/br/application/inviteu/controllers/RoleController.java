package br.application.inviteu.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.application.inviteu.dto.role.*;
import br.application.inviteu.services.RoleService;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

   @Autowired
   private RoleService roleService;

   @GetMapping("/all")
   public ResponseEntity<List<RoleDTO>> getAllRoles() {
      List<RoleDTO> listRoles = roleService.getAllRoles();
      return ResponseEntity.ok(listRoles);
   }

   @GetMapping("/{id}")
   public ResponseEntity<RoleDTO> getRoleById(@PathVariable("id") Long id){
      RoleDTO roleDto = roleService.getRoleById(id);
      return ResponseEntity.ok(roleDto);
   }

   @PostMapping("/new")
   public ResponseEntity<RoleDTO> createRole(@RequestBody RoleCreateDTO createDto){
      RoleDTO roleDto = roleService.createRole(createDto);
      URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(roleDto.getId()).toUri();
      return ResponseEntity.created(uri).body(roleDto);
   }

   @PutMapping("/{id}/edit")
   public ResponseEntity<RoleDTO> updateRole(@PathVariable("id") Long id, @RequestBody RoleCreateDTO updateRoleDto){
      RoleDTO roleDto = roleService.updateRole(id, updateRoleDto);
      return ResponseEntity.ok().body(roleDto);
   }

   @DeleteMapping("/{id}/delete")
   public ResponseEntity<Void> removeRole(@PathVariable("id") Long id){
      roleService.removeRole(id);
      return ResponseEntity.noContent().build();
   }
}
