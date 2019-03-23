import java.util.List;
import java.util.LinkedList;

public class Hand {

private LinkedList<Card> cardList;

public Hand() {

cardList = new LinkedList<Card>();

}

public String toString(){

String s = "[ ";

List<Integer> l = this.count();

int i;

if(cardList.size()!=0){

for(i=0;i<cardList.size()-1;i++){

s=s+cardList.get(i)+", ";

}

s=s+cardList.get(i);

}

s=s+"] : [ ";

if(l.size()!=0){

for(i=0;i<l.size()-1;i++){

s=s+l.get(i)+", ";

}

s=s+l.get(i);

}

s=s+" ]";

return s;

}

public void add(Card card){

cardList.add(card);

}

public void clear(){

cardList.clear();

}

public List<Integer> count(){

LinkedList<Integer> result = new LinkedList<Integer>();
LinkedList<Integer> tmp = new LinkedList<Integer>();
int val=0;

result.add(0);

for(Card c : cardList){

for(int i=0; i<result.size(); i++){

val=result.get(i);

tmp.add(val+c.getPoints());

if(c.getPoints()==1) tmp.add(val+11);

}

result = tmp;

tmp = new LinkedList<Integer>();

}

return result;

}

public int best(){

int res21=0;
int res=0;

List<Integer> l = this.count();

for(int i=0; i<l.size();i++){

if(l.get(i)>res21 && l.get(i)<=21) res21=l.get(i);

if(l.get(i)>res) res=l.get(i);

}

if(res21 > 0) return res21;

return res;

}

public List<Card> getCardList(){

return cardList;

}

};
