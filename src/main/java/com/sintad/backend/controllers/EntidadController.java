package com.sintad.backend.controllers;

import com.sintad.backend.dataTransferObjects.request.EntidadRequest;
import com.sintad.backend.dataTransferObjects.response.MessageResponse;
import com.sintad.backend.services.EntidadService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api-sintad/entidad")
public class EntidadController {
    @Autowired
    private EntidadService entidadService;

    @GetMapping
    public List<EntidadRequest> index(){
        return entidadService.allEntidad();
    }

    @GetMapping("{id}")
    public ResponseEntity<EntidadRequest> show(@PathVariable(name = "id") long id){
        try{
            return ResponseEntity.ok(entidadService.oneEntidad(id));
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody EntidadRequest entidadDTO){
        try{
            entidadService.createEntidad(entidadDTO);
            return ResponseEntity.ok(new MessageResponse("success", "Entidad creada exitosamente"));
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@Valid @RequestBody EntidadRequest entidadDTO,
                                         @PathVariable(name = "id") long id){
        try{
            entidadService.updateEntidad(entidadDTO, id);
            return ResponseEntity.ok(new MessageResponse("success","Entidad actualizada exitosamente"));
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> destroy(@PathVariable(name = "id") long id){
        try{
            entidadService.deleteEntidad(id);
            return ResponseEntity.ok(new MessageResponse("success", "Entidad eliminada exitosament"));
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
}
