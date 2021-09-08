package r2s.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity
public class Accounts implements Serializable {
	@Id
	private String username;
	private String password;
	private String email;
	private String fullname;

	@JsonIgnore
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "account")
	List<Authorities> authorities;

	@Override
	public String toString() {
		return "";
	}
}
