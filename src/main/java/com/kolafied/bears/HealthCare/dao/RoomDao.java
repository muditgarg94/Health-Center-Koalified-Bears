package com.kolafied.bears.HealthCare.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kolafied.bears.HealthCare.model.Room;
import com.kolafied.bears.HealthCare.model.RoomMenu;

@Repository
public interface RoomDao extends JpaRepository<Room, RoomMenu> {
}
