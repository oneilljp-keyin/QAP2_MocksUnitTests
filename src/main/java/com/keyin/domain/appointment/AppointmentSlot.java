package com.keyin.domain.appointment;

import java.time.LocalDate;
import java.time.LocalTime;

public class AppointmentSlot {
  private int id;
  private String location;
  private LocalDate appointmentDate;
  private LocalTime startTime;
  private LocalTime endTime;
  private String bloodType;

  public void setBloodType(String bloodType) { this.bloodType = bloodType; }
  public void setId(int id) { this.id = id; }
  public void setLocation(String location) { this.location = location; }
  public void setAppointmentDate(LocalDate appointmentDate) { this.appointmentDate = appointmentDate; }
  public void setStartTime(LocalTime startTime) { this.startTime = startTime; }
  public void setEndTime(LocalTime endTime) { this.endTime = endTime; }

  public String getBloodType() { return bloodType; }
  public int getId() { return id; }
  public String getLocation() { return location; }
  public LocalDate getAppointmentDate() { return appointmentDate; }
  public LocalTime getStartTime() { return startTime; }
  public LocalTime getEndTime() { return endTime; }

}