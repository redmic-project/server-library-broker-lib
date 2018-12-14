package es.redmic.brokerlib.avro.geodata.common;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.locationtech.jts.geom.Geometry;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kjetland.jackson.jsonSchema.annotations.JsonSchemaIgnore;
import com.kjetland.jackson.jsonSchema.annotations.JsonSchemaNotNull;

import es.redmic.brokerlib.avro.common.CommonDTO;
import es.redmic.brokerlib.avro.geodata.utils.GeoJSONFeatureType;
import es.redmic.jts4jackson.module.JTSModule;

public abstract class FeatureDTO<TProperties extends PropertiesBaseDTO, TGeometry extends Geometry> extends CommonDTO {

	@JsonIgnore
	protected ObjectMapper mapper = new ObjectMapper().registerModule(new JTSModule());

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((geometry == null) ? 0 : geometry.hashCode());
		result = prime * result + ((properties == null) ? 0 : properties.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
		return result;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		FeatureDTO other = (FeatureDTO) obj;
		if (geometry == null) {
			if (other.geometry != null)
				return false;
		} else if (!geometry.equals(other.geometry))
			return false;
		if (properties == null) {
			if (other.properties != null)
				return false;
		} else if (!properties.equals(other.properties))
			return false;
		if (type != other.type)
			return false;
		if (uuid == null) {
			if (other.uuid != null)
				return false;
		} else if (!uuid.equals(other.uuid))
			return false;
		return true;
	}

}
