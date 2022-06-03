package com.ecomercefarmacia.farmacia.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tb_categorias")
public class CategoriasModel {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @NotBlank
        private String nome;

        @NotBlank
        private String descricao;

        @OneToMany(mappedBy = "categorias",cascade = CascadeType.REMOVE)
        @JsonIgnoreProperties("categorias")
        private List<ProdutosModel> produtos;



}
