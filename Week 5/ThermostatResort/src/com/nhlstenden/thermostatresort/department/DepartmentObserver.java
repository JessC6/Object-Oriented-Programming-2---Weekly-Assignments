package com.nhlstenden.thermostatresort.department;

import com.nhlstenden.thermostatresort.checkin.CheckIn;

public interface DepartmentObserver
{
    void update(CheckIn checkIn);
}