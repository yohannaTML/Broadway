import java.util.LinkedList;
import java.util.Collections;

public class Deck {

private LinkedList<Card> cardList;

public Deck(int nbBox) {

cardList = new LinkedList<Card>();

for(int i=0;i<nbBox;i++){

for(Color c: Color.values()){

for(Value v: Value.values()){

cardList.add(new Card(v, c));

}

}

}

Collections.shuffle(cardList);

}

public String toString(){

String s = "[ ";

int i;

if(cardList.size()!=0){

for(i=0;i<cardList.size()-1;i++){

s=s+cardList.get(i)+", ";

}

s=s+cardList.get(i);

}

s=s+"]";

return s;

}

public Card draw() throws EmptyDeckException{

if(cardList.size() == 0){
			
throw new EmptyDeckException("The deck is empty.");

}else{

return cardList.pollFirst();

}

}

};
