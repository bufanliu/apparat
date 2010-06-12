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

package com.joa_ebert.apparat.taas.constants;

import com.joa_ebert.apparat.abc.AbcEnvironment;
import com.joa_ebert.apparat.abc.MethodBody;
import com.joa_ebert.apparat.abc.bytecode.Bytecode;
import com.joa_ebert.apparat.abc.bytecode.operations.PushString;
import com.joa_ebert.apparat.taas.TaasConstant;
import com.joa_ebert.apparat.taas.TaasException;
import com.joa_ebert.apparat.taas.TaasValue;
import com.joa_ebert.apparat.taas.types.StringType;
import com.joa_ebert.apparat.taas.types.TaasType;

/**
 * 
 * @author Joa Ebert
 * 
 */
public class TaasString extends TaasConstant
{
	public final String value;

	public TaasString( final String value )
	{
		super( StringType.INSTANCE );

		this.value = value;
	}

	@Override
	public TaasValue dup()
	{
		return new TaasString( value );
	}

	@Override
	protected void emitOps( final AbcEnvironment environment,
			final MethodBody body, final Bytecode code )
	{
		code.add( new PushString( value ) );
	}

	@Override
	public String toString()
	{
		return "[TaasString value: " + value + "]";
	}

	@Override
	public TaasConstant widen( final TaasType type )
	{
		if( type == StringType.INSTANCE )
		{
			return this;
		}
		else
		{
			throw new TaasException( "Can not convert from " + getType()
					+ " to " + type );
		}
	}
}
