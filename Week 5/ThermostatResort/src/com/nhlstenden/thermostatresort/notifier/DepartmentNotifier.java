package com.nhlstenden.thermostatresort.notifier;

import com.nhlstenden.thermostatresort.checkin.CheckIn;
import com.nhlstenden.thermostatresort.department.DepartmentObserver;

import java.util.ArrayList;
import java.util.List;

public class DepartmentNotifier
{
    private List<DepartmentObserver> observers;

    public DepartmentNotifier()
    {
        this.setObservers(new ArrayList<>());
    }

    public List<DepartmentObserver> getObservers()
    {
        return this.observers;
    }

    private void setObservers(List<DepartmentObserver> observers)
    {
        if (observers == null)
        {
            throw new IllegalArgumentException("Observers cannot be null.");
        }

        this.observers = observers;
    }

    public void addObserver(DepartmentObserver observer)
    {
        if (observer == null)
        {
            throw new IllegalArgumentException("Observer cannot be null.");
        }

        if (this.getObservers().contains(observer))
        {
            throw new IllegalArgumentException("This observer already exists in the system.");
        }

        this.getObservers().add(observer);
    }

    public void removeObserver(DepartmentObserver observer)
    {
        if (observer == null)
        {
            throw new IllegalArgumentException("Observer cannot be null.");
        }

        if (!this.getObservers().contains(observer))
        {
            throw new IllegalArgumentException("This observer does not exist in the system.");
        }

        this.getObservers().remove(observer);
    }

    public void notifyObservers(CheckIn checkIn)
    {
        if (checkIn == null)
        {
            throw new IllegalArgumentException("Check In cannot be null.");
        }

        for (DepartmentObserver departmentObserver : this.getObservers())
        {
            departmentObserver.update(checkIn);
        }
    }
}