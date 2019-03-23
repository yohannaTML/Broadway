import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BlackJackGUI implements ActionListener{

BlackJack bj;
JPanel playerPanel;
JPanel bankPanel;
JButton anotherButton;
JButton noMoreButton;
JButton resetButton;

public BlackJackGUI() {

bj = new BlackJack();
playerPanel = new JPanel();
bankPanel = new JPanel();
JFrame frame = new JFrame("BlackJack GUI");
JPanel topPanel = new JPanel();
JPanel centerPanel = new JPanel();
anotherButton = new JButton("Another Card");
anotherButton.setActionCommand("Another Card");
anotherButton.addActionListener(this);
noMoreButton = new JButton("No More Card");
noMoreButton.setActionCommand("No More Card");
noMoreButton.addActionListener(this);
resetButton = new JButton("Reset");
resetButton.setActionCommand("Reset");
resetButton.addActionListener(this);
BorderLayout l1 = new BorderLayout();
FlowLayout l2 = new FlowLayout(FlowLayout.LEFT);
GridLayout l3 = new GridLayout(2,1);

bankPanel.setBorder(BorderFactory.createTitledBorder("Bank"));
playerPanel.setBorder(BorderFactory.createTitledBorder("Player"));

playerPanel.setLayout(l2);
bankPanel.setLayout(l2);
centerPanel.setLayout(l2);

topPanel.setLayout(l2);
centerPanel.setLayout(l3);

topPanel.add(anotherButton);
topPanel.add(noMoreButton);
topPanel.add(resetButton);

centerPanel.add(bankPanel);
centerPanel.add(playerPanel);

frame.setLayout(l1);

frame.add(topPanel,BorderLayout.NORTH);
frame.add(centerPanel,BorderLayout.CENTER);

frame.setMinimumSize(new Dimension(640,480));
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
frame.pack();
frame.setVisible(true);

try{
updatePlayerPanel();
updateBankPanel();
} catch (FileNotFoundException ex) {
JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
System.exit(-1);
}

}

public static void main(String[] args) {

new BlackJackGUI();

}

private void addToPanel(JPanel p, String token)throws FileNotFoundException{

File file = new File("./img/card_"+token+".png");
if (!file.exists()) {
throw new FileNotFoundException("Can't find "+file.getPath());
}
ImageIcon icon = new ImageIcon(file.getPath());
JLabel label = new JLabel(icon); 
p.add(label);

}

private void updatePlayerPanel()throws FileNotFoundException{

playerPanel.removeAll();

for(Card c : bj.getPlayerCardList()){

addToPanel(playerPanel,c.getColorName()+"_"+c.getValueSymbole());

}

playerPanel.add(new JLabel("Player Best : "+bj.getPlayerBest()));

if(bj.getPlayerBest()==21) addToPanel(playerPanel,"blackjack");

if(bj.isGameFinished()){

if(bj.isPlayerWinner()){

addToPanel(playerPanel,"winner");

}else{

if(bj.isBankWinner()){

addToPanel(playerPanel,"looser");

}else{

addToPanel(playerPanel,"draw");

}

}

}

playerPanel.updateUI();

}

private void updateBankPanel()throws FileNotFoundException{

bankPanel.removeAll();

for(Card c : bj.getBankCardList()){

addToPanel(bankPanel,c.getColorName()+"_"+c.getValueSymbole());

}

bankPanel.add(new JLabel("Bank Best : "+bj.getBankBest()));

if(bj.getBankBest()==21) addToPanel(bankPanel,"blackjack");

if(bj.isGameFinished()){

if(bj.isBankWinner()){

addToPanel(bankPanel,"winner");

}else{

if(bj.isPlayerWinner()){

addToPanel(bankPanel,"looser");

}else{

addToPanel(bankPanel,"draw");

}

}

}

bankPanel.updateUI();

}

public void actionPerformed(ActionEvent e) {
switch(e.getActionCommand()) {
case "Another Card":
if(!(bj.isGameFinished())){

try{
bj.playerDrawAnotherCard();
if(bj.isGameFinished()) bj.bankLastTurn();
} catch (EmptyDeckException ex) {
JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
System.exit(-1);
}
try{
updatePlayerPanel();
updateBankPanel();
} catch (FileNotFoundException ex) {
JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
System.exit(-1);
}

}
break;

case "No More Card":
if(!(bj.isGameFinished())){

try{
bj.bankLastTurn();
} catch (EmptyDeckException ex) {
JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
System.exit(-1);
}
try{
updatePlayerPanel();
updateBankPanel();
} catch (FileNotFoundException ex) {
JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
System.exit(-1);
}

}
break;

case "Reset":
try{
bj.reset();
} catch (EmptyDeckException ex) {
JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
System.exit(-1);
}
try{
updatePlayerPanel();
updateBankPanel();
} catch (FileNotFoundException ex) {
JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
System.exit(-1);
}
break;

default:
break;

}
}

}
