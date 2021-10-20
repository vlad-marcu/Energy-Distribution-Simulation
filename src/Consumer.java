

public final class Consumer extends Participant {

    private long initialBudget;
    private long monthlyIncome;
    private long id;
    private long remainingMonths = 0;
    private Distributor distributor = null;
    private boolean isBankrupt = false;
    private boolean hasRestance = false;
    private long contract = 0;
    private long contractRestance;
   private Distributor distributorRestance;

    public Distributor getDistributorRestance() {
        return distributorRestance;
    }

    public void setDistributorRestance(Distributor distributorRestance) {
        this.distributorRestance = distributorRestance;
    }

    public long getContractRestance() {
        return contractRestance;
    }


    public void setContractRestance(final long contractRestance) {
        this.contractRestance = contractRestance;
    }

    public long getContract() {
        return contract;
    }

    public boolean isHasRestance() {
        return hasRestance;
    }

    public void setHasRestance(final boolean hasRestance) {
        this.hasRestance = hasRestance;
    }

    public void setContract(final long contract) {
        this.contract = contract;
    }



    public long getRemainingMonths() {
        return remainingMonths;
    }

    public Distributor getDistributor() {
        return distributor;
    }

    public void setDistributor(final Distributor distributor) {
        this.distributor = distributor;
    }

    public boolean isBankrupt() {
        return isBankrupt;
    }

    public void setBankrupt(final boolean bankrupt) {
        isBankrupt = bankrupt;
    }

    public void setRemainingMonths(final long remainingMonths) {
        this.remainingMonths = remainingMonths;
    }

    /**
     * updates a consumer
     */
    public void update() {
        initialBudget = initialBudget + monthlyIncome;


    }



    @Override
    public String toString() {
        return "Consumer{" + "initialBudget="
                + initialBudget
                + ", monthlyIncome="
                + monthlyIncome
                + ", id="
                + id
                + ", remainingMonths="
                + remainingMonths
                + ", isBankrupt="
                + isBankrupt
                + ", contract="
                + contract
                + '}';
    }

    public long getInitialBudget() {
        return initialBudget;
    }

    public void setInitialBudget(final long initialBudget) {
        this.initialBudget = initialBudget;
    }

    public long getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(final long monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }
}
