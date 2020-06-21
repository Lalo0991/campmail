package mx.com.d4.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "client")
public class Client implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_client")
	private String id;
			
	@ManyToOne
	@JoinColumn(name = "hdr_chargue", referencedColumnName = "id_chargue")
	private ChargeHeader chargeHdr;

	@NotNull
	@Column(name = "status")
	private String status;

	@NotNull
	@Size(min = 3, max = 70)
	@Column(name = "contact")
	private String contact;

	@NotNull
	@Size(min = 3, max = 70)
	@Column(name = "mail")
	private String mail;

	@NotNull
	@Size(min = 3, max = 60)
	@Column(name = "name")
	private String name;

	@NotNull
	@Size(min = 3, max = 20)
	@Column(name = "tag")
	private String tag;

	@NotNull
	@Column(name = "audith_user")
	private String audithUsr;

	@NotNull
	@Column(name = "audith_time")
	private Date audithTime;	
}
