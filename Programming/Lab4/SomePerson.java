package com.company;

abstract public class SomePerson implements Person {
    private String name;
    private PersonTypes type;

    public SomePerson(String name){
        this.name = name;
    }

    public void say(String s) {
        System.out.print(this + " says: \"" + s + "\"\n");
    }

    public void think(String s) {
        System.out.print(this + " thinks: \"" + s + "\"\n");
    }

    protected void setType(PersonTypes type) {
        this.type = type;
    }

    public String toString() {
        return type + " " + name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PersonTypes getType() {
        return type;
    }
}
