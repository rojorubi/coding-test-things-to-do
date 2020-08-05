package com.mimacom.infrastructure.configuration;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mimacom.infrastructure.mappers.CodingTestRankingMapper;
import com.mimacom.infrastructure.mappers.CodingTestRankingMapperImpl;

@Configuration
public class MapperConfiguration {
	
	@Bean(name="mapper")
	public DozerBeanMapper getMapper() {
		
		CodingTestRankingMapper dozerMapper = new CodingTestRankingMapperImpl();
		DozerBeanMapper mapper = new DozerBeanMapper();
		mapper.addMapping(dozerMapper.getMappingBuilder());
		
		return mapper;
	}
	
	@Bean(name="dozerMapper")	
	public DozerBeanMapper getStandardDozerMapper() {	
		return new DozerBeanMapper();
	}
}
