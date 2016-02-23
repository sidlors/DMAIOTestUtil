package mx.gob.sre.nec.util.main;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;

public class UnzipFile {

	public void unzipFiles(List<File> zips) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		for (File file : zips) {
			try {
				ZipFile zipFile = new ZipFile(file);
				if (zipFile.isEncrypted()) {
					zipFile.setPassword(getMD5Digest(zipFile.getFile().getName().replace(".zip", "")));
				}
				zipFile.extractAll(file.getParent());
			} catch (ZipException e) {
				e.printStackTrace();
			}
		}
	}

	public String getMD5Digest(String text) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		MessageDigest md;
		md = MessageDigest.getInstance("MD5");
		byte[] md5hash = new byte[32];
		md.update(text.getBytes("iso-8859-1"), 0, text.length());
		md5hash = md.digest();
		return convertToHex(md5hash);
	}

	private static String convertToHex(byte[] data) {
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < data.length; i++) {
			int halfbyte = (data[i] >>> 4) & 0x0F;
			int two_halfs = 0;
			do {
				if ((0 <= halfbyte) && (halfbyte <= 9))
					buf.append((char) ('0' + halfbyte));
				else
					buf.append((char) ('a' + (halfbyte - 10)));
				halfbyte = data[i] & 0x0F;
			} while (two_halfs++ < 1);
		}
		return buf.toString();
	}

}
