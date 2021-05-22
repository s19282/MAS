package com.company;

public class Sensor
{
    private String name;
    private String serialNumber;

    private Device device;
    private Device deviceSubset;

    public Sensor(String name, String serialNumber) {
        this.name = name;
        this.serialNumber = serialNumber;
    }

    public void setDevice(Device device)
    {
        if(this.device != null)
        {
            if(this.device != device)
            {
                this.device.removeSensor(this);
                this.device = device;
                device.addSensor(this);
            }
        }
        else
        {
            this.device = device;
            device.addSensor(this);
        }
    }

    public void removeDevice()
    {
        if(device.getSensors().contains(this))
            device.removeSensor(this);

        if(device !=null)
        {
            device = null;
        }
        removeDeviceSubset();
    }
    public void setDeviceSubset(Device device) throws Exception {
        if(this.device == null || !this.device.getSensors().contains(this) || this.device!=device)
        {
            throw new Exception("Add device before you specify critical sensor!");
        }
        {
            if(this.deviceSubset != null)
            {
                if(this.deviceSubset != device)
                {
                    this.deviceSubset.removeCriticalSensor(this);
                    this.deviceSubset = device;
                    device.addCriticalSensor(this);
                }
            }
            else
            {
                this.deviceSubset = device;
                device.addCriticalSensor(this);
            }
        }
    }

    public void removeDeviceSubset()
    {
        if(deviceSubset.getCriticalSensors().contains(this))
            deviceSubset.removeCriticalSensor(this);

        if(deviceSubset !=null)
        {
            deviceSubset = null;
        }
    }
    public Device getDevice() {
        return device;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String showSensor()
    {
        return "Sensor{" +
                "name='" + name + '\'' +
                ", serialNumber='" + serialNumber + '\'' +
                '}';
    }
    @Override
    public String toString() {
        return "Sensor{" +
                "name='" + name + '\'' +
                ", serialNumber='" + serialNumber + '\'' +
                ", device=" + (device != null ? device.showDevice() : "") +
                ", deviceSubset=" + (deviceSubset != null ? deviceSubset.showDevice() : "") +
                '}';
    }
}
