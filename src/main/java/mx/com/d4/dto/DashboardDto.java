package mx.com.d4.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class DashboardDto {
	private Long sendCamps;
	private Long errorCamps;
	private Long clients;
	private Long visits;
	private Long errors;
	private Long totalClients;
	private Long totalCamps;
	private Double sendCampsPercent;
	private Double clientsPercent;
	private Double visitsPercent;
	private Double errorsPercent;
	
	
	public DashboardDto(Long sendCamps, Long errorCamps, Long clients, Long visits, Long errors, Long totalClients,
			Long totalCamps, Double sendCampsPercent, Double clientsPercent, Double visitsPercent,
			Double errorsPercent) {
		super();
		this.sendCamps = sendCamps;
		this.errorCamps = errorCamps;
		this.clients = clients;
		this.visits = visits;
		this.errors = errors;
		this.totalClients = totalClients;
		this.totalCamps = totalCamps;
		this.sendCampsPercent = sendCampsPercent;
		this.clientsPercent = clientsPercent;
		this.visitsPercent = visitsPercent;
		this.errorsPercent = errorsPercent;
	}
	

	
	
}
