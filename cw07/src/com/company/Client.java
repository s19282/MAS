package com.company;

public class Client
{
    private static long counter = 0;
    private final long clientId;
    private float discount = 0f;
    private final static float maxDiscount = .4f;
    private String name;
    private double moneySpend = 0d;

    public Client(String name)
    {
        this.name = name;
        clientId = counter++;
    }

    public long getClientId() {
        return clientId;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) throws Exception {
        if(discount<this.discount)
        {
            throw new Exception("Discount can't be decreased!");
        }
        if(discount>maxDiscount)
        {
            throw new Exception(String.format("Discount can't be bigger than: %f",maxDiscount));
        }
        this.discount = discount;
    }
    public static float getMaxDiscount() {
        return maxDiscount;
    }

    public void setDiscount() throws Exception {
        if(moneySpend>10000d)
        {
            setDiscount(.4f);
        }
        else if(moneySpend>5000d)
        {
            setDiscount(.3f);
        }
        else if(moneySpend>2500d)
        {
            setDiscount(.2f);
        }
        else if(moneySpend>1000d)
        {
            setDiscount(.1f);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMoneySpend() {
        return moneySpend;
    }

    public void setMoneySpend(double moneySpend) {
        this.moneySpend = moneySpend;
    }

    @Override
    public String toString() {
        return "Client{" +
                "clientId=" + clientId +
                ", discount=" + discount +
                ", name='" + name + '\'' +
                ", moneySpend=" + moneySpend +
                '}';
    }
}
