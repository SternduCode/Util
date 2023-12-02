@file:JvmName("ZipUtil")
package com.sterndu.util

import java.io.*
import java.nio.file.attribute.FileTime
import java.util.concurrent.atomic.AtomicBoolean
import java.util.jar.*
import java.util.zip.*

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

	fun DeZip(zipFile: File, targetFolder: File): Boolean {
		if (zipFile.exists() and targetFolder.isDirectory) {
			if (!targetFolder.exists()) targetFolder.mkdirs()
			if (zipFile.isFile) {
				val name = zipFile.name
				if (name.endsWith(".zip")) return try {
					DeZip(ZipFile(zipFile), targetFolder)
				} catch (e: IOException) {
					e.printStackTrace()
					false
				} else if (name.endsWith(".jar")) return try {
					DeZip(JarFile(zipFile), targetFolder)
				} catch (e: IOException) {
					e.printStackTrace()
					false
				}
			}
		}
		return false
	}

	fun DeZip(zipFile: File, targetFolder: String): Boolean {
		return DeZip(zipFile, File(targetFolder))
	}

	fun DeZip(zipFile: JarFile, targetFolder: File): Boolean {
		val ab = AtomicBoolean(true)
		zipFile.stream().parallel().forEach { zipEntry: JarEntry ->
			try {
				val f = File(targetFolder, zipEntry.name)
				if (zipEntry.isDirectory) f.mkdirs() else {
					if (!f.parentFile.exists()) f.parentFile.mkdirs()
					f.createNewFile()
					val `is` = zipFile.getInputStream(zipEntry)
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

	fun DeZip(zipFile: JarFile, targetFolder: String): Boolean {
		return DeZip(zipFile, File(targetFolder))
	}

	fun DeZip(zipFile: String, targetFolder: File): Boolean {
		return DeZip(File(zipFile), targetFolder)
	}

	fun DeZip(zipFile: String, targetFolder: String): Boolean {
		return DeZip(File(zipFile), File(targetFolder))
	}

	fun DeZip(zipFile: ZipFile, targetFolder: File): Boolean {
		val ab = AtomicBoolean(true)
		zipFile.stream().parallel().forEach { zipEntry: ZipEntry ->
			try {
				val f = File(targetFolder, zipEntry.name)
				if (zipEntry.isDirectory) f.mkdirs() else {
					if (!f.parentFile.exists()) f.parentFile.mkdirs()
					f.createNewFile()
					val `is` = zipFile.getInputStream(zipEntry)
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

	fun DeZip(zipFile: ZipFile, targetFolder: String): Boolean {
		return DeZip(zipFile, File(targetFolder))
	}

	fun Zip(folder: File, zipFile: File): Boolean {
		if (folder.exists() && folder.isDirectory) {
			if (!zipFile.exists()) try {
				zipFile.createNewFile()
			} catch (e: IOException) {
				e.printStackTrace()
				return false
			}
			if (zipFile.isFile) {
				val name = zipFile.name
				if (name.endsWith(".zip")) return try {
					Zip(ZipOutputStream(FileOutputStream(zipFile)), folder)
				} catch (e: IOException) {
					e.printStackTrace()
					false
				} else if (name.endsWith(".jar")) return try {
					Zip(JarOutputStream(FileOutputStream(zipFile)), folder)
				} catch (e: IOException) {
					e.printStackTrace()
					false
				}
			}
		}
		return false
	}

	fun Zip(folder: File, zipFile: String): Boolean {
		return Zip(folder, File(zipFile))
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

	fun Zip(folder: String, zipFile: File): Boolean {
		return Zip(File(folder), zipFile)
	}

	fun Zip(folder: String, zipFile: String): Boolean {
		return Zip(File(folder), File(zipFile))
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
