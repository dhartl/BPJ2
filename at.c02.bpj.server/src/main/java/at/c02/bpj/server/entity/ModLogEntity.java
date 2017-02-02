package at.c02.bpj.server.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Klasse, die automatisch Einfüge- und Änderungsdatum speichert
 * 
 */
@MappedSuperclass
public abstract class ModLogEntity<T> extends BaseEntity<T> {
	private static final long serialVersionUID = 1440807528504378577L;

	@Column(name = "insDt", nullable = false, updatable = false)
	private Date insDt;

	@Column(name = "insUserId", nullable = false, updatable = false)
	private Long insUserId;

	@Column(name = "updDt", nullable = false)
	private Date updDt;

	@Column(name = "updUserId", nullable = false)
	private Long updUserId;

	public void setInsDt(Date insDt) {
		this.insDt = insDt;
	}

	public Date getInsDt() {
		return insDt;
	}

	public void setInsUserId(Long insUserId) {
		this.insUserId = insUserId;
	}

	public Long getInsUserId() {
		return insUserId;
	}

	public void setUpdDt(Date updDt) {
		this.updDt = updDt;
	}

	public Date getUpdDt() {
		return updDt;
	}

	public void setUpdUserId(Long updUserId) {
		this.updUserId = updUserId;
	}

	public Long getUpdUserId() {
		return updUserId;
	}

	@PrePersist
	protected void beforeInsert() {
		insDt = new Date();
		insUserId = getCurrentUserId();
	}

	@PreUpdate
	protected void beforeUpdate() {
		updDt = new Date();
		updUserId = getCurrentUserId();
	}

	private static Long getCurrentUserId() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.getPrincipal() != null
				&& authentication.getPrincipal() instanceof Employee) {
			Employee employee = (Employee) authentication.getPrincipal();
			if (employee != null) {
				return employee.getEmployeeId();
			}
		}
		return 0L;
	}
}
