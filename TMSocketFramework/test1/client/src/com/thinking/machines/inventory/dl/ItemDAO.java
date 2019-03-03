package com.thinking.machines.inventory.dl;
import com.thinking.machines.inventory.dl.exception.*;
import com.thinking.machines.inventory.dl.interfaces.*;
import com.thinking.machines.socket.framework.client.*;
import java.util.*;
public class ItemDAO implements ItemDAOInterface
{
TMClient tmClient=TMClient.getInstance("localhost",5000);
public void add(ItemDTOInterface itemDTOInterface) throws DAOException
{
try
{
String path="/item/add";
tmClient.invoke(path,itemDTOInterface);
}catch(Throwable e)
{
throw new DAOException(e.getMessage());
}
}
public void update(ItemDTOInterface itemDTOInterface) throws DAOException
{
try
{
String path="/item/update";
tmClient.invoke(path,itemDTOInterface);
}catch(Throwable e)
{
throw new DAOException(e.getMessage());
}
}
public void delete(int code) throws DAOException
{
try
{
String path="/item/delete";
tmClient.invoke(path,code);
}catch(Throwable e)
{
throw new DAOException(e.getMessage());
}
}
public ItemDTOInterface get(int code) throws DAOException
{
try
{
String path="/item/getByCode";
return (ItemDTOInterface)tmClient.invoke(path,code);
}catch(Throwable e)
{
throw new DAOException(e.getMessage());
}
}
public ArrayList<ItemDTOInterface> getAll() throws DAOException
{
try
{
String path="/item/getAll";
return (ArrayList<ItemDTOInterface>)tmClient.invoke(path);
}catch(Throwable e)
{
throw new DAOException(e.getMessage());
}
}
public long getCount() throws DAOException
{
try
{
String path="/item/getCount";
return (Long)tmClient.invoke(path);
}catch(Throwable e)
{
throw new DAOException(e.getMessage());
}

}
public boolean codeExists(int code) throws DAOException
{
try
{
String path="/item/codeExists";
return (Boolean)tmClient.invoke(path,code);
}catch(Throwable e)
{
throw new DAOException(e.getMessage());
}
}
public boolean nameExists(String name) throws DAOException
{
try
{
String path="/item/nameExists";
return (Boolean)tmClient.invoke(path,name);
}catch(Throwable e)
{
throw new DAOException(e.getMessage());
}
}
}