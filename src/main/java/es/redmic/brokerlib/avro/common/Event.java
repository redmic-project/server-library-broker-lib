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

import java.io.Serializable;
import java.util.UUID;

import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class Event extends CommonDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String aggregateId;

	private Integer version;

	private String type;

	private DateTime date;

	private String sessionId;

	private String userId;

	public Event(String type) {
		setId(UUID.randomUUID().toString());
		date = DateTime.now();
		setType(type);
	}

	public String getAggregateId() {
		return aggregateId;
	}

	public void setAggregateId(String aggregateId) {
		this.aggregateId = aggregateId;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public DateTime getDate() {
		return date;
	}

	public void setDate(DateTime date) {
		this.date = date;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	// Eventos de confimación y fallo deben tener los mismos metadatos
	@SuppressWarnings("unchecked")
	public <T> T buildFrom(Event source) {

		setAggregateId(source.getAggregateId());
		setVersion(source.getVersion());
		setSessionId(source.getSessionId());
		setUserId(source.getUserId());
		return (T) this;
	}

	@JsonIgnore
	public static String getEventBaseSchema() {
		// @formatter:off
		return "{\"name\":\"aggregateId\",\"type\":\"string\"},"
				+ "{\"name\":\"version\",\"type\":[\"int\", \"null\"]},"
				+ "{\"name\":\"type\",\"type\":\"string\"},"
				+ "{\"name\":\"date\",\"type\":{\"type\":\"long\",\"logicalType\":\"timestamp-millis\"}},"
				+ "{\"name\":\"sessionId\",\"type\":\"string\"},"
				+ "{\"name\":\"userId\",\"type\":\"string\"},"
				+ "{\"name\":\"id\",\"type\":\"string\"}";
		// @formatter:on
	}
}
