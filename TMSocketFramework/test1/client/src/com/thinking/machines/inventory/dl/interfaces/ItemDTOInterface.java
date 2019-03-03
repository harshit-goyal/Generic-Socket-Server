package com.thinking.machines.inventory.dl.interfaces;
import java.io.*;
public interface ItemDTOInterface extends Serializable,Comparable<ItemDTOInterface>
{
public void setCode(int Code);
public void setName(String name);
public void setPrice(int price);
public void setCategory(String category);
public String getName();
public int getCode();
public String getCategory();
public int getPrice();
}
