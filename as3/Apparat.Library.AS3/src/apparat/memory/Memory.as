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
 * Copyright (C) 2009 Joa Ebert
 * http://www.joa-ebert.com/
 * 
 */

package apparat.memory 
{
	import flash.system.ApplicationDomain;
	import flash.utils.ByteArray;

	/**
	 * The Memory class is a high-level API for the Alchemy operations.
	 * 
	 * <p>All calls to Memory work without running the optimizer since it
	 * implements the standard behaviour by default as well. This is of course
	 * slow.</p>
	 * 
	 * <p>Calls to the Memory class will be inlined wherever possible. This
	 * means if you access the Memory class using a runtime namespace it will
	 * not be possible for the optimizer to inline the method call. In that
	 * case you will only benifit from the fact that the code inside the Memory
	 * class will be optimized using the Alchemy operations.</p>
	 * 
	 * @author Joa Ebert
	 */
	public final class Memory {
		/**
		 * The current application domain.
		 * @private
		 */
		private static const applicationDomain: ApplicationDomain = ApplicationDomain.currentDomain;

		/**
		 * Selects a ByteArray object as the current memory.
		 * 
		 * @param byteArray The ByteArray object to work with.
		 */
		public static function select(byteArray: ByteArray): void {
			applicationDomain.domainMemory = byteArray;
		}
		
		/**
		 * Writes a byte to the memory.
		 * 
		 * @param address The address in memory.
		 * @param value The value to write.
		 */
		public static function writeByte(value: int, address: int): void {
			var domainMemory: ByteArray = applicationDomain.domainMemory;

			domainMemory.position = address;
			domainMemory.writeByte(value);
		}
		
		/**
		 * Writes a short to the memory.
		 * 
		 * @param address The address in memory.
		 * @param value The value to write.
		 */
		public static function writeShort(value: int, address: int): void {
			var domainMemory: ByteArray = applicationDomain.domainMemory;

			domainMemory.position = address;
			domainMemory.writeShort(value);
		}
		
		/**
		 * Writes an integer to the memory.
		 * 
		 * @param address The address in memory.
		 * @param value The value to write.
		 */
		public static function writeInt(value: int, address: int): void {
			var domainMemory: ByteArray = applicationDomain.domainMemory;

			domainMemory.position = address;
			domainMemory.writeInt(value);
		}
		
		/**
		 * Writes a float to the memory.
		 * 
		 * @param address The address in memory.
		 * @param value The value to write.
		 */
		public static function writeFloat(value: Number, address: int): void {
			var domainMemory: ByteArray = applicationDomain.domainMemory;

			domainMemory.position = address;
			domainMemory.writeFloat(value);
		}
		
		/**
		 * Writes a double to the memory.
		 * 
		 * @param address The address in memory.
		 * @param value The value to write.
		 */
		public static function writeDouble(value: Number, address: int): void {
			var domainMemory: ByteArray = applicationDomain.domainMemory;

			domainMemory.position = address;
			domainMemory.writeDouble(value);
		}
		
		/**
		 * Reads an unsigned byte from the memory.
		 * 
		 * @param address The address in memory.
		 * @return Unsigned byte at given address in memory.
		 */
		public static function readUnsignedByte(address: int): int {
			var domainMemory: ByteArray = applicationDomain.domainMemory;

			domainMemory.position = address;
			return domainMemory.readUnsignedByte();
		}
		
		/**
		 * Reads an unsigned short from the memory.
		 * 
		 * @param address The address in memory.
		 * @return Unsigned short at given address in memory.
		 */
		public static function readUnsignedShort(address: int): int {
			var domainMemory: ByteArray = applicationDomain.domainMemory;

			domainMemory.position = address;
			return domainMemory.readUnsignedShort();
		}
		
		/**
		 * Reads a signed integer from the memory.
		 * 
		 * @param address The address in memory.
		 * @return Signed integer at given address in memory.
		 */
		public static function readInt(address: int): int {
			var domainMemory: ByteArray = applicationDomain.domainMemory;

			domainMemory.position = address;
			return domainMemory.readInt();
		}
		
		/**
		 * Reads a float from the memory.
		 * 
		 * @param address The address in memory.
		 * @return Float at given address in memory.
		 */
		public static function readFloat(address: int): Number {
			var domainMemory: ByteArray = applicationDomain.domainMemory;

			domainMemory.position = address;
			return domainMemory.readFloat();
		}
		
		/**
		 * Reads a double from the memory.
		 * 
		 * @param address The address in memory.
		 * @return Double at given address in memory.
		 */
		public static function readDouble(address: int): Number {
			var domainMemory: ByteArray = applicationDomain.domainMemory;

			domainMemory.position = address;
			return domainMemory.readDouble();
		}
		
		/**
		 * Sign-extension from 1 to 32 bits.
		 * 
		 * @param value The value to extend.
		 * @return The extended value.
		 */
		public static function signExtend1(value: int): int {
			if(0 != (value & 0x1)) {
				value &= 0x0;
				value -= 0x1;
			}
			
			return value;
		}
		
		/**
		 * Sign-extension from 8 to 32 bits.
		 * 
		 * @param value The value to extend.
		 * @return The extended value.
		 */
		public static function signExtend8(value: int): int {
			if(0 != (value & 0x80)) {
				value &= 0x7f;
				value -= 0x80;
			}
			
			return value;
		}
		
		/**
		 * Sign-extension from 16 to 32 bits.
		 * 
		 * @param value The value to extend.
		 * @return The extended value.
		 */
		public static function signExtend16(value: int): int {
			if(0 != (value & 0x8000)) {
				value &= 0x7fff;
				value -= 0x8000;
			}
			
			return value;
		}
		
		/**
		 * @private
		 */
		public function Memory() {
			throw new Error( 'Can not instantiate Memory object.' );
		}
	}
}
