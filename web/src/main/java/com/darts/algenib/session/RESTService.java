package com.darts.algenib.session;


import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jpc on 7/04/2016.
 */
@Singleton
@Lock(LockType.READ)
@Path("/test")
public class RESTService {
    @GET
    public Map<String, Object> test(){
        final Map<String, Object> map = new HashMap<>();
        map.put("response", "server response");
        return map;
    }
}
