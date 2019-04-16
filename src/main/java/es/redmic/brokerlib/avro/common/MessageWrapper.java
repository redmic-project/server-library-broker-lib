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

import java.nio.ByteBuffer;

import org.apache.avro.Schema;
import org.apache.avro.specific.SpecificRecord;
import org.apache.avro.specific.SpecificRecordBase;

public class MessageWrapper extends SpecificRecordBase implements SpecificRecord {

	public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser()
			.parse("{\"type\":\"record\",\"name\":\"MessageWrapper\",\"namespace\":\"es.redmic.brokerlib.avro.common\","
					+ "\"fields\":[{\"name\":\"content\",\"type\":[\"bytes\", \"null\"]},"
					+ "{\"name\":\"userId\",\"type\": [\"string\", \"null\"]}, "
					+ "{\"name\":\"actionId\",\"type\":[\"string\", \"null\"]}]}");

	private ByteBuffer content;

	/* Identificador del usuario que envió el mensaje. */
	private String userId;

	/* Identificador enviado en el mensaje, en caso de existir. */
	private String actionId;

	public ByteBuffer getContent() {
		return content;
	}

	public void setContent(ByteBuffer content) {
		this.content = content;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getActionId() {
		return actionId;
	}

	public void setActionId(String actionId) {
		this.actionId = actionId;
	}

	@Override
	public Schema getSchema() {
		return SCHEMA$;
	}

	@Override
	public java.lang.Object get(int field$) {
		switch (field$) {
		case 0:
			return content;
		case 1:
			return userId.toString();
		case 2:
			return actionId != null ? actionId.toString() : null;
		default:
			throw new org.apache.avro.AvroRuntimeException("Bad index");
		}
	}

	// Used by DatumReader. Applications should not call.
	@Override
	public void put(int field$, java.lang.Object value$) {
		switch (field$) {
		case 0:
			content = (ByteBuffer) value$;
			break;
		case 1:
			userId = value$.toString();
			break;
		case 2:
			actionId = value$ != null ? value$.toString() : null;
			break;
		default:
			throw new org.apache.avro.AvroRuntimeException("Bad index");
		}
	}
}
