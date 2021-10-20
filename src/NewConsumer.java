public final class NewConsumer {
    private long id;
    private long budget;
    private long monthlyIncome;

    public NewConsumer(final long id, final long budget, final long monthlyIncome) {
        this.id = id;
        this.budget = budget;
        this.monthlyIncome = monthlyIncome;
    }

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "NewConsumer{"
                + "id="
                + id
                + ", budget="
                + budget
                + ", monthlyIncome="
                + monthlyIncome
                + '}';
    }

    public long getBudget() {
        return budget;
    }

    public void setBudget(final long budget) {
        this.budget = budget;
    }

    public long getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(final long monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }
}
