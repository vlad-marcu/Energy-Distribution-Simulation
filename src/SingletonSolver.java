public final class SingletonSolver {
    private static SingletonSolver instance;

    static {
        instance = new SingletonSolver();
    }

    public static SingletonSolver getInstance() {
        return instance;
    }

    /**
     * adds the monthly updates
     * @param database
     */
    public void makeUpdates(final Database database, final long index) {
        if (index != 0) {
            if (database.getMonthlyChanges().get((int) index - 1).getCostChanges() != null) {
                for (CostChange change : database.getMonthlyChanges()
                        .get((int) index - 1).getCostChanges()) {
                    database.getDistributors().get((int) change.getId()).updateCostChange(change);
                }
            }

            if (database.getMonthlyChanges().get((int) index - 1).getNewConsumers() != null) {
            for (Consumer consumer
                    : database.getMonthlyChanges().get((int) index - 1).getNewConsumers()) {
                    database.getConsumers().add(consumer);
                }
            }
        }


    }



    /**
     * calculates the contract price of each distributor
     * @param database
     */
    public void contractCalculator(final Database database) {
        for (Distributor distributor : database.getDistributors()) {
            if (!distributor.isBankrupt()) {
                distributor.profitCalculator();
                distributor.contractCalculator();
            }
        }
    }

    /**
     * deletes a consumer comntract if it's expired
     * @param database
     */
    public void removeExpiredContracts(final Database database) {
        for (Consumer consumer : database.getConsumers()) {
            if (!consumer.isBankrupt()) {
                if (consumer.getRemainingMonths() == 0) {
                    for (Distributor distributor : database.getDistributors()) {
                        if (!distributor.isBankrupt()) {
                            if (distributor.getConsumersInput().contains(consumer)) {
                                distributor.getConsumersInput().remove(consumer);
                                consumer.setDistributor(null);


                            }
                        }
                    }
                    }
                }

        }
    }



    /**
     * all the valid consumers choose a distributor
     * @param database
     */
    public void chooseContracts(final Database database) {
        for (Consumer consumer : database.getConsumers()) {
            if (!consumer.isBankrupt()) {
                if (consumer.getRemainingMonths() == 0) {
              consumer.setDistributor(database.getDistributorMinContract());
              database.getDistributorMinContract().getConsumersInput().add(consumer);
              consumer.setContract(database.getDistributorMinContract().getContract());
              consumer.setRemainingMonths(database.getDistributorMinContract().getContractLength());
                }
            }
        }
    }

    /**
     * adds the monthly income to each consumer and the infrastructure and
     * production costs to each distributor
     * @param database
     */
    public void updateBudget(final Database database) {
        for (Consumer consumer : database.getConsumers()) {
            if (!consumer.isBankrupt()) {
                consumer.update();
            }
        }

        for (Distributor distributor : database.getDistributors()) {
            if (!distributor.isBankrupt()) {
                distributor.update();
            }

        }
    }

    /**
     * all the consumers and the distributors pay their costs
     * @param database
     */
    public void pay(final Database database) {
        for (Consumer consumer : database.getConsumers()) {
            if (!consumer.isBankrupt()) {
                if (consumer.isHasRestance()) {
                    if (consumer.getInitialBudget() - consumer.getContract()
                            - Math.round(Math.floor(1.2 * consumer.getContractRestance())) >= 0) {
                        consumer.setHasRestance(false);
                        consumer.setInitialBudget((consumer.getInitialBudget()
                                - consumer.getContract()
                                - Math.round(Math.floor(1.2 * consumer.getContractRestance()))));
                        for (Distributor distributor : database.getDistributors()) {
                            if (!distributor.isBankrupt()) {
                                if (distributor == consumer.getDistributorRestance()) {
                                    distributor.setInitialBudget((distributor.getInitialBudget()
                                  + Math.round(Math.floor(1.2 * consumer.getContractRestance()))));
                                    break;
                                }
                                if (distributor == consumer.getDistributor()) {
                                    distributor.setInitialBudget(distributor.getInitialBudget()
                                     + consumer.getContract());
                                }
                            }
                        }
                    } else {
                        consumer.setBankrupt(true);
                        for (Distributor distributor : database.getDistributors()) {
                            distributor.getConsumersInput().remove(consumer);
                        }
                    }
                } else {
                    if (consumer.getInitialBudget() - consumer.getContract() >= 0) {
                        consumer.setInitialBudget(consumer.getInitialBudget()
                                - consumer.getContract());
                        for (Distributor distributor : database.getDistributors()) {
                            if (!distributor.isBankrupt()) {
                                if (distributor.getConsumersInput().contains(consumer)) {
                                    distributor.setInitialBudget(distributor.getInitialBudget()
                                            + consumer.getContract());
                                    break;
                                }
                            }
                        }

                    } else {
                        consumer.setHasRestance(true);
                        consumer.setContractRestance(consumer.getContract());

                    }
                }
            }
        }
    }

    /**
     * checks if a distributor is bankrupt
     * @param database
     */
   public void checkBankrupcy(final Database database) {
       for (Distributor distributor : database.getDistributors()) {
               if (distributor.getInitialBudget() < 0) {
                   distributor.getConsumersInput().clear();
                   distributor.setBankrupt(true);
                   for (Consumer consumer : database.getConsumers()) {
                       if (consumer.getDistributor() == distributor) {
                           if (!consumer.isBankrupt()) {
                               if (consumer.isHasRestance()) {
                                   consumer.setHasRestance(false);
                               }
                               consumer.setDistributor(null);
                               consumer.setRemainingMonths(0);

                           }
                       }
                   }
               }


       }



   }

    /**
     * updates remaining months of consumers
     * @param database
     */
   public void decreaseRemainingMonths(final Database database) {

        for (Consumer consumer : database.getConsumers()) {
            if (!consumer.isBankrupt()) {
                if (consumer.getRemainingMonths() != 0) {
                    consumer.setRemainingMonths(consumer.getRemainingMonths() - 1);
                }
            }
        }
   }

    /**
     * combines all the tasks for each round of the game
     * @param database
     * @param index
     */
    public void round(final Database database, final long index) {
        makeUpdates(database, index);
        contractCalculator(database);
        removeExpiredContracts(database);
        chooseContracts(database);
        updateBudget(database);
        pay(database);
        checkBankrupcy(database);
        decreaseRemainingMonths(database);




    }

}
