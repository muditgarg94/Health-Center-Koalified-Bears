package com.kolafied.bears.HealthCare.model;


import javax.persistence.Entity;
import javax.persistence.EmbeddedId;

import javax.persistence.EntityListeners;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.kolafied.bears.HealthCare.model.RoomMenu;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.NoArgsConstructor;
import lombok.Setter;




@Table(name="room")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Room {
	
	@EmbeddedId
	private RoomMenu roomMenu;
	
	@NotBlank
	private String roomType;
	private Long patientId;
	private String availability;
	
	public Room() {
	}
	
	public Room(RoomMenu roomMenu, @NotBlank String roomType, Long patientId, String availability) {
		super();
		this.roomMenu = roomMenu;
		this.roomType = roomType;
		this.patientId = patientId;
		this.availability = availability;
	}

	public Room(Long roomId, String bedId, String roomType, Long patientId, String availability) {
		this.roomMenu=new RoomMenu(roomId,bedId);
		this.roomType=roomType;
		this.patientId=patientId;
		this.availability=availability;
		
	}


	public RoomMenu getRoomMenu() {
		return roomMenu;
	}


	public void setRoomMenu(RoomMenu roomMenu) {
		this.roomMenu = roomMenu;
	}


	public String getRoomType() {
		return roomType;
	}


	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}


	public Long getPatientId() {
		return patientId;
	}


	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}


	public String getAvailability() {
		return availability;
	}


	public void setAvailability(String availability) {
		this.availability = availability;
	}
	
	
}


