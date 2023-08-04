package com.jtampakakis.bluemaster.savelogs.utils;

public enum Vars {
	
	persistentKeepAlive(null,15),
	serverVtable("on",0),
	clientVtable("off",0),
	serverVpnIp("remotegateway.mybithive.com",0),
	mgmtServerIp("api.mybithive.com:445",0),
	licensingServer("api.mybithive.com:445",0),
	licensingServerIp("api.mybithive.com:445",0),
	checkInterval(null,3600),
	hardwaresClientId("hardware",0),
	hardwaresClientSecret("vagmmuc4xcY28FTS",0), 
	defaultDnsIp("172.16.0.254",0);
	
	public final String val;
	public final int ival;

    private Vars(String val,int ival) {
        this.val = val;
        this.ival=ival;
    }
}
