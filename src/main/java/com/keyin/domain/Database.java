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

        AppointmentSlot appointmentSlot1 = new AppointmentSlot(1,"123 Water St., St. John's, NL",
                LocalDate.of( 2021 , Month.NOVEMBER , 15 ), LocalTime.parse("14:00:00"),
                LocalTime.parse("14:15:00"),"A");
        appointmentSlots.add(appointmentSlot1);

        AppointmentSlot appointmentSlot2 = new AppointmentSlot(2,"123 Water St., St. John's, NL",
                LocalDate.of( 2021 , Month.NOVEMBER , 16 ),LocalTime.parse("14:00:00"),
                LocalTime.parse("14:15:00"),"B");
        appointmentSlots.add(appointmentSlot2);

        AppointmentSlot appointmentSlot3 = new AppointmentSlot(3,"123 Water St., St. John's, NL",
                LocalDate.of( 2021 , Month.NOVEMBER , 17 ), LocalTime.parse("14:00:00"),
                LocalTime.parse("14:15:00"), "AB");
        appointmentSlots.add(appointmentSlot3);

        AppointmentSlot appointmentSlot4 = new AppointmentSlot(4,"123 Water St., St. John's, NL",
                LocalDate.of( 2021 , Month.NOVEMBER , 18 ),LocalTime.parse("10:00:00"),
                LocalTime.parse("10:15:00"),"O");
        appointmentSlots.add(appointmentSlot4);

        return appointmentSlots;
    }

    public BloodDonor getDonor(int id) {
        BloodDonor bloodDonor = new BloodDonor(id, null, null, null,
                null, null, null);

        bloodDonor.setDateOfBirth(LocalDate.of( 1980 , Month.FEBRUARY , 11 ));

        return bloodDonor;
    }
}