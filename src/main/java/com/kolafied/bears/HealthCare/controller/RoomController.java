package com.kolafied.bears.HealthCare.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.RequestParam;

import com.kolafied.bears.HealthCare.dao.RoomDao;
import com.kolafied.bears.HealthCare.model.Room;
import com.kolafied.bears.HealthCare.model.RoomMenu;
import org.json.JSONObject;
import java.util.Map;
import java.util.ArrayList;

@RestController
@RequestMapping({"/rooms"})
public class RoomController {

	 @Autowired
	    RoomDao room;


	    @GetMapping("/all")
	    public List<Room> getAllNotes() {
	        return room.findAll();
	    }

	    @PostMapping("/add")
	    public Room addRoom(@Valid @RequestBody Map roomAdd) {
	    	//System.out.println(roomAdd.toString());
	    	Long roomId=Long.parseLong(roomAdd.get("roomId").toString());
	    	String bedId=roomAdd.get("bedId").toString();
	    	Long patientId=roomAdd.get("patientId")!=null ? Long.parseLong(roomAdd.get("patientId").toString()):null;
	    	String availability=roomAdd.get("availability")!=null ? roomAdd.get("availability").toString():null;
	    	 Room roomObj=new Room(roomId,bedId,roomAdd.get("roomType").toString(),patientId,availability);
	        return room.save(roomObj);
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<Room> getRoomById(@PathVariable(value = "id") RoomMenu roomMenu) {
	 
	        return room.findById(roomMenu)
	                .map(record -> ResponseEntity.ok().body(record))
	                .orElse(ResponseEntity.notFound().build());
	    }
	    
	    @RequestMapping(method=RequestMethod.GET)
	    public List<Room> getRoomByAvailibility(@RequestParam(name="availibility",required=false) String avail,@RequestParam(name="roomType",required=false) String roomType) {
	    	List<Room> rooms= room.findAll();
	    	List<Room> ans=new ArrayList<Room>();
	    	List<Room> availableRooms=new ArrayList<Room>();
	        for(Room r: rooms) {
	        	if(avail!=null && r.getAvailability()!=null && r.getAvailability().equals(avail)) {
	        		availableRooms.add(r);
	        	}
	        }
	        	if(roomType==null) {
	        		return availableRooms;

	        	}else {
	        		if(!availableRooms.isEmpty()) {
	        			for(Room j: availableRooms) {
	        				if(j.getRoomType()!=null && j.getRoomType().equals(roomType)) {
	        					ans.add(j);
	        				}
	        			}
	        		}
	        	}
	        
	        return ans;
	    }
	    

	    @PutMapping("/update")
	    public ResponseEntity<Room> updateRoom(
	                           @Valid @RequestBody Map roomupdate) {
	    	Long roomId=Long.parseLong(roomupdate.get("roomId").toString());
	    	String bedId=roomupdate.get("bedId").toString();
	        return room.findById(new RoomMenu(roomId,bedId))
	                .map(record -> {
	                	if(roomupdate.containsKey("roomType")){
	                		Object type=roomupdate.get("roomType");
	                		if(type!=null) {
	                			record.setRoomType(type.toString());
	                		}
	                	}
	                	if(roomupdate.containsKey("availability")){
	                		Object type=roomupdate.get("availability");
	                		if(type!=null) {
	                			record.setAvailability(type.toString());
	                		}
	                	}
	                	if(roomupdate.containsKey("patientId")){
	                		Object type=roomupdate.get("patientId");
	                		if(type!=null) {
	                			record.setPatientId(Long.parseLong(type.toString()));
	                		}
	                	}
	                	
	                	
	                    
	                    Room updated = room.save(record);
	                    return ResponseEntity.ok().body(updated);
	                }).orElse(ResponseEntity.notFound().build());
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<?> deleteNote(@PathVariable(value = "id") RoomMenu menu) {

	        return room.findById(menu)
	                .map(record -> {
	                    room.deleteById(menu);
	                    return ResponseEntity.ok().build();
	                }).orElse(ResponseEntity.notFound().build());
	    }
}
