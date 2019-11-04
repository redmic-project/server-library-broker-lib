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

import es.redmic.brokerlib.avro.common.SimpleEvent;

public abstract class BaseRollbackEvent extends SimpleEvent {

	private String failEventType;

	public BaseRollbackEvent(String type) {
		super(type);
	}

	public String getFailEventType() {
		return failEventType;
	}

	public void setFailEventType(String failEventType) {
		this.failEventType = failEventType;
	}
}