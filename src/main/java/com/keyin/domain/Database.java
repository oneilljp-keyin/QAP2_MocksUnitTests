package com.keyin.domain;

import com.keyin.domain.appointment.AppointmentSlot;
import com.keyin.domain.donor.BloodDonor;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class Database {

    public List<AppointmentSlot> getAppointmentSlots() {
        ArrayList<AppointmentSlot> appointmentSlots = new ArrayList<AppointmentSlot>();

        AppointmentSlot appointmentSlot1 = new AppointmentSlot();
        appointmentSlot1.setId(1);
        appointmentSlot1.setLocation("123 Water St., St. John's, NL");
        appointmentSlot1.setLocation("123 Water St., St. John's, NL");

        appointmentSlots.add(appointmentSlot);

        return appointmentSlots;
    }

    public BloodDonor getDonor(int id) {
        BloodDonor bloodDonor = new BloodDonor();

        bloodDonor.setDateOfBirth(LocalDate.of( 1980 , Month.FEBRUARY , 11 ));

        return bloodDonor;
    }
}