package br.com.serratec.musica.controller;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.musica.dto.MusicaDto;
import br.com.serratec.musica.service.MusicaService;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/musicas")
public class MusicaController {
	
	@Autowired
	private MusicaService servico;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public MusicaDto cadastrarPedidos(@Valid @RequestBody MusicaDto musica) {
		return servico.cadastrarMusica(musica);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<MusicaDto> procurarPorId(@PathVariable Long id) {
		Optional<MusicaDto> musica = servico.listarPorId(id);
		if (musica.isPresent()) {
			return ResponseEntity.ok(musica.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping
	public ResponseEntity<List<MusicaDto>> procurarTodos() {
		return ResponseEntity.ok(servico.listarMusica());
	}
	
	@GetMapping("/musica")
	public ResponseEntity<List<MusicaDto>> procurarPorMusica(@RequestBody String musica) {
		return ResponseEntity.ok(servico.listarMusica(musica));
	}
	
	@GetMapping("/artista")
	public ResponseEntity<List<MusicaDto>> procurarPorArtista(@RequestBody String artista) {
		return ResponseEntity.ok(servico.listarArtista(artista));
	}
	
	@GetMapping("/album")
	public ResponseEntity<List<MusicaDto>> procurarPorAlbum(@RequestBody String album) {
		return ResponseEntity.ok(servico.listarAlbum(album));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<MusicaDto> alterarMusicas(@PathVariable Long id, @RequestBody MusicaDto musicaAlterada) {
		Optional<MusicaDto> musica = servico.atualizarMusica(id, musicaAlterada);
		if (musica.isPresent()) {
			return ResponseEntity.ok(musica.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarMusica(@PathVariable Long id) {
		if(!servico.excluirMusica(id)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build();
	}
	

}
