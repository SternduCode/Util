package com.sterndu.util;

import java.io.*;
import java.nio.file.attribute.FileTime;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.jar.*;
import java.util.zip.*;

public class ZipUtil {

	private static boolean writeEntry(ZipOutputStream zos, File file, String name) {
		if (file.isDirectory()) {
			File[] f = file.listFiles();
			for (File file2 : f)
				if (!writeEntry(zos, file2, name + file.getName() + "/"))
					return false;
		} else {
			ZipEntry z = new ZipEntry(name + file.getName());
			z.setLastModifiedTime(FileTime.fromMillis(file.lastModified()));
			try {
				zos.putNextEntry(z);
				FileInputStream fis = new FileInputStream(file);
				byte[] b = new byte[fis.available()];
				int i = fis.read(b);
				fis.close();
				zos.write(b, 0, i);
				zos.closeEntry();
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}

	public static boolean DeZip(File zipfile, File targetfolder) {
		if (zipfile.exists() & targetfolder.isDirectory()) {
			if (!targetfolder.exists())
				targetfolder.mkdirs();
			if (zipfile.isFile()) {
				String name = zipfile.getName();
				if (name.endsWith(".zip"))
					try {
						return DeZip(new ZipFile(zipfile), targetfolder);
					} catch (IOException e) {
						e.printStackTrace();
						return false;
					}
				else if (name.endsWith(".jar"))
					try {
						return DeZip(new JarFile(zipfile), targetfolder);
					} catch (IOException e) {
						e.printStackTrace();
						return false;
					}
			}
		}
		return false;
	}

	public static boolean DeZip(File zipfile, String targetfolder) {
		return DeZip(zipfile, new File(targetfolder));
	}

	public static boolean DeZip(JarFile zipfile, File targetfolder) {
		AtomicBoolean ab = new AtomicBoolean(true);
		zipfile.stream().parallel().forEach(zipEntry -> {
			try {
				File f = new File(targetfolder, zipEntry.getName());
				if (zipEntry.isDirectory())
					f.mkdirs();
				else {
					if (!f.getParentFile().exists())
						f.getParentFile().mkdirs();
					f.createNewFile();
					InputStream is = zipfile.getInputStream(zipEntry);
					byte[] b = new byte[is.available()];
					int i = is.read(b);
					FileOutputStream fos = new FileOutputStream(f);
					fos.write(b, 0, i);
					fos.flush();
					fos.close();
					f.setLastModified(zipEntry.getLastModifiedTime().toMillis());
				}
			} catch (IOException e) {
				e.printStackTrace();
				ab.set(false);
			}
		});
		return ab.get();
	}

	public static boolean DeZip(JarFile zipfile, String targetfolder) {
		return DeZip(zipfile, new File(targetfolder));
	}

	public static boolean DeZip(String zipfile, File targetfolder) {
		return DeZip(new File(zipfile), targetfolder);
	}

	public static boolean DeZip(String zipfile, String targetfolder) {
		return DeZip(new File(zipfile), new File(targetfolder));
	}

	public static boolean DeZip(ZipFile zipfile, File targetfolder) {
		AtomicBoolean ab = new AtomicBoolean(true);
		zipfile.stream().parallel().forEach(zipEntry -> {
			try {
				File f = new File(targetfolder, zipEntry.getName());
				if (zipEntry.isDirectory())
					f.mkdirs();
				else {
					if (!f.getParentFile().exists())
						f.getParentFile().mkdirs();
					f.createNewFile();
					InputStream is = zipfile.getInputStream(zipEntry);
					byte[] b = new byte[is.available()];
					int i = is.read(b);
					FileOutputStream fos = new FileOutputStream(f);
					fos.write(b, 0, i);
					fos.flush();
					fos.close();
					f.setLastModified(zipEntry.getLastModifiedTime().toMillis());
				}
			} catch (IOException e) {
				e.printStackTrace();
				ab.set(false);
			}
		});
		return ab.get();
	}

	public static boolean DeZip(ZipFile zipfile, String targetfolder) {
		return DeZip(zipfile, new File(targetfolder));
	}

	public static boolean Zip(File folder, File zipfile) {
		if (folder.exists() && folder.isDirectory()) {
			if (!zipfile.exists())
				try {
					zipfile.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
					return false;
				}
			if (zipfile.isFile()) {
				String name = zipfile.getName();
				if (name.endsWith(".zip"))
					try {
						return Zip(new ZipOutputStream(new FileOutputStream(zipfile)), folder);
					} catch (IOException e) {
						e.printStackTrace();
						return false;
					}
				else if (name.endsWith(".jar"))
					try {
						return Zip(new JarOutputStream(new FileOutputStream(zipfile)), folder);
					} catch (IOException e) {
						e.printStackTrace();
						return false;
					}
			}
		}
		return false;
	}

	public static boolean Zip(File folder, String zipfile) {
		return Zip(folder, new File(zipfile));
	}

	public static boolean Zip(JarOutputStream jos, File folder) {
		File[] f = folder.listFiles();
		for (File file : f)
			if (!writeEntry(jos, file, ""))
				return false;
		try {
			jos.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static boolean Zip(String folder, File zipfile) {
		return Zip(new File(folder), zipfile);
	}

	public static boolean Zip(String folder, String zipfile) {
		return Zip(new File(folder), new File(zipfile));
	}

	public static boolean Zip(ZipOutputStream zos, File folder) {
		File[] f = folder.listFiles();
		for (File file : f)
			if (!writeEntry(zos, file, ""))
				return false;
		try {
			zos.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
