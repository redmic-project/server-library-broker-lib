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

public abstract class EventTypes {

	public static String
	// @formatter:off
		//CREATE
		CREATE = "CREATE",
		ENRICH_CREATE = "ENRICH_CREATE",
		CREATE_ENRICHED = "CREATE_ENRICHED",
		CREATE_ENRICH_FAILED = "CREATE_ENRICH_FAILED",
		CREATE_CONFIRMED = "CREATE_CONFIRMED",
		CREATED = "CREATED",
		CREATE_FAILED = "CREATE_FAILED",
		CREATE_CANCELLED = "CREATE_CANCELLED",
		//UPDATE
		UPDATE = "UPDATE",
		ENRICH_UPDATE = "ENRICH_UPDATE",
		UPDATE_ENRICHED = "UPDATE_ENRICHED",
		UPDATE_ENRICH_FAILED = "UPDATE_ENRICH_FAILED",
		UPDATE_CONFIRMED = "UPDATE_CONFIRMED",
		UPDATED = "UPDATED",
		UPDATE_FAILED = "UPDATE_FAILED",
		UPDATE_CANCELLED = "UPDATE_CANCELLED",
		//DELETE
		DELETE = "DELETE",
		CHECK_DELETE = "CHECK_DELETE",
		DELETE_CHECKED = "DELETE_CHECKED",
		DELETE_CHECK_FAILED = "DELETE_CHECK_FAILED",
		DELETE_CONFIRMED = "DELETE_CONFIRMED",
		DELETED = "DELETED",
		DELETE_FAILED = "DELETE_FAILED",
		DELETE_CANCELLED = "DELETE_CANCELLED";
		//@formatter:on

	/**
	 * Función que identifica si un evento no corresponde con el final de un ciclo
	 * 
	 * @param eventType
	 *            tipo de evento
	 * @return true si el tipo coincide con un evento final, false en caso contrario
	 **/
	protected static boolean isLocked(String eventType) {

		return !(eventType.equals(EventTypes.CREATED.toString()) || eventType.equals(EventTypes.UPDATED.toString())
				|| eventType.equals(EventTypes.CREATE_CANCELLED.toString())
				|| eventType.equals(EventTypes.UPDATE_CANCELLED.toString())
				|| eventType.equals(EventTypes.DELETE_CANCELLED.toString()));
	}

	/**
	 * Función que identifica si un evento contiene el último estado correcto del
	 * elemento
	 * 
	 * @param eventType
	 *            tipo de evento
	 * @return true si el tipo coincide con snapshot, false en caso contrario
	 **/
	protected static boolean isSnapshot(String eventType) {

		return (eventType.equals(EventTypes.CREATED.toString()) || eventType.equals(EventTypes.UPDATED.toString())
				|| eventType.equals(EventTypes.DELETED.toString()));
	}

	/**
	 * Función que identifica si el contenido de un evento puede ser modificado
	 * 
	 * @param eventType
	 *            tipo de evento
	 * @return true si el tipo coincide con finales de ciclo y no borrado, false en
	 *         caso contrario
	 **/
	protected static boolean isUpdatable(String eventType) {

		return (isSnapshot(eventType) && !eventType.equals(EventTypes.DELETED.toString()));
	}
}
