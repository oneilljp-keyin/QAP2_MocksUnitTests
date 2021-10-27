package com.keyin.domain.appointment;

import java.time.LocalDate;
import java.time.LocalTime;

public class AppointmentSlot {
  private int id;
  private String location;
  private LocalDate slotDate;
  private LocalTime startTime;
  private LocalTime endTime;
  private String bloodType;

  public int getId() {
    return id;
  }

  public String getLocation() {
    return location;
  }

  public LocalDate getSlotDate() {
    return slotDate;
  }

  public void setSlotDate(LocalDate slotDate) {
    this.slotDate = slotDate;
  }

  public LocalTime getStartTime() {
    return startTime;
  }

  public void setStartTime(LocalTime startTime) {
    this.startTime = startTime;
  }

  public LocalTime getEndTime() {
    return endTime;
  }

  public void setEndTime(LocalTime endTime) {
    this.endTime = endTime;
  }

  public String getBloodType() {
    return bloodType;
  }

  public void setBloodType(String bloodType) {
    this.bloodType = bloodType;
  }

  public void setId(int idl) {
    this.id = idl;
  }

  public void setLocation(String location) {
    this.location = location;
  }
}