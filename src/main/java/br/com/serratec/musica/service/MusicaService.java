package br.com.serratec.musica.service;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.serratec.musica.dto.MusicaDto;
import br.com.serratec.musica.model.Musica;
import br.com.serratec.musica.repository.MusicaRepository;


@Service
public class MusicaService {

	@Autowired
	public MusicaRepository musicaRepositorio;
	
	public MusicaDto cadastrarMusica(MusicaDto musica) {
		Musica musicaEntity = musicaRepositorio.save(musica.toEntity());
		return musicaEntity.toDto();
	}
	
	public Optional<MusicaDto> listarPorId(Long id){
		Optional<Musica> musica = musicaRepositorio.findById(id);
		if(musica.isEmpty()) {
			return Optional.empty();		
		}
		return Optional.of(musica.get().toDto());
	}
	
	public List<MusicaDto> listarMusica(){
		return musicaRepositorio.findAll().stream()
				.map(m -> new MusicaDto(m.getId(),m.getArtista(),m.getAlbum(), m.getMusica())).toList();
	}
	
	public Optional<MusicaDto> atualizarMusica(Long id, MusicaDto musica){
		Musica musicaEntity = musica.toEntity();
		if(musicaRepositorio.existsById(id)) {
			musicaEntity.setId(id);
			musicaRepositorio.save(musicaEntity);
			return Optional.of(musicaEntity.toDto());
		}
		return Optional.empty();
	}
	
	public boolean excluirMusica(Long id) {
		if(!musicaRepositorio.existsById(id)) {
			return false;
		}
		musicaRepositorio.deleteById(id);
		return true;
	}
	
	public List<MusicaDto> listarMusica(String musica) {
		return musicaRepositorio.findByMusicaContainingIgnoreCase(musica)
				.stream()
				.map(m -> new MusicaDto(m.getId(), m.getArtista(), m.getAlbum(), m.getMusica())).toList();
		}
	
	public List<MusicaDto> listarArtista(String artista) {
		return musicaRepositorio.findByArtistaContainingIgnoreCase(artista)
				.stream()
				.map(n -> new MusicaDto(n.getId(), n.getArtista(), n.getAlbum(), n.getMusica())).toList();
		}
	
	public List<MusicaDto> listarAlbum(String album) {
		return musicaRepositorio.findByAlbumContainingIgnoreCase(album)
				.stream()
				.map(s -> new MusicaDto(s.getId(), s.getArtista(), s.getAlbum(), s.getMusica())).toList();
		}
	
}
