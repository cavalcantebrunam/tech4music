package br.com.tech4me.music.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tech4me.music.model.Musica;
import br.com.tech4me.music.service.MusicaService;
import br.com.tech4me.music.shared.MusicaDto;

@RestController
@RequestMapping("/api/musicas")
public class MusicaController {
    
    @Autowired
    private MusicaService servico;

    @GetMapping
    public ResponseEntity<List<MusicaDto>> obterMusicas(){
        return new ResponseEntity<>(servico.obterMusicas(), HttpStatus.OK);
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<MusicaDto> obterMusica(@PathVariable String id){
       Optional<MusicaDto> musica = servico.obterMusica(id);

       if(musica.isPresent()){
           return new ResponseEntity<>(musica.get(), HttpStatus.OK);
       }

       return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity<MusicaDto> criarMusica(@RequestBody MusicaDto musica){
        servico.criarMusica(musica);

        return new ResponseEntity<>(musica, HttpStatus.CREATED);
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<MusicaDto> atualizarMusica(@PathVariable String id, @RequestBody MusicaDto musica){
        servico.atualizarMusica(id, musica);

        return new ResponseEntity<>(musica, HttpStatus.OK);
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<Musica> deletarMusica(@PathVariable String id){
        servico.deletarMusica(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
