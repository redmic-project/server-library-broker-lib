package es.redmic.brokerlib.avro.geodata.common;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.kjetland.jackson.jsonSchema.annotations.JsonSchemaIgnore;
import com.kjetland.jackson.jsonSchema.annotations.JsonSchemaNotNull;
import com.vividsolutions.jts.geom.Geometry;

import es.redmic.brokerlib.avro.common.CommonDTO;

public abstract class FeatureDTO<TProperties extends PropertiesBaseDTO, TGeometry extends Geometry> extends CommonDTO {

	private String uuid;

	@JsonSchemaIgnore
	protected GeoJSONFeatureType type;

	@JsonSchemaNotNull
	@NotNull
	@Valid
	private TProperties properties;

	@NotNull
	private TGeometry geometry;

	public FeatureDTO() {

	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public void setType(GeoJSONFeatureType type) {
		this.type = type;
	}

	public GeoJSONFeatureType getType() {
		return type;
	}

	public void setProperties(TProperties properties) {
		this.properties = properties;
	}

	public TProperties getProperties() {
		return properties;
	}

	public TGeometry getGeometry() {
		return geometry;
	}

	public void setGeometry(TGeometry geometry) {
		this.geometry = geometry;
	}

}
