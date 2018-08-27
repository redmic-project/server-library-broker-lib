package es.redmic.brokerlib.avro.common;

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

	protected static boolean isLocked(String eventType) {

		return !(eventType.equals(EventTypes.CREATED.toString()) || eventType.equals(EventTypes.UPDATED.toString())
				|| eventType.equals(EventTypes.CREATE_CANCELLED.toString())
				|| eventType.equals(EventTypes.UPDATE_CANCELLED.toString())
				|| eventType.equals(EventTypes.DELETE_CANCELLED.toString()));
	}
}
