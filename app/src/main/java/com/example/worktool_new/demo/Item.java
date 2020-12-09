package com.example.worktool_new.demo;

public class Item {
    int Length;
    String name;

    public Item(String name2, int length) {
        this.name = name2;
        this.Length = length;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name2) {
        this.name = name2;
    }

    public int getLength() {
        return this.Length;
    }

    public void setLength(int length) {
        this.Length = length;
    }
}
