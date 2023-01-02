package com.laptrinhjavaweb.controller.wed;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class Key {
	static PrivateKey privateKey;
	static PublicKey publicKey;

	public static void gennerickey() throws NoSuchAlgorithmException {
		SecureRandom sr = new SecureRandom();
		KeyPairGenerator kpg = KeyPairGenerator.getInstance("DSA");
		kpg.initialize(1024, sr);
		KeyPair keys = kpg.generateKeyPair();
		privateKey = keys.getPrivate();
		publicKey = keys.getPublic();
	}

	public static void savePrivateKey(PrivateKey key, String path) throws NoSuchAlgorithmException, IOException {
		FileOutputStream fos2 = new FileOutputStream(path);
		fos2.write(key.getEncoded());
		fos2.close();

	}

	public static int verify(PublicKey key, byte[] mahoa)
			throws InvalidKeyException, NoSuchAlgorithmException, SignatureException, IOException {
		Signature s = Signature.getInstance("DSA");
		s.initVerify(key);
		String path = "E:\\kknk.pdf";
		FileInputStream fis = new FileInputStream(path);
		byte bytefile[] = new byte[fis.available()];
		fis.close();
		s.update(bytefile);
		boolean check = s.verify(mahoa);
		int re=0;
		if (check) {
			re=1;
		} else {
			re=0;
		}
		return re;

	}

	public static PublicKey publicKeyFromBase64(String base64PublicKey)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		byte[] decoded = Base64.getDecoder().decode(base64PublicKey);

		PublicKey publicKey = KeyFactory.getInstance("DSA").generatePublic(new X509EncodedKeySpec(decoded));
		return publicKey;
	}

	public static PrivateKey getPrivateKey() {
		return privateKey;
	}

	public static void setPrivateKey(PrivateKey privateKey) {
		Key.privateKey = privateKey;
	}

	public static PublicKey getPublicKey() {
		return publicKey;
	}

	public static void setPublicKey(PublicKey publicKey) {
		Key.publicKey = publicKey;
	}

}
