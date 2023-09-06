package com.sintad.backend.controllers;

import com.sintad.backend.dataTransferObjects.request.TipoContribuyenteRequest;
import com.sintad.backend.dataTransferObjects.response.MessageResponse;
import com.sintad.backend.services.TipoContribuyenteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api-sintad/tipoContribuyente")
public class TipoContribuyenteController {
    @Autowired
    private TipoContribuyenteService tipoContribuyenteService;

    @GetMapping
    public List<TipoContribuyenteRequest> index(){
        return tipoContribuyenteService.allTipoContribuyente();
    }

    @GetMapping("{id}")
    public ResponseEntity<TipoContribuyenteRequest> show(@PathVariable(name = "id") long id){
        try{
            return ResponseEntity.ok(tipoContribuyenteService.oneTipoContribuyente(id));
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody TipoContribuyenteRequest tipoContribuyenteRequest){
        try{
            tipoContribuyenteService.createTipoContribuyente(tipoContribuyenteRequest);
            return ResponseEntity.ok(new MessageResponse("Tipo de Contribuyente creado exitosamente"));
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }
    @PutMapping("{id}")
    public ResponseEntity<?> update(@Valid @RequestBody TipoContribuyenteRequest tipoContribuyenteRequest,
                                         @PathVariable(name = "id") long id){
        try{
            tipoContribuyenteService.updateTipoContribuyente(tipoContribuyenteRequest,id);
            return ResponseEntity.ok(new MessageResponse("Tipo de Contribuyente actualizado exitosamente"));
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> destroy(@PathVariable(name = "id") long id){
        try{
            tipoContribuyenteService.deleteTipoContribuyente(id);
            return ResponseEntity.ok(new MessageResponse("Tipo de Contribuyente eliminado exitosamente"));
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
}
