package com.github.claraf025.ms_pagamentos.services;

import com.github.claraf025.ms_pagamentos.entities.Pagamento;
import com.github.claraf025.ms_pagamentos.exceptions.ResourceNotFoundException;
import com.github.claraf025.ms_pagamentos.repositories.PagamentoRepository;
import com.github.claraf025.ms_pagamentos.tests.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PagamentoServiceTest {

    @Mock
    private PagamentoRepository pagamentoRepository;

    @InjectMocks
    private PagamentoService pagamentoService;

    private Long existingId;
    private Long nonExistingId;
    private Pagamento pagamento;

    @BeforeEach
    void setUp(){
        existingId = 1L;
        nonExistingId = Long.MAX_VALUE;
        pagamento = Factory.createPagamento();
    }

        @Test
        void deletePagamentoByIdShouldDeleteWhenIdExists(){
            Mockito.when(pagamentoRepository.existsById(existingId)).thenReturn(true);

            pagamentoService.deletePagamentoById(existingId);

            Mockito.verify(pagamentoRepository).existsById(existingId);

            Mockito.verify(pagamentoRepository, Mockito.times(1)).deleteById(existingId);
        }

    @Test
    @DisplayName("deletePagamentoByID deveria lançar ResourceNotFoundException quando o Id não existir")
    void deletePagamentoByIdShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {

        // Arrange
        Mockito.when(pagamentoRepository.existsById(nonExistingId)).thenReturn(false);

        // Act + Assert
        Assertions.assertThrows(ResourceNotFoundException.class,
                () -> {
                    pagamentoService.deletePagamentoById(nonExistingId);
                });

        // Verificações (behavior)
        Mockito.verify(pagamentoRepository).existsById(nonExistingId);
        // never() = equivalente a times(0) -> esse metodo não pode ter sido chamado nenhuma vez.
        // anyLong() é um matcher (coringa): aceita qualquer valor long/Long
        Mockito.verify(pagamentoRepository, Mockito.never()).deleteById(Mockito.anyLong());
    }




}
