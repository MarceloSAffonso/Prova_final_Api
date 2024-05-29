package br.com.serratec.musica.model;

import br.com.serratec.musica.dto.MusicaDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name= "musicas")
public class Musica {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String artista;
	private String album;
	private String musica;

	public Musica() {}

	public Musica(Long id, String artista, String album, String musica) {
		super();
		this.id = id;
		this.artista = artista;
		this.album = album;
		this.musica = musica;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getArtista() {
		return artista;
	}

	public void setArtista(String artista) {
		this.artista = artista;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public String getMusica() {
		return musica;
	}

	public void setMusica(String musica) {
		this.musica = musica;
	}
	
	public MusicaDto toDto() {
		return new MusicaDto (this.id, this.artista, this.album, this.musica);
	}
	
	
}
