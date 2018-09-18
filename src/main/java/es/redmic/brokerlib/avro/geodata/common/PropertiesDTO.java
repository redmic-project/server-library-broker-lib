package es.redmic.brokerlib.avro.geodata.common;

import javax.validation.constraints.Size;

import com.kjetland.jackson.jsonSchema.annotations.JsonSchemaIgnore;

public abstract class PropertiesDTO extends UpdatablePropertiesDTO {

	public PropertiesDTO() {
		super();
	}

	@JsonSchemaIgnore
	@Size(min = 1, max = 150)
	private String activity;

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}
}
