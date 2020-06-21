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

}
