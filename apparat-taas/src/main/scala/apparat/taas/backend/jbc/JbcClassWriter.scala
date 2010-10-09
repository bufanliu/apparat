/*
 * This file is part of Apparat.
 *
 * Apparat is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Apparat is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Apparat. If not, see <http://www.gnu.org/licenses/>.
 *
 * Copyright (C) 2010 Joa Ebert
 * http://www.joa-ebert.com/
 *
 */
package apparat.taas.backend.jbc

import apparat.utils.IO._
import java.io.{FileOutputStream => JFileOutputStream, File => JFile}
import apparat.log.SimpleLog

/**
 * @author Joa Ebert
 */
class JbcClassWriter(map: Map[String, Array[Byte]]) extends SimpleLog {
	def write(parent: JFile) = {
		parent.mkdirs()
		assume(parent.isDirectory)

		val parentPath = parent.getAbsolutePath+JFile.separator
		
		for((name, data) <- map) {
			val dir = new JFile(parentPath+dirname(name))
			val file = new JFile(parentPath+filename(name))
			dir.mkdirs()
			
			log.debug("Writing %s to %s.", name, file.toString)
			using(new JFileOutputStream(file)) { _ write data }
		}
	}

	def dirname(name: String): String = name lastIndexOf '.' match {
		case -1 => ""
		case n => name.substring(0, n).replaceAll("\\.", "\\"+JFile.separator)
	}

	def filename(name: String): String = name.replaceAll("\\.", "\\"+JFile.separator)+".class"
}