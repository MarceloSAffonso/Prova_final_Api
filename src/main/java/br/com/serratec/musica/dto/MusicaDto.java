package br.com.serratec.musica.dto;

import br.com.serratec.musica.model.Musica;
import jakarta.validation.constraints.NotBlank;

public record MusicaDto (
		 Long id,
		 @NotBlank(message = "Por favor, insira o nome do artista!")
		 String artista,
		 @NotBlank(message = "Insira um album desejado.")
		 String album,
		 @NotBlank(message = "Por favor, insira uma musica!")
		 String musica
		) {

	public Musica toEntity() {
		return new Musica (this.id, this.artista, this.album, this.musica);
	}


}
