package es.redmic.brokerlib.avro.common;

/*-
 * #%L
 * broker-lib
 * %%
 * Copyright (C) 2019 REDMIC Project / Server
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import java.util.HashMap;
import java.util.Map;

import org.apache.avro.util.Utf8;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class EventError extends Event {

	protected String exceptionType;

	protected Map<String, String> arguments;

	public EventError(String type) {
		super(type);
	}

	public EventError(String type, String exceptionType) {
		super(type);
		this.exceptionType = exceptionType;
	}

	public EventError(String type, String exceptionType, Map<String, String> arguments) {
		super(type);
		this.exceptionType = exceptionType;
		this.arguments = arguments;
	}

	public String getExceptionType() {
		return exceptionType;
	}

	public void setExceptionType(String exceptionType) {
		this.exceptionType = exceptionType;
	}

	public Map<String, String> getArguments() {
		return arguments;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void setArguments(Map arguments) {

		if (arguments != null)
			this.arguments = utf8ToString((HashMap<Utf8, String>) arguments);
	}

	@Override
	public Object get(int field$) {
		switch (field$) {
		case 0:
			return getExceptionType();
		case 1:
			return getArguments();
		case 2:
			return getAggregateId();
		case 3:
			return getVersion();
		case 4:
			return getType();
		case 5:
			return getDate().getMillis();
		case 6:
			return getSessionId();
		case 7:
			return getUserId();
		case 8:
			return getId();
		default:
			throw new org.apache.avro.AvroRuntimeException("Bad index");
		}
	}

	@SuppressWarnings({ "rawtypes" })
	@Override
	public void put(int field$, Object value$) {
		switch (field$) {
		case 0:
			setExceptionType(value$.toString());
			break;
		case 1:
			setArguments((Map) value$);
			break;
		case 2:
			setAggregateId(value$.toString());
			break;
		case 3:
			setVersion((java.lang.Integer) value$);
			break;
		case 4:
			setType(value$.toString());
			break;
		case 5:
			setDate(new DateTime(value$, DateTimeZone.UTC));
			break;
		case 6:
			setSessionId(value$.toString());
			break;
		case 7:
			setUserId(value$.toString());
			break;
		case 8:
			setId(value$.toString());
			break;
		default:
			throw new org.apache.avro.AvroRuntimeException("Bad index");
		}
	}

	// TODO: Investigar la conversión, especificar a avro que por defecto use String
	// (es menos eficiente)
	@JsonIgnore
	private HashMap<String, String> utf8ToString(HashMap<?, ?> value) {
		HashMap<String, String> aux = new HashMap<>();
		for (Object key : value.keySet()) {
			aux.put(key.toString(), value.get(key).toString());
		}
		return aux;
	}

	@JsonIgnore
	public static String getFailEventSchema() {
		// @formatter:off
		return "{\"name\":\"exceptionType\",\"type\":\"string\"},"
				+ "{\"name\":\"arguments\",\"type\":[\"null\", {\"type\":\"map\",\"values\":\"string\"}]}";
		// @formatter:on
	}
}
