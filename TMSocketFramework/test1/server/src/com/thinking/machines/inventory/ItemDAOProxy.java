package com.thinking.machines.inventory;
import com.thinking.machines.inventory.dl.interfaces.*;
import com.thinking.machines.inventory.dl.*;
import com.thinking.machines.inventory.dl.exception.*;
import java.util.*;
import com.thinking.machines.socket.framework.annotations.*;
@Path("/item")
public class ItemDAOProxy
{
private ItemDAOInterface itemDAOInterface=new ItemDAO();
@Path("add")
public void add(ItemDTOInterface itemDTOInterface) throws DAOException
{
itemDAOInterface.add(itemDTOInterface);
}
@Path("update")
public void update(ItemDTOInterface itemDTOInterface) throws DAOException
{
itemDAOInterface.update(itemDTOInterface);
}
@Path("delete")
public void delete(int code) throws DAOException
{
itemDAOInterface.delete(code);
}
@Path("getByCode")
public ItemDTOInterface get(int code) throws DAOException
{
return itemDAOInterface.get(code);
}
@Path("getAll")
public ArrayList<ItemDTOInterface> getAll() throws DAOException
{
return itemDAOInterface.getAll();
}
@Path("getCount")
public long getCount() throws DAOException
{
return itemDAOInterface.getCount();
}
@Path("codeExists")
public boolean codeExists(int code) throws DAOException
{
return itemDAOInterface.codeExists(code);
}
@Path("nameExists")
public boolean nameExists(String name) throws DAOException
{
return itemDAOInterface.nameExists(name);
}
}