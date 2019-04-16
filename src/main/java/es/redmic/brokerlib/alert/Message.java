package es.redmic.brokerlib.alert;

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

import org.apache.avro.Schema;
import org.apache.avro.specific.SpecificRecord;
import org.apache.avro.specific.SpecificRecordBase;

public class Message extends SpecificRecordBase implements SpecificRecord {

	// @formatter:off

	public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser()
			.parse("{\"type\":\"record\",\"name\":\"Message\",\"namespace\":\"es.redmic.brokerlib.alert\","
					+ "\"fields\":["
					+ "{\"name\":\"to\",\"type\": \"string\"},"
					+ "{\"name\":\"subject\",\"type\": \"string\"}," 
					+ "{\"name\":\"message\",\"type\":\"string\"},"
					+ "{\"name\":\"type\",\"type\":\"string\"}]}");
	// @formatter:on

	private String to;

	private String subject;

	private String message;

	private String type;

	public Message() {
	}

	public Message(String to, String subject, String message, String type) {
		assert to != null;
		assert subject != null;
		assert message != null;
		assert type != null;
		this.to = to;
		this.subject = subject;
		this.message = message;
		this.type = type;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public Schema getSchema() {
		return SCHEMA$;
	}

	@Override
	public java.lang.Object get(int field$) {
		switch (field$) {
		case 0:
			return to.toString();
		case 1:
			return subject.toString();
		case 2:
			return message.toString();
		case 3:
			return type.toString();
		default:
			throw new org.apache.avro.AvroRuntimeException("Bad index");
		}
	}

	// Used by DatumReader. Applications should not call.
	@Override
	public void put(int field$, java.lang.Object value$) {
		switch (field$) {
		case 0:
			to = value$.toString();
			break;
		case 1:
			subject = value$.toString();
			break;
		case 2:
			message = value$.toString();
			break;
		case 3:
			type = value$.toString();
			break;
		default:
			throw new org.apache.avro.AvroRuntimeException("Bad index");
		}
	}

}
