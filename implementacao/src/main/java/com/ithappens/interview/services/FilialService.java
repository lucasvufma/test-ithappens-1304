package com.ithappens.interview.services;

import com.ithappens.interview.dtos.FilialDTO;
import com.ithappens.interview.dtos.ProdutoDTO;
import com.ithappens.interview.models.*;
import com.ithappens.interview.repositories.FilialProdutoRepository;
import com.ithappens.interview.repositories.FilialRepository;
import com.ithappens.interview.repositories.ProdutoRepository;
import jdk.jfr.StackTrace;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FilialService {

    @Autowired
    FilialRepository filialRepository;

    @Autowired
    FilialProdutoRepository filialProdutoRepository;

    @Autowired
    ProdutoRepository produtoRepository;

    public Filial findById(Integer id) {
        Optional<Filial> filial = filialRepository.findById(id);
        return filial.orElseThrow(() -> new ObjectNotFoundException(id, this.getClass().getName()));
    }

    public FilialDTO asDTO(Filial filial) {
        Integer filialId = filial.getId();
        Set<ProdutoDTO> produtos = filial.getProdutos().stream().map(
                oldProd -> {
                    Optional<FilialProduto> filialProduto =
                            filialProdutoRepository.findByFilialIdAndProdutoId(filialId, oldProd.getProduto().getId());

                    return new ProdutoDTO(oldProd.getProduto().getId(), oldProd.getProduto().getNome(),
                            oldProd.getProduto().getDescricao(), oldProd.getProduto().getCodigoDeBarras(),
                            oldProd.getProduto().getSequencial(), oldProd.getProduto().getCusto(), filialProduto.get().getQuantidadeEstoque());
                }
        ).collect(Collectors.toSet());

        return FilialDTO.builder().id(filial.getId()).nome(filial.getNome()).produtos(produtos).build();

    }

    public List<Filial> findAll() {
        return filialRepository.findAll();
    }
}
