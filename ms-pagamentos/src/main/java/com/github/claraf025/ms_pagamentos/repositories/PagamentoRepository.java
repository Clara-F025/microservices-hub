package com.github.claraf025.ms_pagamentos.repositories;

import com.github.claraf025.ms_pagamentos.entities.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
}
