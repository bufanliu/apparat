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
package apparat.log.output

import apparat.log._

/**
 * @author Joa Ebert
 */
class ConsoleOutput extends LogOutput {
	override def log(level: LogLevel, message: String) = {
		val stringBuilder = new StringBuilder(message.length + 7)
		stringBuilder append '['
		stringBuilder append (level match {
			case Debug => "DEBUG"
			case Info => "INFO"
			case Warning => "WARNING"
			case Error => "ERROR"
			case Fatal => "FATAL"
			case Off => error("Unreachable by definition.")
		})
		stringBuilder append ']'
		stringBuilder append ' '
		stringBuilder append message

		if(level >= Error) {
			Console.out
		} else {
			Console.err
		} println stringBuilder.toString
	}
}