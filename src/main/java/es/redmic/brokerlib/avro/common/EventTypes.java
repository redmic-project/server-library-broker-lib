package es.redmic.brokerlib.avro.common;

public abstract class EventTypes {

	public static String
	// @formatter:off
		//CREATE
		CREATE = "CREATE",
		CREATE_CONFIRMED = "CREATE_CONFIRMED",
		CREATED = "CREATED",
		CREATE_FAILED = "CREATE_FAILED",
		CREATE_CANCELLED = "CREATE_CANCELLED",
		//UPDATE
		UPDATE = "UPDATE",
		UPDATE_CONFIRMED = "UPDATE_CONFIRMED",
		UPDATED = "UPDATED",
		UPDATE_FAILED = "UPDATE_FAILED",
		UPDATE_CANCELLED = "UPDATE_CANCELLED",
		//DELETE
		DELETE = "DELETE",
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
