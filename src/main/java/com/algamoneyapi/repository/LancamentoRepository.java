package com.algamoneyapi.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.algamoneyapi.model.Lancamento;
import com.algamoneyapi.repository.filter.LancamentoFilter;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {
	
	Page<Lancamento> findByDataVencimento(LocalDate dataVencimento, Pageable pageable);

	List<Lancamento> filtrar(LancamentoFilter lancamentoFilter);
	
	
}
