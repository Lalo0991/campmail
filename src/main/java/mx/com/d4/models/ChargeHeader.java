package mx.com.d4.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@Entity
@Table(name = "charge_header")
public class ChargeHeader implements Serializable{	
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_chargue")	
	private String id;
		
	@NotNull
	@Column(name = "create_day")	
	private Date day;	
	
	@Column(name="status")
	private String status;
	
	@Column(name="read_client")
	private int readClient;
	
	@Column(name="insert_client")
	private int insertClient;
	
	@NotNull
	@Column(name = "audith_user")
	private String audithUsr;

	@NotNull
	@Column(name = "audith_time")
	private Date audithTime;

	public ChargeHeader(String id, @NotNull Date day, String status, int readClient, int insertClient,
			@NotNull String audithUsr, @NotNull Date audithTime) {
		super();
		this.id = id;
		this.day = day;
		this.status = status;
		this.readClient = readClient;
		this.insertClient = insertClient;
		this.audithUsr = audithUsr;
		this.audithTime = audithTime;
	}	
	
	

}
