package com.ecomercefarmacia.farmacia.Repository;

import com.ecomercefarmacia.farmacia.Model.CategoriasModel;
import com.ecomercefarmacia.farmacia.Model.ProdutosModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutosRepository extends JpaRepository<ProdutosModel,Long> {

    public List<ProdutosModel> findByValor(@Param("valor") Double valor);

    public List<ProdutosModel> findAllByNomeContainingIgnoreCase (@Param("nome") String nome);

    public List<ProdutosModel>findByValorBetween(Double inicio, Double fim);

    public ProdutosModel findByNomeContainingIgnoreCaseAndDescricaoContainingIgnoreCase(@Param("nome")String nome,@Param("cescricao") String descricao);

    public ProdutosModel findByNomeContainingIgnoreCaseOrValor(@Param("nome") String Nome,@Param("valor") Double valor);

}
