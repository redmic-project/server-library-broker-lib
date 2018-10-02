package es.redmic.brokerlib.avro.geodata.utils;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum GeoJSONFeatureType implements BaseEnumType {

	FEATURE(Constants.FEATURE);

	final String type;

	GeoJSONFeatureType(String type) {
		this.type = type;
	}

	@JsonCreator
	public static GeoJSONFeatureType fromString(String text) {
		if (text != null) {
			for (GeoJSONFeatureType b : GeoJSONFeatureType.values()) {
				if (text.equalsIgnoreCase(b.type)) {
					return b;
				}
			}
		}
		throw new IllegalArgumentException(text + " has no corresponding value");
	}

	@JsonValue
	@Override
	public String toString() {
		return type;
	}

	public static class Constants {
		public static final String FEATURE = "Feature";
	}
}