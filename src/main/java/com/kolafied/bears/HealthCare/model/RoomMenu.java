package com.kolafied.bears.HealthCare.model;

import java.io.Serializable;

import javax.persistence.Embeddable;


@Embeddable
public class RoomMenu implements Serializable {
    private Long roomId;

    private String bedId;

    //getter, setter methods
    
    @Override
    public String toString() {
    	return "roomId = "+roomId+", bedId = "+bedId;
    }

	public RoomMenu(Long roomId, String bedId) {
		super();
		this.roomId = roomId;
		this.bedId = bedId;
	}

	public RoomMenu() {
		super();
	}

	public Long getRoomId() {
		return roomId;
	}

	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}

	public String getBedId() {
		return bedId;
	}

	public void setBedId(String bedId) {
		this.bedId = bedId;
	}
    
}