package com.sintad.backend.controllers;

import com.sintad.backend.dataTransferObjects.request.TipoDocumentoRequest;
import com.sintad.backend.dataTransferObjects.response.MessageResponse;
import com.sintad.backend.services.TipoDocumentoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api-sintad/tipoDocumento")
public class TipoDocumentoController {
    @Autowired
    private TipoDocumentoService tipoDocumentoService;

    @GetMapping
    public List<TipoDocumentoRequest> index() {
        return tipoDocumentoService.allTipoDocumento();
    }

    @GetMapping("{id}")
    public ResponseEntity<TipoDocumentoRequest> show(@PathVariable(name = "id") long id) {
        try {
            return ResponseEntity.ok(tipoDocumentoService.oneTipoDocumento(id));

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody TipoDocumentoRequest tipoDocumentoRequest) {
        try {
            tipoDocumentoService.createTipoDocumento(tipoDocumentoRequest);
            return ResponseEntity.ok(new MessageResponse("success", "Tipo de Documento creado exitosamente"));

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(
            @Valid @RequestBody TipoDocumentoRequest tipoDocumentoRequest,
            @PathVariable(name = "id") long id)
    {
        try {
            tipoDocumentoService.updateTipoDocumento(tipoDocumentoRequest, id);
            return ResponseEntity.ok(new MessageResponse("success", "Tipo de Documento actualizado exitosamente"));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> destroy(@PathVariable(name = "id") long id) {
        try {
            tipoDocumentoService.deleteTipoDocumento(id);
            return ResponseEntity.ok(new MessageResponse("success", "Tipo de Documento eliminado exitosamente"));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
}
