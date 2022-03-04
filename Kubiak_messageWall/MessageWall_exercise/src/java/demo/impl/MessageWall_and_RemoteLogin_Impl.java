package demo.impl;

import demo.spec.Message;
import demo.spec.MessageWall;
import demo.spec.RemoteLogin;
import demo.spec.UserAccess;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MessageWall_and_RemoteLogin_Impl implements RemoteLogin, MessageWall {

  private List<Message> messages;
  private HashMap list_users = ddbb_users();
  
  public MessageWall_and_RemoteLogin_Impl() {
    messages = new ArrayList<Message>();
  }

  @Override
  public UserAccess connect(String usr, String passwd) {
      if (list_users.get(usr) != null && list_users.get(usr).equals(passwd)) {
          return new UserAccess_Impl(this, usr);
      }
      else return null;
  }

  @Override
  public void put(String user, String msg) {
      messages.add(new Message_Impl(user, msg));
  }

  @Override
  public boolean delete(String user, int index) {
      
      if (messages.size() > index ) 
      {
            messages.remove(index);
            return true;
      } else {
            return false;
        }
  }

  @Override
  public Message getLast() {
      if (messages.isEmpty()) {
            return new Message_Impl("No user", "No message");
        } else {
            return messages.get(messages.size() - 1);
        }
  }

  @Override
  public int getNumber() {
      return messages.size();
  }

  @Override
  public List<Message> getAllMessages() {
      return messages;
  }
  
  public static HashMap ddbb_users() {
        HashMap list_users = new HashMap();
        list_users.put("ecomm", "ecomm");
        list_users.put("admin", "admin");
        list_users.put("marcin", "kubiak");
        return list_users;
    }

}
