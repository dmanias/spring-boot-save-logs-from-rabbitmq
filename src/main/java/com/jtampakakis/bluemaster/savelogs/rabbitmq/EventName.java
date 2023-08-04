package com.jtampakakis.bluemaster.savelogs.rabbitmq;

public enum EventName {
	// Administration events
	AdminResellerCreated("bithive.portal.admin.reseller.created"),
	AdminResellerUpdated("bithive.portal.admin.reseller.updated"),
	AdminResellerDeleted("bithive.portal.admin.reseller.deleted"),
	
	AdminCustomerCreated("bithive.portal.admin.customer.created"),
	AdminCustomerUpdated("bithive.portal.admin.customer.updated"),
	AdminCustomerDeleted("bithive.portal.admin.customer.deleted"),
	
	AdminRemoteCreated("bithive.portal.admin.remote.created"),
	AdminRemoteUpdated("bithive.portal.admin.remote.updated"),
	AdminRemoteDeleted("bithive.portal.admin.remote.deleted"),
	
	AdminHardwareCreated("bithive.portal.admin.hardware.created"),
	AdminHardwareUpdated("bithive.portal.admin.hardware.updated"),
	AdminHardwareDeleted("bithive.portal.admin.hardware.deleted"),
	
	AdminUserCreated("bithive.portal.admin.user.created"),
	AdminUserUpdated("bithive.portal.admin.user.updated"),
	AdminUserPasswordReset("bithive.portal.admin.user.password.reset"),
	AdminUserDeleted("bithive.portal.admin.user.deleted"),
	
	// =======================================================================================
	
	RouterConfigOvsCreated("bithive.portal.config.ovs.created"),
	RouterConfigOvsUpdated("bithive.portal.config.ovs.updated"),
	RouterConfigOvsDeleted("bithive.portal.config.ovs.deleted"),
	
	RouterConfigOutboundCreated("bithive.portal.config.outbound.created"),
	RouterConfigOutboundUpdated("bithive.portal.config.outbound.updated"),
	RouterConfigOutboundDeleted("bithive.portal.config.outbound.deleted"),
	
	RouterConfigInboundCreated("bithive.portal.config.inbound.created"),
	RouterConfigInboundUpdated("bithive.portal.config.inbound.updated"),
	RouterConfigInboundDeleted("bithive.portal.config.inbound.deleted"),
	
	RouterConfigDHCPServerCreated("bithive.portal.config.dhcpserver.created"),
	RouterConfigDHCPServerUpdated("bithive.portal.config.dhcpserver.updated"),
	RouterConfigDHCPServerDeleted("bithive.portal.config.dhcpserver.deleted"),
	
	RouterConfigDNSConfigurationUpdated("bithive.portal.config.dnsconfiguration.updated"),
	
	RouterConfigInterfaceCreated("bithive.portal.config.interface.created"),
	RouterConfigInterfaceUpdated("bithive.portal.config.interface.updated"),
	RouterConfigInterfaceDeleted("bithive.portal.config.interface.deleted"),
	
	RouterConfigPush("bithive.portal.config.push"),
	RouterConfigBuilt("bithive.portal.config.built"),
	VPNClientUpdated("bithive.portal.config.vpnclient.updated"),
	
	
	// Received messages
	//AdminAppActivated("admin.app.activated"),
	//AdminAppDeactivated("admin.app.deactivated"),
	//AdminAppSuspended("admin.app.suspended"),
	//AdminAppResumed("admin.app.resumed"),
	
	RouterConfigUpdated("bithive.portal.config.updated"),
	RouterAdditionalConfigUpdated("bithive.portal.additionalConfig.updated"),
	RouterInformationUpdated("bithive.portal.information.updated"),
	
	LicensingLicenseExpiryUpdated("bithive.portal.license.expiryUpdated"),
	
	// Sent messages
	LicensingLicenseCreated("bithive.portal.license.created"),
	LicensingLicenseDeleted("bithive.portal.license.deleted"),
	LicensingLicenseRevoked("bithive.portal.license.revoked"),
	LicensingLicenseEnabled("bithive.portal.license.enabled"),
	
	RouterConfigSaved("bithive.portal.config.saved"),
	RouterAdditionalConfigSaved("bithive.portal..additionalConfig.saved"),
	RouterInformationSaved("bithive.portal..information.saved"),
	RouterConfigStatusUpdated("bithive.portal.config.status.updated"),
	
	LicensingLicenseExpirySaved("bithive.portal.license.expirySaved"),
	
	//===============================================================================
	CrewPinsProfileSaved("bithive.portal.crewpins.profile.saved"),
	CrewPinsProfileUpdated("bithive.portal.crewpins.profile.updated"),
	CrewPinsProfileDeleted("bithive.portal.crewpins.profile.deleted"),
	
	CrewPinsOrderSaved("bithive.portal.crewpins.order.saved"),
	CrewPinsOrderEnabled("bithive.portal.crewpins.order.enabled"),
	CrewPinsOrderDisabled("bithive.portal.crewpins.order.disabled"),
	CrewPinsOrderSync("bithive.portal.crewpins.order.sync"),
	
	PINSessionStoped("bithive.smartbox.crewpins.session.stopped");
	
	public final String label;

    private EventName(String label) {
        this.label = label;
    }
	
	
}
