### Group Design Exercise 
Ian Hanus (ih52@duke.edu),
Lucas Liu (ltl9@duke.edu),
Harry Ross (hgr8@duke.edu)
# RPS Game
1.) Create a class as a hand and instantiate however many you need for the number of players
* Pick a hand
```java
public weapon getWeapon(List<> viableWeapons);
```

* Reset to natural state
```java
public void clearWeapon();
```

2.) Referee
* Scan through file and output viable weapons for user to choose
* Decides who wins
* Report to the scorekeeper
* Reset game
```java
private void reseGame(){
    referee.passWeapons(Players);
    scoreKeeper.reset();
}
```

2.) Scorekeeper 
* Just keep track of score

Actual planning for the cell automata project is in Design_PLAN.

