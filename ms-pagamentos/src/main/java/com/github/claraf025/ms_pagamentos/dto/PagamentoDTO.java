package com.github.claraf025.ms_pagamentos.dto;

import com.github.claraf025.ms_pagamentos.entities.Pagamento;
import com.github.claraf025.ms_pagamentos.entities.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PagamentoDTO {


    private Long id;
    @NotNull(message = "O campo valor é obrigatório")
    @Positive(message = "O campo valor deve ser maior que zero")
    private BigDecimal valor;
    @NotBlank(message = "o campo nome é obrigatório")
    @Size(min = 3, max = 50, message = "o nome deve ter entre 3 e 50 caracteres")
    private String nome;
    @NotBlank(message = "o campo número do cartão é obrigatório")
    @Size(min = 16, max = 16, message = "o número do cartão deve ter 16 caracteres")
    private String numeroCartao;
    @NotBlank(message = "o campo validade é obrigatório")
    @Size(min = 5, max = 5, message = "a validade deve ter 5 caracteres")
    private String validade;
    @NotBlank(message = "o campo código de segurança é obrigatório")
    @Size(min = 3, max = 3, message = "o código de segurança deve ter 3 caracteres")
    private String codigoSeguranca;
    private Status status;
    @NotNull(message = "O campo ID do pedido é obrigatório")
    private Long pedidoId;

    public PagamentoDTO(Pagamento pagamento) {
        id = pagamento.getId();
        valor = pagamento.getValor();
        nome = pagamento.getNome();
        numeroCartao = pagamento.getNumeroCartao();
        validade = pagamento.getValidade();
        codigoSeguranca = pagamento.getCodigoSeguranca();
        status = pagamento.getStatus();
        pedidoId = pagamento.getPedidoId();
    }
}
