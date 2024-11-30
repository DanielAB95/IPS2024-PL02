package persistence.dto;

import java.util.ArrayList;
import java.util.List;

public class VehiculoDto {
	public String matricula;
	public String zonaReparto;
	public List<PaqueteDto> paquetes = new ArrayList<>();
	
}
