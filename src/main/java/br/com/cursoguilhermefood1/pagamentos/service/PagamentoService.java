package br.com.cursoguilhermefood1.pagamentos.service;

import br.com.cursoguilhermefood1.pagamentos.dto.PagamentoDto;
import br.com.cursoguilhermefood1.pagamentos.model.Pagamento;
import br.com.cursoguilhermefood1.pagamentos.model.Status;
import br.com.cursoguilhermefood1.pagamentos.repository.PagamentosRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PagamentoService{

    private final PagamentosRepository repository;

    private final ModelMapper modelMapper;

    public List<PagamentoDto> getAll(){
        return repository
                .findAll()
                .stream()
                .map(p ->modelMapper.map(p, PagamentoDto.class))
                .collect(Collectors.toList());

    }
    public PagamentoDto getById(Long id) {
        Optional<Pagamento> optinalPagamento = repository.findById(id);
        Pagamento pagamento = optinalPagamento.orElseThrow(EntityExistsException::new);
        return modelMapper.map(pagamento, PagamentoDto.class);
    }
    public PagamentoDto createPayment (PagamentoDto dto){
        Pagamento pagamento = modelMapper.map(dto, Pagamento.class);
        pagamento.setStatus(Status.CRIADO);
        repository.save(pagamento);

        return modelMapper.map(pagamento, PagamentoDto.class);
    }

    public PagamentoDto updatePayment(Long id,PagamentoDto dto){
        Pagamento pagamento = modelMapper.map(dto, Pagamento.class);
        pagamento.setId(id);
        pagamento = repository.save(pagamento);
        return modelMapper.map(pagamento, PagamentoDto.class);
    }
    public void deletePayment(long id) {
        repository.deleteById(id);
    }
}