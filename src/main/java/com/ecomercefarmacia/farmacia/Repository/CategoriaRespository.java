package com.ecomercefarmacia.farmacia.Repository;

import com.ecomercefarmacia.farmacia.Model.CategoriasModel;
import com.ecomercefarmacia.farmacia.Model.ProdutosModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRespository extends JpaRepository<CategoriasModel,Long> {

    public List<CategoriasModel> findAllByNomeContainingIgnoreCase (@Param("nome") String nome);


}
