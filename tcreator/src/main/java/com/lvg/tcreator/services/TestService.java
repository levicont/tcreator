package com.lvg.tcreator.services;

import java.util.List;

import com.lvg.tcreator.models.Test;

public interface TestService extends GenericService<Test>{
	public void saveAll(List<Test> testList);
}
