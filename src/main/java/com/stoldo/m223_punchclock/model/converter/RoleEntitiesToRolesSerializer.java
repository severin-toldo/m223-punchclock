package com.stoldo.m223_punchclock.model.converter;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.stoldo.m223_punchclock.model.entity.RoleEntity;

/**
 * This class and or some of its attributes / fields got added / changed because they were needed.
 * */
public class RoleEntitiesToRolesSerializer extends JsonSerializer<List<RoleEntity>> {

	@Override
	public void serialize(List<RoleEntity> roleEntites, JsonGenerator jsonGenerator, SerializerProvider serializers) throws IOException {
		if (roleEntites != null) {
			jsonGenerator.writeStartArray();
	        
			for (RoleEntity re : roleEntites) {
	        	jsonGenerator.writeObject(re.getRole().name());
			}
			
	        jsonGenerator.writeEndArray();	
		}
	}
	
}
