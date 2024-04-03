package br.com.cursoguilhermefood1.pagamentos.controller;

import br.com.cursoguilhermefood1.pagamentos.dto.PagamentoDto;
import br.com.cursoguilhermefood1.pagamentos.service.PagamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/pagamentos")
@RequiredArgsConstructor
public class PagamentoController {
    private final PagamentoService service;

    @GetMapping
    public List<PagamentoDto> listar(){
        return service.getAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<PagamentoDto> BuscaPorID(@PathVariable @NotNull Long id){
        PagamentoDto dto = service.getById(id);

        return ResponseEntity.ok(dto);
    }
    @PostMapping
    public ResponseEntity<PagamentoDto> cadastrar (@RequestBody @Valid PagamentoDto dto,
                                                   UriComponentsBuilder uriBuilder){
        PagamentoDto pagamento = service.createPayment(dto);
        var uri = uriBuilder.path("/pagamentos/{id}").buildAndExpand(pagamento.getId()).toUri();

        return ResponseEntity.created(uri).body(pagamento);
    }
    @PutMapping("/{id}")
    public ResponseEntity<PagamentoDto> atualizarRegistro (@PathVariable @NotNull Long id,
                                                           @RequestBody @Valid PagamentoDto dto){
        PagamentoDto pagamentoAtualizado = service.updatePayment(id, dto);

        return ResponseEntity.ok(pagamentoAtualizado);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<PagamentoDto> deletarRegistro (@PathVariable @NotNull Long id){
        service.deletePayment(id);

        return ResponseEntity.noContent().build();
    }
}
