package com.github.claraf025.ms_pagamentos.controller;

import com.github.claraf025.ms_pagamentos.dto.PagamentoDTO;
import com.github.claraf025.ms_pagamentos.services.PagamentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

    @Autowired
    private PagamentoService service;

    @GetMapping("/{id}")
    public ResponseEntity<PagamentoDTO> getById(@PathVariable Long id){
        return ResponseEntity.ok().body(service.getPagamentoById(id));
    }

    @GetMapping
    public ResponseEntity<List<PagamentoDTO>> getPagamentos(){
        return ResponseEntity.ok().body(service.getPagamentos());
    }

    @PostMapping
    private ResponseEntity<PagamentoDTO> createPagamento(@RequestBody @Valid PagamentoDTO dto){

        dto = service.savePagamento(dto);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(dto.getId())
                .toUri();

        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping("/{id}")
    private ResponseEntity<PagamentoDTO> updatePagamento (@PathVariable Long id, @Valid @RequestBody PagamentoDTO dto){
        return ResponseEntity.ok().body(service.updatePagamento(id, dto));
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Void> deletePagamento(@PathVariable Long id){
        service.deletePagamentoById(id);
        return ResponseEntity.noContent().build();
    }
}
