public final class CostChange {
    private long id;
    private long infrastructureCost;
    private long productionCost;

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "CostChange{" + "id="
                + id
                + ", infrastructureCost="
                + infrastructureCost
                + ", productionCost="
                + productionCost
                + '}';
    }

    public long getInfrastructureCost() {
        return infrastructureCost;
    }

    public void setInfrastructureCost(final long infrastructureCost) {
        this.infrastructureCost = infrastructureCost;
    }

    public long getProductionCost() {
        return productionCost;
    }

    public void setProductionCost(final long productionCost) {
        this.productionCost = productionCost;
    }

    public CostChange(final long id, final long infrastructureCost, final long productionCost) {
        this.id = id;
        this.infrastructureCost = infrastructureCost;
        this.productionCost = productionCost;
    }
}
