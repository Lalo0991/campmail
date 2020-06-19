package mx.com.d4.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "parameter")
public class ParameterConfig implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "parameter")
	private String parameter;
	
	@NotNull
	@Column(name = "value")
	private String value;

	@NotNull
	@Column(name = "description")
	private String description;

	@NotNull
	@Column(name = "audith_user")
	private String audith_user;

	@NotNull
	@Column(name = "audith_create_time")
	private Date audith_create_time;

	@NotNull
	@Column(name = "audith_update_time")
	private Date audith_update_time;

	public ParameterConfig() {
		super();
	}
	

	public ParameterConfig(String parameter, @NotNull String value, @NotNull String description, @NotNull String audith_user, @NotNull Date audith_create_time, @NotNull Date audith_update_time) {
		super();
		this.parameter = parameter;
		this.value = value;
		this.description = description;
		this.audith_user = audith_user;
		this.audith_create_time = audith_create_time;
		this.audith_update_time = audith_update_time;
	}


	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}
	
	

	public String getValue() {
		return value;
	}


	public void setValue(String value) {
		this.value = value;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAudith_user() {
		return audith_user;
	}

	public void setAudith_user(String audith_user) {
		this.audith_user = audith_user;
	}

	public Date getAudith_create_time() {
		return audith_create_time;
	}

	public void setAudith_create_time(Date audith_create_time) {
		this.audith_create_time = audith_create_time;
	}

	public Date getAudith_update_time() {
		return audith_update_time;
	}

	public void setAudith_update_time(Date audith_update_time) {
		this.audith_update_time = audith_update_time;
	}


	@Override
	public String toString() {
		return String.format("ParameterConfig [parameter=%s, value=%s, description=%s, audith_user=%s, audith_create_time=%s, audith_update_time=%s]", parameter, value, description, audith_user, audith_create_time,
				audith_update_time);
	}

}
