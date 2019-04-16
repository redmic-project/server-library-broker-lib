package es.redmic.brokerlib.config;

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

import static org.junit.Assert.assertEquals;

import org.apache.avro.Schema;
import org.apache.avro.reflect.ReflectData;
import org.junit.Test;

import es.redmic.brokerlib.avro.common.MessageWrapper;

public class GenerateAvroSchema {

	@Test
	public void generateSchema() {

		MessageWrapper msg = new MessageWrapper();

		Schema schema = ReflectData.get().getSchema(MessageWrapper.class);

		assertEquals(msg.getSchema(), schema);
	}

}
