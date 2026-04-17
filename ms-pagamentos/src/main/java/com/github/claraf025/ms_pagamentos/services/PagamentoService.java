package com.github.claraf025.ms_pagamentos.services;

import com.github.claraf025.ms_pagamentos.dto.PagamentoDTO;
import com.github.claraf025.ms_pagamentos.entities.Pagamento;
import com.github.claraf025.ms_pagamentos.entities.Status;
import com.github.claraf025.ms_pagamentos.exceptions.ResourceNotFoundException;
import com.github.claraf025.ms_pagamentos.repositories.PagamentoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository repository;

    @Transactional(readOnly = true)
    public List<PagamentoDTO> findAllPagamentos() {
        return repository.findAll().stream().map(PagamentoDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public PagamentoDTO findPagamentoById(Long id) {
        Pagamento pagamento = repository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Recurso não encontrado. ID: "+id));

        return new PagamentoDTO(pagamento);
    }

    @Transactional
    public PagamentoDTO savePagamento(PagamentoDTO pagamentoDTO){
        Pagamento pagamento = new Pagamento();
        mapperDtoToPagamento(pagamentoDTO, pagamento);
        pagamento.setStatus(Status.CRIADO);
        pagamento = repository.save(pagamento);
        return new PagamentoDTO(pagamento);
    }

    @Transactional
    public PagamentoDTO updatePagamento(Long id, PagamentoDTO dto){
        try {
            Pagamento pagamento = repository.getReferenceById(id);
            mapperDtoToPagamento(dto, pagamento);
            pagamento.setStatus(dto.getStatus());
            pagamento = repository.save(pagamento);
            return new PagamentoDTO(pagamento);
        }catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Recurso não encontrado, ID:"+id);
        }
    }

    private void mapperDtoToPagamento(PagamentoDTO pagamentoDTO, Pagamento pagamento) {
        pagamento.setValor(pagamentoDTO.getValor());
        pagamento.setNome(pagamentoDTO.getNome());
        pagamento.setNumeroCartao(pagamentoDTO.getNumeroCartao());
        pagamento.setValidade(pagamentoDTO.getValidade());
        pagamento.setCodigoSeguranca(pagamentoDTO.getCodigoSeguranca());
        pagamento.setPedidoId(pagamentoDTO.getPedidoId());
    }

    @Transactional
    public void deletePagamentoById(Long id){

        if(!repository.existsById(id)){
            throw new ResourceNotFoundException("Recurso não encontrado, ID:"+id);
        }

        repository.deleteById(id);
    }
}

