package com.thinking.machines.inventory.dl.interfaces;
import com.thinking.machines.inventory.dl.exception.*;
import java.util.*;
public interface ItemDAOInterface
{
public void add(ItemDTOInterface itemDTOInterface) throws DAOException;
public void update(ItemDTOInterface itemDTOInterface) throws DAOException;
public void delete(int code) throws DAOException;
public ItemDTOInterface get(int Code) throws DAOException;
public ArrayList<ItemDTOInterface> getAll() throws DAOException;
public long getCount() throws DAOException;
public boolean codeExists(int Code) throws DAOException;
public boolean nameExists(String name) throws DAOException; 
}