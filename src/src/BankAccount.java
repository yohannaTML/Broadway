public class BankAccount {

    int balance;

    BankAccount(int balance){
        this.balance= balance;
    }
    public int getBalance(){
        return balance;
    }
    public int deposit(int amount) throws Exception{
        if (amount < 0){
            throw new Exception("Incorrect amount");
        }else{
            return this.balance+=amount;
        }
    }

    public int withdrawal(int amount) throws Exception{
        if (amount > balance){
            throw new Exception("Not enough money!!!");
        }else{
            return this.balance-=amount;
        }
    }

    public static void main(String[] args) {
        BankAccount myBankAccount = new BankAccount(3000);
        try {
            myBankAccount.deposit(2000);
            System.out.println("My new account:" + myBankAccount.getBalance());
            myBankAccount.deposit(-4000);
            System.out.println("My new account:" + myBankAccount.getBalance());
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        try {
            myBankAccount.withdrawal(2000);
            System.out.println("My new account:" + myBankAccount.getBalance());
            myBankAccount.withdrawal(5000);
            System.out.println("My new account:" + myBankAccount.getBalance());
        } catch(Exception e){
            System.out.println(e.getMessage());
        }

    }

}
