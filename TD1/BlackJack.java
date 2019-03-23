import java.util.LinkedList;
import java.util.List;

public class BlackJack{

private Deck deck;
private Hand playerHand;
private Hand bankHand;
public boolean gameFinished;

public BlackJack(){

this.deck = new Deck(4);
this.playerHand = new Hand();
this.bankHand = new Hand();
this.gameFinished = false;

try{
this.reset();
}catch(EmptyDeckException ex){
System.err.println(ex.getMessage());
System.exit(-1);
}

}

public void reset() throws EmptyDeckException{

this.playerHand.clear();
this.bankHand.clear();

this.bankHand.add(this.deck.draw());
this.playerHand.add(this.deck.draw());
this.playerHand.add(this.deck.draw());
this.gameFinished = false;

}

public String getPlayerHandString(){

return this.playerHand.toString();

}

public String getBankHandString(){

return this.bankHand.toString();

}

public int getPlayerBest(){

return this.playerHand.best();

}

public int getBankBest(){

return this.bankHand.best();

}

public boolean isPlayerWinner(){

int p = this.getPlayerBest();
int b = this.getBankBest();

if(p<=21 && (p>b || b>21) && this.gameFinished) return true;

return false;

}

public boolean isBankWinner(){

int p = this.getPlayerBest();
int b = this.getBankBest();

if(b<=21 && (b>p || p>21) && this.gameFinished) return true;

return false;

}

public boolean isGameFinished(){

return this.gameFinished;

}

public void playerDrawAnotherCard() throws EmptyDeckException{

if(!(this.isGameFinished())){

this.playerHand.add(this.deck.draw());

if(this.playerHand.best()>21) gameFinished = true;

}

}

public void bankLastTurn() throws EmptyDeckException{

//if(!(this.isGameFinished())){

do{

this.bankHand.add(this.deck.draw());

}while(this.bankHand.best()<=21 && this.bankHand.best()<this.playerHand.best() && 21>=this.playerHand.best());

this.gameFinished = true;

//}

}

public List<Card> getPlayerCardList(){

List<Card> originalList = playerHand.getCardList();
LinkedList<Card> copyList = new LinkedList<Card>(originalList);

return copyList;

}

public List<Card> getBankCardList(){

List<Card> originalList = bankHand.getCardList();
LinkedList<Card> copyList = new LinkedList<Card>(originalList);

return copyList;

}

}
