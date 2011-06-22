package edu.columbia.cs.psl.halo.server;

public class UserServiceProxy implements edu.columbia.cs.psl.halo.server.UserService {
  private String _endpoint = null;
  private edu.columbia.cs.psl.halo.server.UserService userService = null;
  
  public UserServiceProxy() {
    _initUserServiceProxy();
  }
  
  public UserServiceProxy(String endpoint) {
    _endpoint = endpoint;
    _initUserServiceProxy();
  }
  
  private void _initUserServiceProxy() {
    try {
      userService = (new edu.columbia.cs.psl.halo.server.UserServiceServiceLocator()).getUserServicePort();
      if (userService != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)userService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)userService)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (userService != null)
      ((javax.xml.rpc.Stub)userService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public edu.columbia.cs.psl.halo.server.UserService getUserService() {
    if (userService == null)
      _initUserServiceProxy();
    return userService;
  }
  
  public java.lang.String test() throws java.rmi.RemoteException{
    if (userService == null)
      _initUserServiceProxy();
    return userService.test();
  }
  
  
}