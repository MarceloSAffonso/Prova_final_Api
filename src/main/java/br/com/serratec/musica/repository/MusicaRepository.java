package br.com.serratec.musica.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import br.com.serratec.musica.model.Musica;


public interface MusicaRepository extends JpaRepository<Musica, Long>{
	
	List<Musica> findByArtistaContainingIgnoreCase(String artista);
	List<Musica> findByAlbumContainingIgnoreCase(String album);
	List<Musica> findByMusicaContainingIgnoreCase(String musica);

}
