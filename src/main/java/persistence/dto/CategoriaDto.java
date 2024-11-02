package persistence.dto;

public class CategoriaDto {
	private String nombreCategoria;
	private String categoriaPadre;
	
	public CategoriaDto(String nombreCategoria, String categoriaPadre) {
		this.nombreCategoria = nombreCategoria;
		this.categoriaPadre = categoriaPadre;
	}
	public String getNombreCategoria() {
		return nombreCategoria;
	}
	public void setNombreCategoria(String nombreCategoria) {
		this.nombreCategoria = nombreCategoria;
	}
	public String getCategoriaPadre() {
		return categoriaPadre;
	}
	public void setCategoriaPadre(String categoriaPadre) {
		this.categoriaPadre = categoriaPadre;
	}
	
	
}
