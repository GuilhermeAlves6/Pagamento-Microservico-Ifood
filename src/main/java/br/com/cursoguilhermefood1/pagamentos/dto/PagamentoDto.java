package br.com.cursoguilhermefood1.pagamentos.dto;

import br.com.cursoguilhermefood1.pagamentos.model.Status;

import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Getter
@Setter
public class PagamentoDto {
    private Long id;
    private BigDecimal valor;
    private String nome;
    private String numero;
    private String expiração;
    private String codigo;
    private Status status;
    private Long pedidoId;
    private Long formaDePagamento;
}