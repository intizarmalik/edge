package com.etip.ezugi.app.service;

public interface ClientService {
	boolean clientExistsByClientIdAndClientSecret(String clientId, String clientSecret);
}
