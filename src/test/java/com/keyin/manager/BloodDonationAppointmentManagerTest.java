package com.keyin.manager;

import com.keyin.domain.Database;
import com.keyin.domain.appointment.AppointmentSlot;
import com.keyin.domain.appointment.BloodDonationAppointment;
import com.keyin.domain.donor.BloodDonor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
public class BloodDonationAppointmentManagerTest {
    @Mock
    private Database mockDatabase;

    @Test
    public void testBloodDonorTooYoung() {
        // Donor that is too young
        BloodDonor bloodDonorTooYoung = new BloodDonor();
        bloodDonorTooYoung.setFirstName("Young");
        bloodDonorTooYoung.setLastName("Person");
        bloodDonorTooYoung.setBloodType("A");
        bloodDonorTooYoung.setDateOfBirth(LocalDate.of( 2006 , Month.FEBRUARY , 11 ));
        bloodDonorTooYoung.setId(1);

        Mockito.when(mockDatabase.getDonor(1)).thenReturn(bloodDonorTooYoung);

        BloodDonationAppointmentManager bloodDonationAppointmentManager =
                new BloodDonationAppointmentManager(mockDatabase);

        try {
            BloodDonationAppointment bloodDonationAppointment = bloodDonationAppointmentManager.bookAppointment(1);
        } catch (InvalidDonationSchedulingException e) {
            Assertions.assertTrue(e.getMessage().equalsIgnoreCase("donor too young"));
        }

        //Assertions.fail("Did not hit expected Exception!");
    }

    @Test
    public void testBloodDonorTooOld() {
        // Donor that is too old
        BloodDonor bloodDonorTooOld = new BloodDonor();
        bloodDonorTooOld.setFirstName("Old");
        bloodDonorTooOld.setLastName("Person");
        bloodDonorTooOld.setBloodType("A");
        bloodDonorTooOld.setDateOfBirth(LocalDate.of( 1921 , Month.FEBRUARY , 11 ));
        bloodDonorTooOld.setId(2);

        Mockito.when(mockDatabase.getDonor(2)).thenReturn(bloodDonorTooOld);

        BloodDonationAppointmentManager bloodDonationAppointmentManager =
                new BloodDonationAppointmentManager(mockDatabase);

        try {
            BloodDonationAppointment bloodDonationAppointment = bloodDonationAppointmentManager.bookAppointment(1);
        } catch (InvalidDonationSchedulingException e) {
            Assertions.assertTrue(e.getMessage().equalsIgnoreCase("Donor too old"));
        }
    }

    @Test
    public void testBloodDonorInvalidType() {
        BloodDonor bloodDonorTypeInvalid = new BloodDonor();
        bloodDonorTypeInvalid.setFirstName("Jamie");
        bloodDonorTypeInvalid.setLastName("Cornick");
        bloodDonorTypeInvalid.setBloodType("A");
        bloodDonorTypeInvalid.setDateOfBirth(LocalDate.of( 1985 , Month.FEBRUARY , 11 ));
        bloodDonorTypeInvalid.setId(1);

        Mockito.when(mockDatabase.getDonor(1)).thenReturn(
                bloodDonorTypeInvalid
        );

        ArrayList<AppointmentSlot> appointmentSlots = new ArrayList<AppointmentSlot>();

        AppointmentSlot appointmentSlot = new AppointmentSlot();
        appointmentSlot.setId(1);
        appointmentSlot.setLocation("123 Water St. st. John's NL");
        appointmentSlot.setBloodType("B");
        appointmentSlots.add(appointmentSlot);

        Mockito.when(mockDatabase.getAppointmentSlots()).thenReturn(appointmentSlots);

        BloodDonationAppointmentManager bloodDonationAppointmentManager =
                new BloodDonationAppointmentManager(mockDatabase);

        try {
            BloodDonationAppointment bloodDonationAppointment = bloodDonationAppointmentManager.bookAppointment(1);
        } catch (InvalidDonationSchedulingException e) {
            Assertions.assertTrue(e.getMessage().equalsIgnoreCase("invalid blood type"));
        }

        //Assertions.fail("Did not hit expected Exception!");
    }
}