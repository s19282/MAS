package com.company;

public class Sponsor {
    private String goal;
    private Double amount;

    private Person person;
    private Company company;

    public Sponsor(String goal, Double amount) {
        setGoal(goal);
        setAmount(amount);
    }

    public void setSponsor(Person person) throws Exception {
        if (company != null) {
            person.removeSponsor(this);
            throw new Exception("Sponsor is assigned already!");
        } else {
            if (this.person != null) {
                if (this.person != person) {
                    this.person.removeSponsor(this);
                    this.person = person;
                    person.sponsor(this);
                }
            } else {
                this.person = person;
                person.sponsor(this);
            }
        }
    }

    public void setSponsor(Company company) throws Exception {
        if (person != null) {
            company.removeSponsor(this);
            throw new Exception("Sponsor is assigned already!");
        } else {
            if (this.company != null) {
                if (this.company != company) {
                    this.company.removeSponsor(this);
                    this.company = company;
                    company.sponsor(this);
                }
            } else {
                this.company = company;
                company.sponsor(this);
            }
        }
    }

    public void removeSponsor(Person person) {
        if (person.getSponsors().contains(this)) {
            person.removeSponsor(this);
        }

        if (this.person != null) {
            this.person = null;
        }
    }

    public void removeSponsor(Company company) {
        if (company.getSponsors().contains(this)) {
            company.removeSponsor(this);
        }
        if (this.company != null) {
            this.company = null;
        }
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String showSponsor() {
        return "Sponsor{" +
                "goal='" + getGoal() + '\'' +
                ", amount=" + getAmount() +
                '}';
    }

    @Override
    public String toString() {
        return "Sponsor{" +
                "goal='" + getGoal() + '\'' +
                ", amount=" + getAmount() +
                ", person=" + (person == null ? "" : person.showPerson()) +
                ", company=" + (company == null ? "" : company.showCompany()) +
                '}';
    }
}
