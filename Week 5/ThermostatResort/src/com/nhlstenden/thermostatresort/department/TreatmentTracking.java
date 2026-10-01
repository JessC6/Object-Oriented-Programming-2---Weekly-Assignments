package com.nhlstenden.thermostatresort.department;

import com.nhlstenden.thermostatresort.checkin.CheckIn;
import com.nhlstenden.thermostatresort.treatment.Treatment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TreatmentTracking implements DepartmentObserver
{
    private List<Treatment> treatments;
    private Map<Treatment, TreatmentStatus> treatmentStatusMap;
    private Map<Treatment, Boolean> isMusicRequiredMap;

    public TreatmentTracking()
    {
        this.setTreatments(new ArrayList<>());
        this.setTreatmentStatusMap(new HashMap<>());
        this.setIsMusicRequiredMap(new HashMap<>());
    }

    public List<Treatment> getTreatments()
    {
        return this.treatments;
    }

    private void setTreatments(List<Treatment> treatments)
    {
        if (treatments == null)
        {
            throw new IllegalArgumentException("Treatments cannot be null.");
        }

        this.treatments = treatments;
    }

    public void addTreatment(Treatment treatment)
    {
        if (treatment == null)
        {
            throw new IllegalArgumentException("Treatment cannot be null.");
        }

        if (this.getTreatments().contains(treatment))
        {
            throw new IllegalArgumentException("This treatment already exists in the system.");
        }

        this.getTreatments().add(treatment);
    }

    public Map<Treatment, TreatmentStatus> getTreatmentStatusMap()
    {
        return this.treatmentStatusMap;
    }

    private void setTreatmentStatusMap(Map<Treatment, TreatmentStatus> treatmentStatusMap)
    {
        if (treatmentStatusMap == null)
        {
            throw new IllegalArgumentException("TreatmentStatusMap cannot be null.");
        }

        this.treatmentStatusMap = treatmentStatusMap;
    }

    public void updateTreatmentStatusMap(Treatment treatment, TreatmentStatus status)
    {
        if (treatment == null || status == null)
        {
            throw new IllegalArgumentException("Treatment and status cannot be null.");
        }

        if (!this.getTreatmentStatusMap().containsKey(treatment))
        {
            throw new IllegalArgumentException("This treatment does not exist in our system.");
        }

        // I don't need to search for the key because if it doesn't exist put() will create it, otherwise will update the value of the corresponding key.
        this.getTreatmentStatusMap().put(treatment, status);
    }

    public Map<Treatment, Boolean> getIsMusicRequiredMap()
    {
        return this.isMusicRequiredMap;
    }

    private void setIsMusicRequiredMap(Map<Treatment, Boolean> isMusicRequiredMap)
    {
        if (isMusicRequiredMap == null)
        {
            throw new IllegalArgumentException("IsMusicRequiredMap cannot be null.");
        }

        this.isMusicRequiredMap = isMusicRequiredMap;
    }

    public void updateIsMusicRequiredMap(Treatment treatment, boolean isMusicRequired)
    {
        if (treatment == null)
        {
            throw new IllegalArgumentException("Treatment cannot be null.");
        }

        this.getIsMusicRequiredMap().put(treatment, isMusicRequired);
    }

    @Override
    public void update(CheckIn checkIn)
    {
        if (checkIn == null)
        {
            throw new IllegalArgumentException("Check in cannot be null.");
        }

        Treatment treatment = checkIn.getCorrespondingBooking().getTreatment();

        if (!this.getTreatments().contains(treatment))
        {
            this.addTreatment(treatment);
        }

        // Check-in happens, so the treatment starts with PENDING status
        // and the selected music preference is recorded.
        this.getTreatmentStatusMap().put(treatment, TreatmentStatus.PENDING);
        this.getIsMusicRequiredMap().put(treatment, checkIn.getIsMusicAllowedDuringTreatment());
    }
}