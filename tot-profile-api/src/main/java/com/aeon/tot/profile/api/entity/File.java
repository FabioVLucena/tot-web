package com.aeon.tot.profile.api.entity;

import java.sql.Types;

import org.hibernate.annotations.JdbcTypeCode;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "file")
public class File {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "uuid")
	private String id;
	
	private String name;

	private String type;

	@Lob
	@JdbcTypeCode(Types.VARBINARY)
	private byte[] data;
	
	public File() {
		super();
	}

	public File(String name, String type, byte[] data) {
		super();
		this.name = name;
		this.type = type;
		this.data = data;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}
}
