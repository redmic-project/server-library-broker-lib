package es.redmic.brokerlib.avro.fail;

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

import es.redmic.brokerlib.avro.common.EventTypes;
import es.redmic.brokerlib.avro.common.SimpleEvent;

public class RollbackEvent extends SimpleEvent {

	// @formatter:off

	public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{"
		+ "\"type\":\"record\",\"name\":\"RollbackEvent\","
				+ "\"namespace\":\"es.redmic.brokerlib.avro.fail\",\"fields\":["
			+ getEventBaseSchema() + "]}");
	
	// @formatter:on

	static String type = EventTypes.ROLLBACK;

	public RollbackEvent() {
		super(type);
	}

	@Override
	public Schema getSchema() {
		return SCHEMA$;
	}

}
