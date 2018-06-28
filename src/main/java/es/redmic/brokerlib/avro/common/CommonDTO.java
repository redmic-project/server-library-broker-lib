package es.redmic.brokerlib.avro.common;

import com.kjetland.jackson.jsonSchema.annotations.JsonSchemaNotNull;

@JsonSchemaNotNull
public abstract class CommonDTO extends org.apache.avro.specific.SpecificRecordBase
		implements org.apache.avro.specific.SpecificRecord {

	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
