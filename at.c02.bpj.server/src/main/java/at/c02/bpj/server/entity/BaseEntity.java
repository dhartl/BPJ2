package at.c02.bpj.server.entity;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;

/**
 * Entity, die Hashcode u. Equals implementiert
 * 
 * @param <T>
 */
@MappedSuperclass
public abstract class BaseEntity<T> implements Serializable {
	private static final long serialVersionUID = -3900126160531312012L;

	public abstract T getId();

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		@SuppressWarnings("unchecked")
		BaseEntity<T> other = (BaseEntity<T>) obj;
		if (getId() == null || other.getId() == null) {
			return false;
		} else if (!getId().equals(other.getId()))
			return false;
		return true;
	}

}
