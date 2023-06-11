@file:JvmName("ZipUtil")
package com.sterndu.util

import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.nio.file.attribute.FileTime
import java.util.concurrent.atomic.AtomicBoolean
import java.util.jar.JarEntry
import java.util.jar.JarFile
import java.util.jar.JarOutputStream
import java.util.zip.ZipEntry
import java.util.zip.ZipFile
import java.util.zip.ZipOutputStream

object ZipUtil {
	private fun writeEntry(zos: ZipOutputStream, file: File, name: String): Boolean {
		if (file.isDirectory) {
			val f = file.listFiles()
			for (file2 in f) if (!writeEntry(zos, file2, name + file.name + "/")) return false
		} else {
			val z = ZipEntry(name + file.name)
			z.setLastModifiedTime(FileTime.fromMillis(file.lastModified()))
			try {
				zos.putNextEntry(z)
				val fis = FileInputStream(file)
				val b = ByteArray(fis.available())
				val i = fis.read(b)
				fis.close()
				zos.write(b, 0, i)
				zos.closeEntry()
			} catch (e: IOException) {
				e.printStackTrace()
				return false
			}
		}
		return true
	}

	fun DeZip(zipfile: File, targetfolder: File): Boolean {
		if (zipfile.exists() and targetfolder.isDirectory) {
			if (!targetfolder.exists()) targetfolder.mkdirs()
			if (zipfile.isFile) {
				val name = zipfile.name
				if (name.endsWith(".zip")) return try {
					DeZip(ZipFile(zipfile), targetfolder)
				} catch (e: IOException) {
					e.printStackTrace()
					false
				} else if (name.endsWith(".jar")) return try {
					DeZip(JarFile(zipfile), targetfolder)
				} catch (e: IOException) {
					e.printStackTrace()
					false
				}
			}
		}
		return false
	}

	fun DeZip(zipfile: File, targetfolder: String?): Boolean {
		return DeZip(zipfile, File(targetfolder))
	}

	fun DeZip(zipfile: JarFile, targetfolder: File?): Boolean {
		val ab = AtomicBoolean(true)
		zipfile.stream().parallel().forEach { zipEntry: JarEntry ->
			try {
				val f = File(targetfolder, zipEntry.name)
				if (zipEntry.isDirectory) f.mkdirs() else {
					if (!f.parentFile.exists()) f.parentFile.mkdirs()
					f.createNewFile()
					val `is` = zipfile.getInputStream(zipEntry)
					val b = ByteArray(`is`.available())
					val i = `is`.read(b)
					val fos = FileOutputStream(f)
					fos.write(b, 0, i)
					fos.flush()
					fos.close()
					f.setLastModified(zipEntry.lastModifiedTime.toMillis())
				}
			} catch (e: IOException) {
				e.printStackTrace()
				ab.set(false)
			}
		}
		return ab.get()
	}

	fun DeZip(zipfile: JarFile, targetfolder: String?): Boolean {
		return DeZip(zipfile, File(targetfolder))
	}

	fun DeZip(zipfile: String?, targetfolder: File): Boolean {
		return DeZip(File(zipfile), targetfolder)
	}

	fun DeZip(zipfile: String?, targetfolder: String?): Boolean {
		return DeZip(File(zipfile), File(targetfolder))
	}

	fun DeZip(zipfile: ZipFile, targetfolder: File?): Boolean {
		val ab = AtomicBoolean(true)
		zipfile.stream().parallel().forEach { zipEntry: ZipEntry ->
			try {
				val f = File(targetfolder, zipEntry.name)
				if (zipEntry.isDirectory) f.mkdirs() else {
					if (!f.parentFile.exists()) f.parentFile.mkdirs()
					f.createNewFile()
					val `is` = zipfile.getInputStream(zipEntry)
					val b = ByteArray(`is`.available())
					val i = `is`.read(b)
					val fos = FileOutputStream(f)
					fos.write(b, 0, i)
					fos.flush()
					fos.close()
					f.setLastModified(zipEntry.lastModifiedTime.toMillis())
				}
			} catch (e: IOException) {
				e.printStackTrace()
				ab.set(false)
			}
		}
		return ab.get()
	}

	fun DeZip(zipfile: ZipFile, targetfolder: String?): Boolean {
		return DeZip(zipfile, File(targetfolder))
	}

	fun Zip(folder: File, zipfile: File): Boolean {
		if (folder.exists() && folder.isDirectory) {
			if (!zipfile.exists()) try {
				zipfile.createNewFile()
			} catch (e: IOException) {
				e.printStackTrace()
				return false
			}
			if (zipfile.isFile) {
				val name = zipfile.name
				if (name.endsWith(".zip")) return try {
					Zip(ZipOutputStream(FileOutputStream(zipfile)), folder)
				} catch (e: IOException) {
					e.printStackTrace()
					false
				} else if (name.endsWith(".jar")) return try {
					Zip(JarOutputStream(FileOutputStream(zipfile)), folder)
				} catch (e: IOException) {
					e.printStackTrace()
					false
				}
			}
		}
		return false
	}

	fun Zip(folder: File, zipfile: String?): Boolean {
		return Zip(folder, File(zipfile))
	}

	fun Zip(jos: JarOutputStream, folder: File): Boolean {
		val f = folder.listFiles()
		for (file in f) if (!writeEntry(jos, file, "")) return false
		try {
			jos.close()
		} catch (e: IOException) {
			e.printStackTrace()
			return false
		}
		return true
	}

	fun Zip(folder: String?, zipfile: File): Boolean {
		return Zip(File(folder), zipfile)
	}

	fun Zip(folder: String?, zipfile: String?): Boolean {
		return Zip(File(folder), File(zipfile))
	}

	fun Zip(zos: ZipOutputStream, folder: File): Boolean {
		val f = folder.listFiles()
		for (file in f) if (!writeEntry(zos, file, "")) return false
		try {
			zos.close()
		} catch (e: IOException) {
			e.printStackTrace()
			return false
		}
		return true
	}
}
