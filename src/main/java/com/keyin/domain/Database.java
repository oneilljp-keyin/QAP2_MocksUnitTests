package com.keyin.domain;

import com.keyin.domain.appointment.AppointmentSlot;
import com.keyin.domain.donor.BloodDonor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class Database {

    public List<AppointmentSlot> getAppointmentSlots() {
        ArrayList<AppointmentSlot> appointmentSlots = new ArrayList<AppointmentSlot>();

        AppointmentSlot appointmentSlot1 = new AppointmentSlot();
        appointmentSlot1.setId(1);
        appointmentSlot1.setLocation("123 Water St., St. John's, NL");
        appointmentSlot1.setAppointmentDate(LocalDate.of( 2021 , Month.NOVEMBER , 15 ));
        appointmentSlot1.setStartTime(LocalTime.parse("14:00:00"));
        appointmentSlot1.setEndTime(LocalTime.parse("14:15:00"));
        appointmentSlot1.setBloodType("A");
        appointmentSlots.add(appointmentSlot1);

        AppointmentSlot appointmentSlot2 = new AppointmentSlot();
        appointmentSlot2.setId(2);
        appointmentSlot2.setLocation("123 Water St., St. John's, NL");
        appointmentSlot1.setAppointmentDate(LocalDate.of( 2021 , Month.NOVEMBER , 16 ));
        appointmentSlot1.setStartTime(LocalTime.parse("14:00:00"));
        appointmentSlot1.setEndTime(LocalTime.parse("14:15:00"));
        appointmentSlot2.setBloodType("B");
        appointmentSlots.add(appointmentSlot2);

        AppointmentSlot appointmentSlot3 = new AppointmentSlot();
        appointmentSlot3.setId(3);
        appointmentSlot3.setLocation("123 Water St., St. John's, NL");
        appointmentSlot1.setAppointmentDate(LocalDate.of( 2021 , Month.NOVEMBER , 17 ));
        appointmentSlot1.setStartTime(LocalTime.parse("14:00:00"));
        appointmentSlot1.setEndTime(LocalTime.parse("14:15:00"));
        appointmentSlot3.setBloodType("AB");
        appointmentSlots.add(appointmentSlot3);

        AppointmentSlot appointmentSlot4 = new AppointmentSlot();
        appointmentSlot4.setId(4);
        appointmentSlot4.setLocation("123 Water St., St. John's, NL");
        appointmentSlot1.setAppointmentDate(LocalDate.of( 2021 , Month.NOVEMBER , 18 ));
        appointmentSlot1.setStartTime(LocalTime.parse("10:00:00"));
        appointmentSlot1.setEndTime(LocalTime.parse("10:15:00"));
        appointmentSlot4.setBloodType("O");
        appointmentSlots.add(appointmentSlot4);

        return appointmentSlots;
    }

    public BloodDonor getDonor(int id) {
        BloodDonor bloodDonor = new BloodDonor();

        bloodDonor.setDateOfBirth(LocalDate.of( 1980 , Month.FEBRUARY , 11 ));

        return bloodDonor;
    }
}