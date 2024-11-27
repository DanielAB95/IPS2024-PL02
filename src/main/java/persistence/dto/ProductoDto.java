package persistence.dto;

import java.util.Objects;

public class ProductoDto {
	
	public int idProducto;
	public String nombre;
	public String categoria;
	public String descripcion;
	public double precio;
	public int pasillo, estanteria, balda; //(x,y,z) almacen
	public int iva;
	public int stock, minStock, stockReposicion;
	
	@Override
	public int hashCode() {
		return Objects.hash(idProducto);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductoDto other = (ProductoDto) obj;
		return idProducto == other.idProducto;
	}
	
	public String ubicacion() {		
		return pasillo + "-" + estanteria + "-" + balda;
	}
	
}
