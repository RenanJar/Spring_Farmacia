package com.ecomercefarmacia.farmacia.Controller;

import com.ecomercefarmacia.farmacia.Model.CategoriasModel;
import com.ecomercefarmacia.farmacia.Repository.CategoriaRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriasController {

    @Autowired
    CategoriaRespository categoriaRespository;

    @GetMapping
    public ResponseEntity<List<CategoriasModel>> getAll(){
        return ResponseEntity.ok(categoriaRespository.findAll());
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<CategoriasModel>> getFabric(@PathVariable String nome){
        return ResponseEntity.ok(categoriaRespository.findAllByNomeContainingIgnoreCase(nome));
    }

    @PostMapping
    public ResponseEntity<CategoriasModel> postCategoria(@RequestBody CategoriasModel categoria){
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaRespository.save(categoria));
    }

    @PutMapping
    public ResponseEntity<CategoriasModel> putCategoria(@RequestBody CategoriasModel categoria){
        if(categoria.getId()==null){
            ResponseEntity.badRequest().build();
        }
        return categoriaRespository.findById(categoria.getId())
                .map(c->ResponseEntity.status(HttpStatus.OK)
                        .body(categoriaRespository.save(categoria)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delCategoria(@PathVariable Long id){
        return categoriaRespository.findById(id)
                .map(c-> {
                    categoriaRespository.deleteById(id);
                    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
                }).orElse(ResponseEntity.notFound().build());
    }



}
