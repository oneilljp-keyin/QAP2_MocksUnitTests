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
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
public class BloodDonationAppointmentManagerTest {
    // Donor Type A
    private final BloodDonor bloodDonorTypeA = new BloodDonor(3, "Blood", "TypeA",
            LocalDate.of( 1990 , 2 , 11 ), "A",
            null, LocalDate.of( 2021 , 8 , 11 ));
    // Donor Type B
    private final BloodDonor bloodDonorTypeB = new BloodDonor(4, "Blood", "TypeB",
            LocalDate.of( 1990 , 2 , 11 ), "B",
            null, LocalDate.of( 2021 , 7 , 11 ));
    // Donor Type AB
    private final BloodDonor bloodDonorTypeAB = new BloodDonor(5, "Blood", "TypeAB",
            LocalDate.of( 1990 , 2 , 11 ), "AB",
            null, LocalDate.of( 2021 , 6 , 11 ));
    // Donor Type O
    private final BloodDonor bloodDonorTypeO = new BloodDonor(6, "Blood", "TypeB",
            LocalDate.of( 1990 , 2 , 11 ), "B",
            null, LocalDate.of( 2021 , 5 , 11 ));
    // Donor First Time
    private final BloodDonor bloodDonorFirstTime = new BloodDonor(9, "First", "Time",
            LocalDate.of( 1990 , 2 , 11 ), "B",
            null, null);
    // Donor that is too young
    private final BloodDonor bloodDonorTooYoung = new BloodDonor(1, "Young", "Person",
            LocalDate.of( 2006 , 2 , 11 ), "A",
            null, null);
    // Donor that is too old
    private final BloodDonor bloodDonorTooOld = new BloodDonor(2, "Old", "Person",
            LocalDate.of( 1921 , 2 , 11 ), "A",
            null, null);
    // Donor Too Soon
    private final BloodDonor bloodDonorTooSoon = new BloodDonor(7, "Too", "Early",
            LocalDate.of( 1990 , 2 , 11 ), "B",
            null, LocalDate.of( 2021 , Month.NOVEMBER , 1 ));
    // Donor Already has Appointment
    private final BloodDonor bloodDonorAppointmentAlready = new BloodDonor(7, "Too", "Early",
            LocalDate.of( 1990 , 2 , 11 ), "B",
            LocalDate.of( 2021 , 11 , 1 ), null);
    // Donor Last Appointment Over A Year
    private final BloodDonor bloodDonorLastAppointmentOverAYear = new BloodDonor(8, "Too", "Early",
            LocalDate.of( 1990 , 2 , 11 ), "B",
            LocalDate.of( 2021 , 11 , 1 ),
            LocalDate.of( 2020 , 7 , 1 ));

    // Appointment Slot, Blood Type A
    AppointmentSlot appointmentSlotA = new AppointmentSlot(1, "123 Water St., St. John's, NL",
            LocalDate.of( 2021 , Month.NOVEMBER , 16 ),
            LocalTime.of( 14 , 0 , 0 ), LocalTime.of(14, 15, 0),
            "A");
    // Appointment Slot, Blood Type B
    AppointmentSlot appointmentSlotB = new AppointmentSlot(1, "123 Water St., St. John's, NL",
            LocalDate.of( 2021 , Month.NOVEMBER , 17 ),
            LocalTime.of( 14 , 0 , 0 ), LocalTime.of(14, 15, 0),
            "B");
    // Appointment Slot, Blood Type AB
    AppointmentSlot appointmentSlotAB = new AppointmentSlot(1, "123 Water St., St. John's, NL",
            LocalDate.of( 2021 , Month.NOVEMBER , 18 ),
            LocalTime.of( 14 , 0 , 0 ), LocalTime.of(14, 15, 0),
            "AB");
    // Appointment Slot, Blood Type O
    AppointmentSlot appointmentSlotO = new AppointmentSlot(1, "123 Water St., St. John's, NL",
            LocalDate.of( 2021 , Month.NOVEMBER , 19 ),
            LocalTime.of( 14 , 0 , 0 ), LocalTime.of(14, 15, 0),
            "O");

    @Mock
    private Database mockDatabase;

    @Test
    public void testBloodDonorTooYoung() {
        Mockito.when(mockDatabase.getDonor(1)).thenReturn(bloodDonorTooYoung);

        BloodDonationAppointmentManager bloodDonationAppointmentManager =
                new BloodDonationAppointmentManager(mockDatabase);

        try {
            BloodDonationAppointment bloodDonationAppointment = bloodDonationAppointmentManager.bookAppointment(1);
        } catch (InvalidDonationSchedulingException e) {
            Assertions.assertTrue(e.getMessage().equalsIgnoreCase("Donor Too Young"));
        }

    }

    @Test
    public void testBloodDonorTooOld() {
        Mockito.when(mockDatabase.getDonor(2)).thenReturn(bloodDonorTooOld);

        BloodDonationAppointmentManager bloodDonationAppointmentManager =
                new BloodDonationAppointmentManager(mockDatabase);

        try {
            BloodDonationAppointment bloodDonationAppointment = bloodDonationAppointmentManager.bookAppointment(2);
        } catch (InvalidDonationSchedulingException e) {
            Assertions.assertTrue(e.getMessage().equalsIgnoreCase("Donor Too Old"));
        }
    }

    @Test
    public void testBloodDonorInvalidType() {
        Mockito.when(mockDatabase.getDonor(5)).thenReturn(
                bloodDonorTypeAB
        );

        ArrayList<AppointmentSlot> appointmentSlots = new ArrayList<AppointmentSlot>();

        appointmentSlots.add(appointmentSlotA);

        Mockito.when(mockDatabase.getAppointmentSlots()).thenReturn(appointmentSlots);

        BloodDonationAppointmentManager bloodDonationAppointmentManager =
                new BloodDonationAppointmentManager(mockDatabase);

        try {
            BloodDonationAppointment bloodDonationAppointment =
                    bloodDonationAppointmentManager.bookAppointment(5);
        } catch (InvalidDonationSchedulingException e) {
            Assertions.assertTrue(e.getMessage().equalsIgnoreCase("Invalid Blood Type"));
        }

        //Assertions.fail("Did not hit expected Exception!");
    }

    @Test
    public void testBloodDonorTooSoon() {
        Mockito.when(mockDatabase.getDonor(6)).thenReturn(
                bloodDonorTooSoon
        );

        ArrayList<AppointmentSlot> appointmentSlots = new ArrayList<AppointmentSlot>();

        appointmentSlots.add(appointmentSlotB);

        Mockito.when(mockDatabase.getAppointmentSlots()).thenReturn(appointmentSlots);

        BloodDonationAppointmentManager bloodDonationAppointmentManager =
                new BloodDonationAppointmentManager(mockDatabase);

        try {
            BloodDonationAppointment bloodDonationAppointment =
                    bloodDonationAppointmentManager.bookAppointment(6);
        } catch (InvalidDonationSchedulingException e) {
            Assertions.assertTrue(e.getMessage().equalsIgnoreCase("Too Soon, Within 56 Days"));
        }
    }

    @Test
    public void testBloodAppointmentAlready() {
        Mockito.when(mockDatabase.getDonor(7)).thenReturn(
                bloodDonorAppointmentAlready
        );

        BloodDonationAppointmentManager bloodDonationAppointmentManager =
                new BloodDonationAppointmentManager(mockDatabase);

        try {
            BloodDonationAppointment bloodDonationAppointment =
                    bloodDonationAppointmentManager.bookAppointment(7);
        } catch (InvalidDonationSchedulingException e) {
            Assertions.assertTrue(e.getMessage().equalsIgnoreCase("Appointment Already Booked"));
        }
    }
    @Test
    public void testBloodDonorLastAppointmentOverAYear() {
        Mockito.when(mockDatabase.getDonor(8)).thenReturn(
                bloodDonorLastAppointmentOverAYear
        );

        BloodDonationAppointmentManager bloodDonationAppointmentManager =
                new BloodDonationAppointmentManager(mockDatabase);

        try {
            BloodDonationAppointment bloodDonationAppointment =
                    bloodDonationAppointmentManager.bookAppointment(8);
        } catch (InvalidDonationSchedulingException e) {
            Assertions.assertTrue(e.getMessage().equalsIgnoreCase("Appointment More Than A Year Ago"));
        }
    }
    @Test
    public void testBloodDonorFirstTime() {
        Mockito.when(mockDatabase.getDonor(9)).thenReturn(
                bloodDonorFirstTime
        );

        BloodDonationAppointmentManager bloodDonationAppointmentManager =
                new BloodDonationAppointmentManager(mockDatabase);

        try {
            BloodDonationAppointment bloodDonationAppointment =
                    bloodDonationAppointmentManager.bookAppointment(9);
                    Assertions.assertTrue(bloodDonationAppointment.isFirstTimeDonor());
        } catch (InvalidDonationSchedulingException e) {
//            Assertions.assertTrue(e.getMessage().equalsIgnoreCase("Appointment More Than A Year Ago"));
        }
    }

    @Test
    public void testBloodDonorValidAppointmentMade() {
        Mockito.when(mockDatabase.getDonor(3)).thenReturn(
                bloodDonorTypeA
        );

        ArrayList<AppointmentSlot> appointmentSlots = new ArrayList<AppointmentSlot>();

        appointmentSlots.add(appointmentSlotA);
        appointmentSlots.add(appointmentSlotB);
        appointmentSlots.add(appointmentSlotAB);
        appointmentSlots.add(appointmentSlotO);

        Mockito.when(mockDatabase.getAppointmentSlots()).thenReturn(appointmentSlots);

        BloodDonationAppointmentManager bloodDonationAppointmentManager =
                new BloodDonationAppointmentManager(mockDatabase);

        try {
            BloodDonationAppointment bloodDonationAppointment =
                    bloodDonationAppointmentManager.bookAppointment(3);
            Assertions.assertTrue(bloodDonationAppointment.getAppointmentDate().isEqual(LocalDate.of(2021, 11,17)));
        } catch (InvalidDonationSchedulingException e) {
//            Assertions.assertTrue(e.getMessage().equalsIgnoreCase("Appointment More Than A Year Ago"));
        }
    }
}
