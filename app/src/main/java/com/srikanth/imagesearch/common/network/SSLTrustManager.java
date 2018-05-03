package com.srikanth.imagesearch.common.network;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.X509TrustManager;

/**
 * The trust manager for X509 certificates to be used to perform authentication
 * for secure sockets. Instance of this interface manage which X509 certificates
 * may be used to authenticate the remote side of a secure socket. Decisions may
 * be based on trusted certificate authorities, certificate revocation lists,
 * online status checking or other means.
 * 
 */
public class SSLTrustManager implements X509TrustManager {
	/*
	 * Checks whether the specified certificate chain (partial or complete) can
	 * be validated and is trusted for client authentication for the specified
	 * authentication type.
	 */
	@Override
	public void checkClientTrusted(X509Certificate[] chain, String authType)
			throws CertificateException {
		//skip
	}

	/*
	 * Checks whether the specified certificate chain (partial or complete) can
	 * be validated and is trusted for server authentication for the specified
	 * key exchange algorithm.
	 */
	@Override
	public void checkServerTrusted(X509Certificate[] chain, String authType)
			throws CertificateException {
		//skip
	}

	/*
	 * Returns the list of certificate issuer authorities which are trusted for
	 * authentication of peers.
	 */
	@Override
	public X509Certificate[] getAcceptedIssuers() {
		return null;
	}
}