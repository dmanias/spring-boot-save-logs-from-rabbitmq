package com.jtampakakis.bluemaster.savelogs.security.config;

import java.util.logging.Logger;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.jtampakakis.bluemaster.savelogs.Startup;
import com.jtampakakis.bluemaster.savelogs.utils.BearerToken;
import com.jtampakakis.bluemaster.savelogs.utils.Rest;
import com.jtampakakis.bluemaster.savelogs.utils.SV;

@Service
public class PreAuthorize {	
	
	private final static Logger LOGGER = Startup.LOGGER;
	
	public boolean hasAccess(String targetType, int targetId) {
		Authentication authentication =SecurityContextHolder.getContext().getAuthentication();
		System.out.println("Entered pre-auth - "+targetType+" - " + targetId+" (User: "+authentication.getName()+")");
		
		
		BearerToken bt = Rest.GetToken(SV.portalClientId, SV.portalClientSecret, SV.portalIp, SV.portalUsername, SV.portalPassword, LOGGER);
		if (bt==null) return false;

		Rest rest = new Rest(SV.webProt+SV.portalIp+"/orchestrator/users/"+targetType+"/"+targetId+"/"+authentication.getName(), bt.getAccess_token(), "application/json", null);
		rest.GET("Bearer", LOGGER);
		
		if (rest.getResponseCode()==200) return true;
		
		return false;
	}
}