package com.ecomercefarmacia.farmacia.Controller;


import com.ecomercefarmacia.farmacia.Model.ProdutosModel;
import com.ecomercefarmacia.farmacia.Repository.CategoriaRespository;
import com.ecomercefarmacia.farmacia.Repository.ProdutosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutosController {

    @Autowired
    ProdutosRepository produtosRepository;

    @Autowired
    CategoriaRespository categoriaRespository;

    @GetMapping
    public ResponseEntity<List<ProdutosModel>> getALL(){
        return ResponseEntity.ok(produtosRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutosModel>getById(@PathVariable Long id){
        return produtosRepository.findById(id)
                .map(produto ->ResponseEntity.status(HttpStatus.OK).body(produto))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/valorini/{valor}/valorfin/{valor2}")
    public ResponseEntity<List<ProdutosModel>> getbetwen(@PathVariable Double valor,@PathVariable Double valor2){
        return ResponseEntity.ok(produtosRepository.findByValorBetween(valor, valor2));
    }

    @GetMapping("/valor/{valor}")
    public ResponseEntity<List<ProdutosModel>> getByValor(@PathVariable Double valor){
        return ResponseEntity.ok(produtosRepository.findByValor(valor));
    }

    @GetMapping("/nome/{nome}/categoria/{descricao}")
    public ResponseEntity<ProdutosModel> getAnd (@PathVariable String nome,@PathVariable String descricao){
        return ResponseEntity.status(HttpStatus.OK)
                .body(produtosRepository
                        .findByNomeContainingIgnoreCaseAndDescricaoContainingIgnoreCase(nome,descricao));
    }

    @GetMapping("/encontre/{nome}/{valor}")
    public ResponseEntity<ProdutosModel> getOR(@PathVariable String nome,@PathVariable Double valor){
        return ResponseEntity.ok(produtosRepository.findByNomeContainingIgnoreCaseOrValor(nome,valor));
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<ProdutosModel>> getName(@PathVariable String nome){
        return ResponseEntity.ok(produtosRepository.findAllByNomeContainingIgnoreCase(nome));
    }

    @PostMapping
    public ResponseEntity<ProdutosModel> postProduto(@Valid @RequestBody ProdutosModel produtos){
        if(produtos.getCategorias().getId()==null)
            return ResponseEntity.badRequest().build();

        return categoriaRespository.findById(produtos.getCategorias().getId())
                .map(r-> ResponseEntity.status(HttpStatus.CREATED).body(produtosRepository
                        .save(produtos))).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping
    public ResponseEntity<ProdutosModel> putProduto (@Valid @RequestBody ProdutosModel produtos){
        if(produtos.getId()==null ||produtos.getCategorias().getId()==null)
            return ResponseEntity.badRequest().build();
        return  categoriaRespository.findById(produtos.getCategorias().getId())
                .map(id->produtosRepository.findById(produtos.getId())
                        .map(r-> ResponseEntity.ok(produtosRepository.save(produtos)))
                .orElse(ResponseEntity.notFound().build()))
                .orElse(ResponseEntity.notFound().build());

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduto(@Valid @PathVariable Long id){
        return produtosRepository.findById(id)
                .map(r-> {
                    produtosRepository.deleteById(id);
                    return ResponseEntity.noContent().build();
                }).orElse(ResponseEntity.notFound().build());
    }


}
