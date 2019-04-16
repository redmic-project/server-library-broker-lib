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

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

public abstract class SimpleEvent extends Event {

	public SimpleEvent(String type) {
		super(type);
	}

	@Override
	public Object get(int field$) {
		switch (field$) {
		case 0:
			return getAggregateId();
		case 1:
			return getVersion();
		case 2:
			return getType();
		case 3:
			return getDate().getMillis();
		case 4:
			return getSessionId();
		case 5:
			return getUserId();
		case 6:
			return getId();
		default:
			throw new org.apache.avro.AvroRuntimeException("Bad index");
		}
	}

	@Override
	public void put(int field$, Object value$) {
		switch (field$) {
		case 0:
			setAggregateId(value$.toString());
			break;
		case 1:
			setVersion((java.lang.Integer) value$);
			break;
		case 2:
			setType(value$.toString());
			break;
		case 3:
			setDate(new DateTime(value$, DateTimeZone.UTC));
			break;
		case 4:
			setSessionId(value$.toString());
			break;
		case 5:
			setUserId(value$.toString());
			break;
		case 6:
			setId(value$.toString());
			break;
		default:
			throw new org.apache.avro.AvroRuntimeException("Bad index");
		}
	}
}
