package es.redmic.brokerlib.avro.common;

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
