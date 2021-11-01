package com.keyin.manager;

import com.keyin.domain.Database;
import com.keyin.domain.appointment.AppointmentSlot;
import com.keyin.domain.appointment.BloodDonationAppointment;
import com.keyin.domain.donor.BloodDonor;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class BloodDonationAppointmentManager {
    private Database database;

    public BloodDonationAppointmentManager(Database database) {
        this.database = database;
    }

    public BloodDonationAppointment bookAppointment(int bloodDonorId) throws InvalidDonationSchedulingException {
        BloodDonationAppointment bloodDonationAppointment = null;

        BloodDonor bloodDonor = database.getDonor(bloodDonorId);

        LocalDate today = LocalDate.now();
        LocalDate tooYoungDate = today.minus(18, ChronoUnit.YEARS);

        if (bloodDonor.getDateOfBirth().isAfter(tooYoungDate)) {
            throw new InvalidDonationSchedulingException("donor too young");
        }

        List<AppointmentSlot> appointmentSlotList = database.getAppointmentSlots();

        for (AppointmentSlot appointmentSlot: appointmentSlotList) {
            if (appointmentSlot.getBloodType().equalsIgnoreCase(bloodDonor.getBloodType())) {

            } else {
                throw new InvalidDonationSchedulingException("invalid blood type");
            }
        }

        return bloodDonationAppointment;
    }
}