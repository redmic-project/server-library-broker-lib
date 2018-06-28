package es.redmic.brokerlib.deserializer;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TestDTO {

	private String id;

	@NotNull
	@Size(min = 1, max = 50)
	private String name;

	@NotNull
	@Size(min = 1, max = 50)
	private String name_en;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public TestDTO() {

	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String getName_en() {
		return this.name_en;
	}

	public void setName_en(String name_en) {
		this.name_en = name_en;
	}
}
