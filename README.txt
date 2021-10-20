Marcu Vlad 324 CD

I used the factory pattern to implement the Participant class, respectively the
Consumer and Distributor classes that extends it.
I used the singletonp pattern in the SingletonWriter, SingletonReader and SingletonSolver 
classes.These are classes that have methods used in my algotithm.
I solved the problem in a number of steps.Firstly I read the input from the JSON file
and I save it in a Database class.Then the proper game starts.We update the cost changes
and we add the new users.Then we calculate the contracts of each distributor that is
not bankrupt.Then we remove the consumers with the expired contracts from their
respective distributor.Then, each consumer without a distributor chooses one.The consumers
receive their monthly income and the distributors pay the infrastructure and the production
costs.Then, the consumers pay thei respective taxes and become bankrupt depending on the
case, and the distributors receive their income from the consumers.After that we check 
if any distributor became bankrupt.Finally, we update the remaining months of each consumer.
In the end, the ouput is written to a JSON file.