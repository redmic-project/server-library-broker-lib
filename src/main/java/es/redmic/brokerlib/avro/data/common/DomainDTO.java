package es.redmic.brokerlib.avro.data.common;

/*-
 * #%L
 * broker-lib
 * %%
 * Copyright (C) 2019 REDMIC Project / Server
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import es.redmic.brokerlib.avro.common.CommonDTO;

public abstract class DomainDTO extends CommonDTO {

	public DomainDTO() {
		super();
	}

	@NotNull
	@Size(min = 1, max = 150)
	protected String name;

	@NotNull
	@Size(min = 1, max = 150)
	private String name_en;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName_en() {
		return this.name_en;
	}

	public void setName_en(String name_en) {
		this.name_en = name_en;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((name_en == null) ? 0 : name_en.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		DomainDTO other = (DomainDTO) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (name_en == null) {
			if (other.name_en != null)
				return false;
		} else if (!name_en.equals(other.name_en))
			return false;
		return true;
	}
}
