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
@Table(name = "log")
public class Log implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id_log")	
	private Long id;
	
	@NotNull
	@Column(name = "operation")	
	private String operation;
	
	@NotNull
	@Column(name = "status")	
	private String status;
	
	@NotNull
	@Column(name = "operation_date")	
	private Date operationDate;
	
	@NotNull
	@Column(name = "description")	
	private String description;
	
	@NotNull
	@Column(name = "user")	
	private String user;
	
	@NotNull
	@Column(name = "creation_date")	
	private Date creationDate;
	
	@NotNull
	@Column(name = "modification_date")	
	private Date modificationDate;
}
