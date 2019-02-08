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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((activity == null) ? 0 : activity.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (getClass() != obj.getClass())
			return false;
		PropertiesDTO other = (PropertiesDTO) obj;
		if (activity == null) {
			if (other.activity != null)
				return false;
		} else if (!activity.equals(other.activity))
			return false;
		return true;
	}

}
