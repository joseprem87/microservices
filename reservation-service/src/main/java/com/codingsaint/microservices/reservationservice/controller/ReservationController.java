package com.codingsaint.microservices.reservationservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codingsaint.microservices.reservationservice.model.Reservation;

@RestController
public class ReservationController {
	@Value("${server.port}")
	private Integer port;

	@PreAuthorize("#oauth2.hasScope('read')")
	@GetMapping("reservation")
	public List<Reservation> getResrvation(){
		List<Reservation> list= new ArrayList<>();
		Reservation r= new Reservation();
		r.setName("TEST :"+port);
		list.add(r);
		return list;
		
	}
	@GetMapping("reservation-test")
	public List<Reservation> getResrvationTest(){
		List<Reservation> list= new ArrayList<>();
		Reservation r= new Reservation();
		r.setName("TEST2");
		list.add(r);
		return list;
		
	}
}
