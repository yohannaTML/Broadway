import java.util.Scanner;

public class BlackJackConsole {

public BlackJackConsole() {

boolean flag = true;
Scanner scan = new Scanner(System.in);
String choice;

System.out.println("Welcome to the BlackJack table. Let's play !");
BlackJack game = new BlackJack();
System.out.println("The bank draw : "+game.getBankHandString());
System.out.println("You draw : "+game.getPlayerHandString());

do{

System.out.println("Do you want another card ? [y/n]");
choice = scan.next();

switch(choice.charAt(0)){

case 'y':
flag=true;
try{
game.playerDrawAnotherCard();
}catch(EmptyDeckException ex){
System.err.println(ex.getMessage());
System.exit(-1);
}
System.out.println("Your new hand : "+game.getPlayerHandString());
break;

case 'n':
flag=false;
break;

default:
flag=true;
break;

}

}while(flag && !(game.isGameFinished()));

try{
game.bankLastTurn();
}catch(EmptyDeckException ex){
System.err.println(ex.getMessage());
System.exit(-1);
}

System.out.println("The bank draw cards. New hand : "+game.getBankHandString());

System.out.println("Player best : "+game.getPlayerBest());
System.out.println("Bank best : "+game.getBankBest());

if(game.isPlayerWinner()){

System.out.println("You won!");

}else{

if(game.isBankWinner()){

System.out.println("You lose!");

}else{

System.out.println("Draw!");

}

}

}

public static void main(String[] args) {

new BlackJackConsole();

}

};
