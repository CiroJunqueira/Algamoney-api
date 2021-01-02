package com.algamoneyapi.service;

import java.time.LocalDate;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.algamoneyapi.model.Lancamento;
import com.algamoneyapi.model.Pessoa;
import com.algamoneyapi.repository.LancamentoRepository;
import com.algamoneyapi.repository.PessoaRepository;
import com.algamoneyapi.service.exception.PessoaInexistenteOuInativaException;

@Service
public class LancamentoService {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private LancamentoRepository lancamentoRepository;

	public Lancamento salvar(@Valid Lancamento lancamento) {
		Pessoa pessoa = pessoaRepository.findById(lancamento.getPessoa().getCodigo()).orElse(null);
		if (pessoa == null || !pessoa.isAtivo()) {
			throw new PessoaInexistenteOuInativaException();
		}
			
		
		
		return lancamentoRepository.save(lancamento);
	}
	
	public Page<Lancamento> findAll(Pageable pageable, LocalDate dataVencimento){
		return lancamentoRepository.findByDataVencimento(dataVencimento, pageable);
	}

}
