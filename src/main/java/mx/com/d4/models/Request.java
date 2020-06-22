package mx.com.d4.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "request")
public class Request implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_request")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "id_client")
	private String idClient;

	@NotNull
	@Size(min = 1, max = 1)
	@Column(name = "status")
	private String status;
	
	@NotNull
	@Column(name="reason")
	private String reason;

	@NotNull
	@Column(name = "audith_user")
	private String audithUsr;

	@Column(name = "audith_time")
	private Date audithTime;

	
	public Request( String idClient, @NotNull @Size(min = 1, max = 1) String status, @NotNull String reason, @NotNull String audithUsr, Date audithTime) {
		super();		
		this.idClient = idClient;
		this.status = status;
		this.reason = reason;
		this.audithUsr = audithUsr;
		this.audithTime = audithTime;
	}
	
}
